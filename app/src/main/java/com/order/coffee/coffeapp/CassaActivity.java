package com.order.coffee.coffeapp;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CursorAdapter;
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


        getLoaderManager().initLoader(0,null,this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {

        String[] projector = {
                Contract.OrdindeDataBase._ID,
                Contract.OrdindeDataBase.COLUMN_NOME_PRODOTTO,
                Contract.OrdindeDataBase.COLUMN_PREZZO_PRODOTTO,
                Contract.OrdindeDataBase.COLUMN_QUANTITA
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
}