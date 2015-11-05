package com.sarah_wissocq_adrien_agez.bibliotheque.book;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sarah_wissocq_adrien_agez.bibliotheque.R;

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
        TextView isbnView = (TextView) convertView.findViewById(R.id.isbn);

        /** Drawable img= Drawable.createFromPath(book.getImg());
         image.setImageDrawable(img);
         String imgpath = book.getImg();
         BitmapDrawable bm = new BitmapDrawable(imgpath);
         image.setImageBitmap(bm);

         Bitmap bm = BitmapFactory.decodeFile(book.getImg());
         BitmapDrawable bitmapDrawable = new BitmapDrawable(bm);*/

        imageView.setImageDrawable(book.getImage());

        auteurView.setText(book.getAuthor());
        titreView.setText(book.getTitle());
        isbnView.setText(book.getIsbn()
        );

        // Retourne view
        return convertView;
    }
}
