package com.order.coffee.coffeapp.DatabaseProdotti;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mauro on 03/11/2017.
 */

public class Helper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "prodotti.db";
    public static final int DATABASE_VERSION = 1;

    public Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_DATABASE = " CREATE TABLE " + Contract.ProdottiDataBase.TABLE_NAME + " ( " +
                Contract.ProdottiDataBase._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Contract.ProdottiDataBase.COLUMN_NOME + " TEXT NOT NULL, " +
                Contract.ProdottiDataBase.COLUMN_PREZZO + " INTEGER NOT NULL" + " ) ";
        sqLiteDatabase.execSQL(CREATE_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DeleteData());
    }

    public String DeleteData(){
        String delete = " DROP TABLE IF EXISTS " + Contract.ProdottiDataBase.TABLE_NAME;
        return delete;
    }
}
