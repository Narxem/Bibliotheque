package com.sarah_wissocq_adrien_agez.bibliotheque.book;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Parcel;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sarah_wissocq_adrien_agez.bibliotheque.R;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wissocq on 29/09/15.
 */
public class BookAdapter extends ArrayAdapter<Book> {
    public BookAdapter(Context context, ArrayList<Book> library) {
        super(context, 0, library);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Book book = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_book, parent, false);
        }
        // Lookup view for data population
        ImageView imageView = (ImageView) convertView.findViewById(R.id.image);
        TextView auteurView = (TextView) convertView.findViewById(R.id.author);
        TextView titreView = (TextView) convertView.findViewById(R.id.title);
        TextView isbnView = (TextView) convertView.findViewById(R.id.isbn);

        /**Drawable img= Drawable.createFromPath(book.getImg());
        image.setImageDrawable(img);
        String imgpath = book.getImg();
        BitmapDrawable bm = new BitmapDrawable(imgpath);
        image.setImageBitmap(bm);*/

        Bitmap bm = BitmapFactory.decodeFile(book.getImg());
        BitmapDrawable bitmapDrawable = new BitmapDrawable(bm);
        imageView.setImageDrawable(book.getImage());

        auteurView.setText(book.author);
        titreView.setText(book.title);
        isbnView.setText(book.isbn);

        // Return the completed view to render on screen
        return convertView;
    }
}