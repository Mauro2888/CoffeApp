package com.order.coffee.coffeapp.DatabaseProdotti;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Mauro on 03/11/2017.
 */

public class ContentProvider extends android.content.ContentProvider {

    public Helper mHelper;
    public SQLiteDatabase mSQLite;
    public final static UriMatcher mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    public static final int PRODOTTI_ID = 100;
    public static final int PRODOTTI = 101;

    static {
        mUriMatcher.addURI(Contract.AUTHORITY,Contract.PATH_TABLE + "/#",PRODOTTI_ID);
        mUriMatcher.addURI(Contract.AUTHORITY,Contract.PATH_TABLE,PRODOTTI);
    }


    @Override
    public boolean onCreate() {
        mHelper = new Helper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projector, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortBy) {

        mSQLite = mHelper.getReadableDatabase();
        Cursor mCursor;
        int match = mUriMatcher.match(uri);
        switch (match){
            case PRODOTTI:
                mCursor = mSQLite.query(Contract.ProdottiDataBase.TABLE_NAME,projector,selection,selectionArgs,null,null,sortBy);
                break;
            case PRODOTTI_ID:
                selection = Contract.ProdottiDataBase._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                mCursor = mSQLite.query(Contract.ProdottiDataBase.TABLE_NAME,projector,selection,selectionArgs,null,null,sortBy);
                break;
                default:
                    throw new IllegalArgumentException("Errore Query " + uri);
        }
        mCursor.setNotificationUri(getContext().getContentResolver(),uri);

        return mCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        mSQLite = mHelper.getReadableDatabase();
        long InsertId;
        Uri mUri;
        int match = mUriMatcher.match(uri);
        switch (match){
            case PRODOTTI:
                InsertId = mSQLite.insert(Contract.ProdottiDataBase.TABLE_NAME,null,contentValues);
                if (InsertId > 0){
                    mUri = ContentUris.withAppendedId(Contract.URI_CONTENT,InsertId);
                }else {
                    throw new IllegalArgumentException("Errore Insert " +uri);
                }
                break;
            default:
                throw new IllegalArgumentException("Errore" +uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return mUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
