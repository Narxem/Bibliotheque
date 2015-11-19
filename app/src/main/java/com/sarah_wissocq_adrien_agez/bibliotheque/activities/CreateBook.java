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

import com.sarah_wissocq_adrien_agez.bibliotheque.R;
import com.sarah_wissocq_adrien_agez.bibliotheque.book.Book;
import com.sarah_wissocq_adrien_agez.bibliotheque.book.database.BookDAO;

import java.io.IOException;
import java.sql.SQLException;

public class CreateBook extends Activity {

    private static final int SELECT_PHOTO = 1;
    private Uri uri;
    private BitmapDrawable bm=new BitmapDrawable();

    private BookDAO bookDAO = new BookDAO(this);

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
     * Récupère l'image et la sauvegarde
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
        String author= editAuthor.getText().toString();

        /** Récupère le titre */
        EditText editTitle= (EditText) findViewById(R.id.title);
        String title = editTitle.getText().toString();

        /** Récupère l'ISBN */
        EditText editIsbn = (EditText) findViewById(R.id.isbn);
        String isbn = editIsbn.getText().toString();

        /** Récupère la description */
        EditText editDetail = (EditText) findViewById(R.id.details);
        String detail = editDetail.getText().toString();

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        /**  String image = imageView.getDrawable().toString();
         String image=this.uri.getPath();*/

        /** Ajoute le livre à la bibliothèque */
        // TODO compléter le Book
        bookDAO.insert(new Book(title));

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
