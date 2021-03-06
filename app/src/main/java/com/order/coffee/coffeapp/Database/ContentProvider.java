package com.order.coffee.coffeapp.Database;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

    public static final int ORDINI_ID = 200;
    public static final int ORDINI = 201;

    static {
        mUriMatcher.addURI(Contract.AUTHORITY,Contract.PATH_TABLE + "/#",PRODOTTI_ID);
        mUriMatcher.addURI(Contract.AUTHORITY,Contract.PATH_TABLE,PRODOTTI);

        mUriMatcher.addURI(Contract.AUTHORITY,Contract.PATH_TABLE_ORDINI + "/#",ORDINI_ID);
        mUriMatcher.addURI(Contract.AUTHORITY,Contract.PATH_TABLE_ORDINI,ORDINI);
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

            case ORDINI:
                mCursor = mSQLite.query(Contract.OrdindeDataBase.TABLE_NAME,projector,selection,selectionArgs,null,null,sortBy);
                break;
            case ORDINI_ID:
                selection = Contract.OrdindeDataBase._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                mCursor = mSQLite.query(Contract.OrdindeDataBase.TABLE_NAME,projector,selection,selectionArgs,null,null,sortBy);
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
            case ORDINI:
                InsertId = mSQLite.insert(Contract.OrdindeDataBase.TABLE_NAME,null,contentValues);
                if (InsertId > 0){
                    mUri = ContentUris.withAppendedId(Contract.URI_CONTENT_ORDINI,InsertId);
                }else {
                    throw new IllegalArgumentException("Errore Insert Ordini " + uri);
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
        mSQLite = mHelper.getWritableDatabase();
        int delete;
        int match = mUriMatcher.match(uri);
        switch (match){
            case PRODOTTI:
                delete = mSQLite.delete(Contract.ProdottiDataBase.TABLE_NAME,null,null);
                break;
            case PRODOTTI_ID:
                selection = Contract.ProdottiDataBase._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                delete = mSQLite.delete(Contract.ProdottiDataBase.TABLE_NAME,selection,selectionArgs);
                break;

            case ORDINI:
                delete = mSQLite.delete(Contract.OrdindeDataBase.TABLE_NAME,null,null);
                break;
            case ORDINI_ID:
                selection = Contract.OrdindeDataBase._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                delete = mSQLite.delete(Contract.OrdindeDataBase.TABLE_NAME,selection,selectionArgs);
                break;
                default:
                    throw new IllegalArgumentException("Errore delete " + uri);
        }

        getContext().getContentResolver().notifyChange(uri,null);
        return delete;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        mSQLite = mHelper.getWritableDatabase();
        int update = 0;
        int match = mUriMatcher.match(uri);
        switch (match){
            case PRODOTTI:
                update = mSQLite.update(Contract.PATH_TABLE,contentValues,null,null);
                break;
            case PRODOTTI_ID:
                selection = Contract.ProdottiDataBase._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                update = mSQLite.update(Contract.PATH_TABLE,contentValues,selection,selectionArgs);
                break;
            case ORDINI:
                update = mSQLite.update(Contract.PATH_TABLE_ORDINI,contentValues,null,null);
                break;
            case ORDINI_ID:
                selection = Contract.ProdottiDataBase._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                update = mSQLite.update(Contract.PATH_TABLE_ORDINI,contentValues,selection,selectionArgs);
                break;
            
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return update;
    }
}
