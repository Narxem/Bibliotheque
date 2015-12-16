package com.sarah_wissocq_adrien_agez.bibliotheque.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.sarah_wissocq_adrien_agez.bibliotheque.R;
import com.sarah_wissocq_adrien_agez.bibliotheque.book.Book;

/**
 *
 */
public class DetailsBook extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_book_details);
        Bundle extras = getIntent().getBundleExtra("livre");
        Book b = (Book) extras.getSerializable("myBook");

        ImageView imageView = (ImageView) this.findViewById(R.id.image);
        TextView auteurView = (TextView) this.findViewById(R.id.author);
        TextView titreView = (TextView) this.findViewById(R.id.title);
        TextView editorView = (TextView) this.findViewById(R.id.editor);
        TextView serieView = (TextView) this.findViewById(R.id.serie);
        TextView numSerieView = (TextView) this.findViewById(R.id.numSerie);
        TextView yearView = (TextView) this.findViewById(R.id.year);
        TextView isbnView = (TextView) this.findViewById(R.id.isbn);
        TextView detailsView = (TextView) this.findViewById(R.id.details);

        String authors  = b.getAuthors().toString();
        auteurView.setText(authors.substring(1, authors.length() - 1));
        titreView.setText(b.getTitle());
        editorView.setText(b.getEditor());
        serieView.setText(b.getSeries());
        if(b.getNumSeries()==-1){
            numSerieView.setText("Numéro de série inconnu");
        }else {
            numSerieView.setText(Integer.toString(b.getNumSeries()));
        }
        if(b.getYear()==-1){
            yearView.setText("Année de publication inconnue");
        }else {
            yearView.setText(Integer.toString(b.getYear()));
        }
        isbnView.setText(b.getIsbn());
        detailsView.setText(b.getSummary());
    }
}
