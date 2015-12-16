package com.sarah_wissocq_adrien_agez.bibliotheque.book;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sarah_wissocq_adrien_agez.bibliotheque.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Adrien Agez
 * @author Sarah Wissocq
 */
public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Context context, List<Book> library) {
        super(context, 0, library);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Récupère le livre a la position position
        Book book = getItem(position);
        // Vérifie que view n'existe pas sinon, l'inflate
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_book, parent, false);
        }
        // Récupère les données du livre
        ImageView imageView = (ImageView) convertView.findViewById(R.id.image);
        TextView auteurView = (TextView) convertView.findViewById(R.id.author);
        TextView titreView = (TextView) convertView.findViewById(R.id.title);
        TextView editorView = (TextView) convertView.findViewById(R.id.editor);

       /** Drawable img= Drawable.createFromPath(book.getCoverURI());
         image.setImageDrawable(img);

         String imgpath = book.getCoverURI();
         BitmapDrawable bm = new BitmapDrawable(imgpath);
        image.setImageDrawable(bm);

        Bitmap bm = BitmapFactory.decodeFile(book.getCoverURI());
       BitmapDrawable bitmapDrawable = new BitmapDrawable(bm);
       imageView.setImageBitmap(bm);*/

        String authors  = book.getAuthors().toString();

        auteurView.setText(authors.substring(1, authors.length() - 1));
        titreView.setText(book.getTitle());
        editorView.setText(book.getEditor()
        );

        // Retourne view
        return convertView;
    }
}
