package com.order.coffee.coffeapp;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.order.coffee.coffeapp.Adapters.CursorAdapterOrdini;
import com.order.coffee.coffeapp.Database.Contract;

public class CassaActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    ListView mListView;
    CursorAdapterOrdini mCursorAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cassa);

        mListView = findViewById(R.id.listView_cassa);
        mCursorAdapter = new CursorAdapterOrdini(this,null);
        mListView.setAdapter(mCursorAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Uri uri = ContentUris.withAppendedId(Contract.URI_CONTENT_ORDINI,l);
                getContentResolver().delete(uri,null,null);
            }
        });



        getLoaderManager().initLoader(0,null,this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {

        String[] projector = {
                Contract.OrdindeDataBase._ID,
                Contract.OrdindeDataBase.COLUMN_NOME_PRODOTTO,
                Contract.OrdindeDataBase.COLUMN_PREZZO_PRODOTTO,
                Contract.OrdindeDataBase.COLUMN_QUANTITA_PRODOTTO
        };
        return new CursorLoader(CassaActivity.this,Contract.URI_CONTENT_ORDINI,projector,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        mCursorAdapter.swapCursor(cursor);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

            mCursorAdapter.swapCursor(null);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete:
                getContentResolver().delete(Contract.URI_CONTENT_ORDINI,null,null);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}
