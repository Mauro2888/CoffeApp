package com.order.coffee.coffeapp.Database;

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

        String CREATE_DATABASE = " CREATE TABLE IF NOT EXISTS " + Contract.ProdottiDataBase.TABLE_NAME + " ( " +
                Contract.ProdottiDataBase._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Contract.ProdottiDataBase.COLUMN_NOME + " TEXT NOT NULL, " +
                Contract.ProdottiDataBase.COLUMN_PREZZO + " INTEGER NOT NULL," +
                Contract.ProdottiDataBase.COLUMN_CATEGORY + " TEXT NOT NULL " +
                " ) ";

        String CREATE_DATABASE_ORDINI = " CREATE TABLE IF NOT EXISTS " + Contract.OrdindeDataBase.TABLE_NAME + " ( " +
                Contract.OrdindeDataBase._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Contract.OrdindeDataBase.COLUMN_NOME_PRODOTTO + " TEXT, " +
                Contract.OrdindeDataBase.COLUMN_PREZZO_PRODOTTO + " TEXT, " +
                Contract.OrdindeDataBase.COLUMN_QUANTITA + "INTEGER, " +
                Contract.OrdindeDataBase.COLUMN_ORDINE_TOTALE + " INTEGER " +
                " ) ";

        sqLiteDatabase.execSQL(CREATE_DATABASE);
        sqLiteDatabase.execSQL(CREATE_DATABASE_ORDINI);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String delete_prodotti = " DROP TABLE IF EXISTS " + Contract.ProdottiDataBase.TABLE_NAME;
        String delete_ordini = " DROP TABLE IF EXISTS " + Contract.OrdindeDataBase.TABLE_NAME;
        sqLiteDatabase.execSQL(delete_prodotti);
        sqLiteDatabase.execSQL(delete_ordini);
        onCreate(sqLiteDatabase);
    }


}
