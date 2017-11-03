package com.order.coffee.coffeapp;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;

import com.order.coffee.coffeapp.DatabaseProdotti.Contract;

public class CaffetteriaActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    ListView mListView;
    CursorAdapter mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caffetteria);

        mListView = findViewById(R.id.listView);
        mCursorAdapter = new com.order.coffee.coffeapp.Adapters.CursorAdapter(this,null);
        mListView.setAdapter(mCursorAdapter);
        getLoaderManager().initLoader(0,null,this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {

        String[] projector = {
                Contract.ProdottiDataBase._ID,
                Contract.ProdottiDataBase.COLUMN_NOME,
                Contract.ProdottiDataBase.COLUMN_PREZZO
        };
        return new CursorLoader(CaffetteriaActivity.this,Contract.URI_CONTENT,projector,null,null,null);
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
