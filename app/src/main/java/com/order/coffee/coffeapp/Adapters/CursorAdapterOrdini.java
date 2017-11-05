package com.order.coffee.coffeapp.Adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.order.coffee.coffeapp.Database.Contract;
import com.order.coffee.coffeapp.R;


public class CursorAdapterOrdini extends android.widget.CursorAdapter  {


    public CursorAdapterOrdini(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.list_layout_ordini,viewGroup,false);
    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {


        TextView mTitle = view.findViewById(R.id.nome_layout_ordini);
        TextView mPrezzo = view.findViewById(R.id.prezzo_layout_ordini);
        final TextView mQuantity = view.findViewById(R.id.quantity_ordini);
        final Button mAddQuantity = view.findViewById(R.id.addQuantity_ordini);
        Button mSubtrackQuantity = view.findViewById(R.id.subtractQuantity_ordini);

        final ContentValues mQuantiyUpdate = new ContentValues();
        final int[] setQuantity2 = {0};

        mQuantity.setText(String.valueOf(0));


        mAddQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setQuantity2[0] = setQuantity2[0] + 1;
                mQuantity.setText(String.valueOf(setQuantity2[0]) );


            }
        });

        mSubtrackQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setQuantity2[0] = setQuantity2[0] - 1;
                mQuantity.setText(String.valueOf(setQuantity2[0]));
                if (setQuantity2[0] < 0){
                    setQuantity2[0] = 0;
                }
            }
        });


        int nome = cursor.getColumnIndexOrThrow(Contract.OrdindeDataBase.COLUMN_NOME_PRODOTTO);
        int prezzo = cursor.getColumnIndexOrThrow(Contract.OrdindeDataBase.COLUMN_PREZZO_PRODOTTO);
        int quantity = cursor.getColumnIndexOrThrow(Contract.OrdindeDataBase.COLUMN_QUANTITA_PRODOTTO);

        String nomeProdotti = cursor.getString(nome);
        String prezzoProdotti = cursor.getString(prezzo);
        String quantita = cursor.getString(quantity);


        mTitle.setText(nomeProdotti);
        mPrezzo.setText(prezzoProdotti);
        mQuantity.setText(quantita);
        setQuantity2[0] = Integer.parseInt(quantita);

    }
}
