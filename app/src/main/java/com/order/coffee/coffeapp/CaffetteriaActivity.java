package com.order.coffee.coffeapp;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;

import com.order.coffee.coffeapp.Database.Contract;

public class CaffetteriaActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    ListView mListView;
    CursorAdapter mCursorAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caffetteria);

        mListView = findViewById(R.id.listView_caffetteria);
        mCursorAdapter = new com.order.coffee.coffeapp.Adapters.CursorAdapter(this,null);
        mListView.setAdapter(mCursorAdapter);
        getLoaderManager().initLoader(0,null,this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {

        String[] projector = {
                Contract.ProdottiDataBase._ID,
                Contract.ProdottiDataBase.COLUMN_NOME,
                Contract.ProdottiDataBase.COLUMN_PREZZO,
                Contract.ProdottiDataBase.COLUMN_CATEGORY
        };
        return new CursorLoader(CaffetteriaActivity.this,Contract.URI_CONTENT,projector,Contract.ProdottiDataBase.COLUMN_CATEGORY + " = 'Caffetteria'",null,null);
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
