package com.order.coffee.coffeapp.DatabaseProdotti;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Mauro on 03/11/2017.
 */

public class Contract {

    public Contract() {}

    public static final String AUTHORITY = "com.order.coffee.coffeapp";
    public static final String PATH_TABLE = "prodottiItems";
    public static final Uri BASE_URI = Uri.parse("content://" + AUTHORITY);
    public static final Uri URI_CONTENT = Uri.withAppendedPath(BASE_URI,PATH_TABLE);

    public class ProdottiDataBase implements BaseColumns{
        public final static String TABLE_NAME = "prodotti";
        public final static String COLUMN_NOME = "nome";
        public final static String COLUMN_PREZZO = "prezzo";
        public final static String COLUMN_CATEGORY = "categoria";
    }
}
