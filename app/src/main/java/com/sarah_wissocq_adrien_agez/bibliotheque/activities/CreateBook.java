package com.sarah_wissocq_adrien_agez.bibliotheque.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sarah_wissocq_adrien_agez.bibliotheque.R;
import com.sarah_wissocq_adrien_agez.bibliotheque.book.Book;
import com.sarah_wissocq_adrien_agez.bibliotheque.book.database.BookDAO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe permettant de créer un livre manuellement ou en saisissant l'ISBN
 */
public class CreateBook extends Activity {

    private static final int SELECT_PHOTO = 1;
    private Uri uri;
    private BitmapDrawable bm=new BitmapDrawable();
    private BookDAO bookDAO = new BookDAO(this);
    private static final String OURKEY ="AIzaSyC288QGqDhvI56ljFezhgMZbgZ1xjP8QrI";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        try {
            bookDAO.openWritable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_create_book);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bookDAO.close();
    }


    @Override
    /**
     * Récupère l'image choisi dans la gallery et la sauvegarde
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /** Récupérer l'image dans la gallery */
        if (requestCode == SELECT_PHOTO && resultCode == RESULT_OK && data != null && data.getData() != null) {
            /** Récupère l'uri */
            this.uri = data.getData();
            String[] projection = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(projection[0]);
            String picturePath = cursor.getString(columnIndex); // returns null
            cursor.close();

            try {
                /** Converti l'uri en bitmap */
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                /** Sauvegarde le bitmap */
                bm= new BitmapDrawable(bitmap);
                /** AfficheZ le bitmap */
                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /** Prendre une photo */

    }

    /**
     *  Permet de rechercher un livre grace à l'isbn
     * @param v
     */
    public void chercherLivre(View v){
        EditText editIsbn = (EditText) findViewById(R.id.isbn);
        String isbn = editIsbn.getText().toString();
        String bookSearchString = "https://www.googleapis.com/books/v1/volumes?" + "q=isbn:" + isbn + "&key=" + OURKEY;
        doGoogleBookRequest(bookSearchString);
    }

    /** Attention, duplication de code (ScanBook), a régler plus tard*/
    private void doGoogleBookRequest(String url) {
        final Book book = new Book();
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String reponse) {
                System.out.println("OnResponse");
                parseJsonResult(reponse, book);
                EditText editTitle= (EditText) findViewById(R.id.title);
                editTitle.setText(book.getTitle(), TextView.BufferType.EDITABLE);
                EditText editAuthor= (EditText) findViewById(R.id.author);
                String authors = book.getAuthors().toString();
                editAuthor.setText(authors.substring(1, authors.length() - 1).replace(',', ';'), TextView.BufferType.EDITABLE);
                EditText editEditor= (EditText) findViewById(R.id.editor);
                editEditor.setText(book.getEditor(), TextView.BufferType.EDITABLE);
                EditText editYear = (EditText) findViewById(R.id.year);
                editYear.setText(new Integer(book.getYear()).toString(), TextView.BufferType.EDITABLE);
                EditText editSummary = (EditText) findViewById(R.id.details);
                editSummary.setText(book.getSummary(), TextView.BufferType.EDITABLE);
            }

        }, new Response.ErrorListener() {
            @Override public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);

    }


    public void parseJsonResult(String reponse, Book book) {
        if (reponse != null) {
            try {
                JSONObject volumes = new JSONObject(reponse);
                JSONArray items = volumes.getJSONArray("items");
                JSONObject volume = items.getJSONObject(0);
                JSONObject volumeInfo = volume.getJSONObject("volumeInfo");
                book.setTitle(volumeInfo.getString("title"));
                JSONArray JSONauthors = volumeInfo.getJSONArray("authors");
                List<String> authors = new LinkedList<String>();
                for (int i = 0; i < JSONauthors.length(); i++)
                    authors.add(JSONauthors.getString(i));
                book.setAuthors(authors);
                book.setEditor(volumeInfo.getString("publisher"));
                book.setSummary(volumeInfo.getString("description"));
                try {
                    book.setYear(Integer.parseInt(volumeInfo.getString("publishedDate").substring(0, 4)));
                } catch (Exception e) {}

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Démarre une application pour rechercher une image
     */
    public void chercherImage(View view){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }

    /**public void prendrePhoto(View view){
    }
*/
    /**
     * Créer le livre lorqu'on appuie sur le bouton.
     */
    public void createBooks(View view){

        /** Récupère le nom de l'auteur */
        EditText editAuthor = (EditText) findViewById(R.id.author);
        List<String> authors = Arrays.asList(editAuthor.getText().toString().split("\\s*;\\s*"));
        System.out.println(authors);

        /** Récupère le titre */
        EditText editTitle= (EditText) findViewById(R.id.title);
        String title = editTitle.getText().toString();

        /** Récupère l'éditeur */
        EditText editEditor= (EditText) findViewById(R.id.editor);
        String editor = editEditor.getText().toString();

        /** Récupère la série */
        EditText editSerie= (EditText) findViewById(R.id.serie);
        String serie = editSerie.getText().toString();

        /** Récupère le numéro dans la série */
        EditText editNumSerie = (EditText) findViewById(R.id.numSerie);
        int numSerie;
        try {
            numSerie = Integer.parseInt(editNumSerie.getText().toString());
        } catch (NumberFormatException e) {
            numSerie = -1;
        }


        /** Récupère l'année */
        EditText editYear= (EditText) findViewById(R.id.year);
        int year;
        try {
            year = Integer.parseInt(editYear.getText().toString());
        } catch (NumberFormatException e) {
            year = -1;
        }

        /** Récupère l'ISBN */
        EditText editIsbn = (EditText) findViewById(R.id.isbn);
        String isbn = editIsbn.getText().toString();

        /** Récupère la description */
        EditText editDetail = (EditText) findViewById(R.id.details);
        String detail = editDetail.getText().toString();

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
       // String image =imageView.getDrawable().toString();
       // String image = uri.toString();
       // String image=this.uri.getPath();

        /** Ajoute le livre à la bibliothèque */

        bookDAO.insert(new Book(title, serie, numSerie, editor, year, isbn, "", detail, authors));

        /** Affiche une boîte de dialogue pour confirmer que le livre a été créé */
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle(R.string.creation_book);
        alert.setMessage(R.string.confirm_creation);
        alert.setPositiveButton(R.string.ok, new OkListener());
        alert.show();
    }


    /**
     * Vérifie que le livre a bien été créé et redémarre l'activité afin de pouvoir recréer un autre livre.
     */
    private final class OkListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            CreateBook.this.finish();
            Intent intent = new Intent(CreateBook.this, CreateBook.class);
            startActivity(intent);
        }
    }
}
