package com.sarah_wissocq_adrien_agez.bibliotheque.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sarah_wissocq_adrien_agez.bibliotheque.R;
import com.sarah_wissocq_adrien_agez.bibliotheque.book.Book;
import com.sarah_wissocq_adrien_agez.bibliotheque.book.BookAdapter;
import com.sarah_wissocq_adrien_agez.bibliotheque.book.BookLibrary;


public class ViewBook extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book);

        /** Créer une liste de livres */
        BookAdapter adapter = new BookAdapter(this, BookLibrary.LIBRARY);
        final ListView listView = (ListView) findViewById(R.id.lvBook);
        registerForContextMenu(listView);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_book, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    /**
     * Créer un menu permettant de modifier ou de supprimer
     */
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId()==R.id.lvBook) {
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
        ListView listView = (ListView) findViewById(R.id.lvBook);
        switch(item.getItemId()) {
            case R.id.edit:
                // edit stuff here
                return true;
            case R.id.delete:
                /** Récupère le livre est le supprime de la librairie */
                BookLibrary.LIBRARY.removeBook((Book) listView.getAdapter().getItem(info.position));

                /** Affiche une boîte de dialogue pour confirmer que le livre a été supprimé */
                AlertDialog.Builder alert=new AlertDialog.Builder(this);
                alert.setTitle(R.string.suppression_book);
                alert.setMessage(R.string.confirm_suppression);
                alert.setPositiveButton(R.string.ok, new OkListener());
                alert.show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }


    /**
     * Redémarre l'activité afin de ne plus voir le livre supprimé dans la liste.
     */
    private final class OkListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            ViewBook.this.finish();
            Intent intent = new Intent(ViewBook.this, ViewBook.class);
            startActivity(intent);
        }
    }
}



