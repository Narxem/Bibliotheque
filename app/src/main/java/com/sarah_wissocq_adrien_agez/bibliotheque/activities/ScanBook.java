package com.sarah_wissocq_adrien_agez.bibliotheque.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.sarah_wissocq_adrien_agez.bibliotheque.zxing.IntentIntegrator;
import com.sarah_wissocq_adrien_agez.bibliotheque.zxing.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


/**
 * Classe permettant de scanner le code barre d'un, de récupérer ses informations puis de l'ajouter à la bibliothèque.
 */
public class ScanBook extends Activity {

    private static final int SCAN_BOOK = 1;
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
        setContentView(R.layout.activity_scan_book);
        if (savedInstanceState == null) {
            // Lance l'application permettant de scanner un code barre.
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Fermer la base de données.
        bookDAO.close();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            Log.v("SCAN", "content :" + scanContent + " - format: " + scanFormat);
            if (scanContent != null && scanFormat != null && scanFormat.equalsIgnoreCase("EAN_13")) {
                String bookSearchString = "https://www.googleapis.com/books/v1/volumes?" + "q=isbn:" + scanContent + "&key=" + OURKEY;
                doGoogleBookRequest(bookSearchString);

            }
        }
    }

    /**
     * Permet d'effectuer la requête google book
     * @param url requête
     */
    private void doGoogleBookRequest(String url) {
        final Book book = new Book();
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String reponse) {
                System.out.println("OnResponse");
                parseJsonResult(reponse, book);
                //Remplissage des champs
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

    /**
     * Parse le Json afin de récupérer les informations nécessaire à la création du livre
     * @param reponse
     * @param book livre à modifier
     */
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
        startActivityForResult(photoPickerIntent, SCAN_BOOK);
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
        String image =null; // imageView.getDrawable().toString();

        /**String image=this.uri.getPath();*/

        /** Ajoute le livre à la bibliothèque */

        bookDAO.insert(new Book(title, serie, numSerie, editor, year, isbn, image, detail, authors));

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
            ScanBook.this.finish();
            Intent intent = new Intent(ScanBook.this, Main.class);
            startActivity(intent);
        }
    }
}
