package com.sarah_wissocq_adrien_agez.bibliotheque.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sarah_wissocq_adrien_agez.bibliotheque.R;
import com.sarah_wissocq_adrien_agez.bibliotheque.book.Book;

/**
 * Created by agez on 12/11/15.
 */
public class ViewBookDetails extends Fragment {

    @Override
    public View  onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        Intent intent = this.getActivity().getIntent();
        Book book = (Book) intent.getSerializableExtra("Book");
        TextView title = (TextView) this.getActivity().findViewById(R.id.title);
        TextView author = (TextView) this.getActivity().findViewById(R.id.author);
        TextView isbn = (TextView) this.getActivity().findViewById(R.id.isbn);
        TextView details = (TextView) this.getActivity().findViewById(R.id.details);

        title.setText(book.getTitle());
        author.setText(book.getTitle());
        isbn.setText(book.getIsbn());
        details.setText(book.getSummary());

        return inflater.inflate(R.layout.layout_book_details, container);
    }
}
