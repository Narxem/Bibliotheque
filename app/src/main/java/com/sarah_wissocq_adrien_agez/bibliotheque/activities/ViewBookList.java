package com.sarah_wissocq_adrien_agez.bibliotheque.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sarah_wissocq_adrien_agez.bibliotheque.FilterMenu;
import com.sarah_wissocq_adrien_agez.bibliotheque.R;
import com.sarah_wissocq_adrien_agez.bibliotheque.book.Book;
import com.sarah_wissocq_adrien_agez.bibliotheque.book.BookAdapter;
import com.sarah_wissocq_adrien_agez.bibliotheque.book.database.BookDAO;

import java.sql.SQLException;
import java.util.List;

//import static com.sarah_wissocq_adrien_agez.bibliotheque.book.database.BookDatabaseHelper.BOOK_AUTHOR;

public class ViewBookList extends FragmentActivity {

    private List<Book> bookList;
    private BookDAO bookDAO = new BookDAO(this);
    private float mDownX;
    private float mDownY;
    private final float SCROLL_THRESHOLD = 10;
    private boolean isOnClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_view_book_list);
        bookDAO = new BookDAO(this);
        try {
            bookDAO.openWritable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /** Créer une liste de livres */
         bookList = bookDAO.getAllBooks();
        /** Utiliser le filtre pour filtrer... */
        BookAdapter adapter = new BookAdapter(this, bookList);
        final ListView listView = (ListView) findViewById(R.id.list);
        registerForContextMenu(listView);
        listView.setAdapter(adapter);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_book_list, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bookDAO.close();
    }


    @Override
    /**
     * Créer un menu permettant de modifier ou de supprimer
     */
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId()==R.id.list) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_list, menu);
        }
    }

    @Override
    /**
     * Modifier / Supprimer
     */
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        ListView listView = (ListView) findViewById(R.id.list);
        switch(item.getItemId()) {
            case R.id.edit:
                // edit stuff here
                return true;
            case R.id.detail:
                if(!isFinishing()) {
                    Book book = (Book) listView.getAdapter().getItem(info.position);
                    this.closeContextMenu();
                    Intent activity = new Intent(this, DetailsBook.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("myBook", book);
                    activity.putExtra("livre", bundle);
                    startActivity(activity);
                }
                return true;
            case R.id.delete:
                /** Récupère le livre est le supprime de la librairie */
                Book bookToDelete = (Book) listView.getAdapter().getItem(info.position);
                bookDAO.deleteBook(bookToDelete);
                bookList.remove(bookToDelete);

                /** Affiche une boîte de dialogue pour confirmer que le livre a été supprimé */
                AlertDialog.Builder alert=new AlertDialog.Builder(this);
                alert.setTitle(R.string.suppress);
                alert.setMessage(R.string.BookSuppressed);
                alert.setPositiveButton(R.string.ok, new OkListener());
                alert.show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void addFilter(View view){
        Intent intent = new Intent(this, FilterMenu.class);
        startActivity(intent);
    }


    /**
     * Redémarre l'activité afin de ne plus voir le livre supprimé dans la liste.
     */
    private final class OkListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            ViewBookList.this.finish();
            Intent intent = new Intent(ViewBookList.this, ViewBookList.class);
            startActivity(intent);
        }
    }
}