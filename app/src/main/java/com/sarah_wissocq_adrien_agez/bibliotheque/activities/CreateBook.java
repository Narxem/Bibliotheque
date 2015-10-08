package com.sarah_wissocq_adrien_agez.bibliotheque.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.sarah_wissocq_adrien_agez.bibliotheque.R;
import com.sarah_wissocq_adrien_agez.bibliotheque.book.Book;

import java.io.IOException;
import java.io.InputStream;

import static com.sarah_wissocq_adrien_agez.bibliotheque.activities.Main.LIBRARY;

public class CreateBook extends ActionBarActivity {

    private static final int SELECT_PHOTO = 1;
    private Uri uri;
    private BitmapDrawable bm=new BitmapDrawable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_create_book);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_book, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECT_PHOTO && resultCode == RESULT_OK && data != null && data.getData() != null) {

            this.uri = data.getData();
            String[] projection = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(projection[0]);
            String picturePath = cursor.getString(columnIndex); // returns null
            cursor.close();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                bm= new BitmapDrawable(bitmap);
                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void chercherImage(View view){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }

    public void createBooks(View view){

        EditText editAuthor = (EditText) findViewById(R.id.author);
        String author= editAuthor.getText().toString();

        EditText editTitle= (EditText) findViewById(R.id.title);
        String title = editTitle.getText().toString();

        EditText editIsbn = (EditText) findViewById(R.id.isbn);
        String isbn = editIsbn.getText().toString();

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        /**  String image = imageView.getDrawable().toString();
      String image=this.uri.getPath();*/

            LIBRARY.addBook(new Book(author, title, isbn, this.bm));

        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle(R.string.creation_book);
        alert.setMessage(R.string.confirm_creation);
        alert.setPositiveButton(R.string.ok, new OkListener());
        alert.show();
    }


    private final class OkListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            CreateBook.this.finish();
            Intent intent = new Intent(CreateBook.this, CreateBook.class);
            startActivity(intent);
        }
    }
}
