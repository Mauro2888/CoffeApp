package com.order.coffee.coffeapp.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.order.coffee.coffeapp.DatabaseProdotti.Contract;
import com.order.coffee.coffeapp.R;



public class CursorAdapter extends android.widget.CursorAdapter  {

    public CursorAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.list_layout,viewGroup,false);
    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {



        TextView mTitle = view.findViewById(R.id.nome_layout);
        TextView mPrezzo = view.findViewById(R.id.prezzo_layout);
        TextView mCategory = view.findViewById(R.id.category);
        final TextView mQuantity = view.findViewById(R.id.quantity);
        final Button mAddQuantity = view.findViewById(R.id.addQuantity);
        Button mSubtrackQuantity = view.findViewById(R.id.subtractQuantity);

        final int[] setQuantity = {0};

        mQuantity.setText(String.valueOf(0));


        mAddQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setQuantity[0] = setQuantity[0] + 1;
                mQuantity.setText(String.valueOf(setQuantity[0]) );
            }
        });

        mSubtrackQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setQuantity[0] = setQuantity[0] - 1;
                mQuantity.setText(String.valueOf(setQuantity[0]));
                if (setQuantity[0] < 0){
                    setQuantity[0] = 0;
                    mQuantity.setText(String.valueOf(setQuantity[0]));
                }
            }
        });


        int nome = cursor.getColumnIndexOrThrow(Contract.ProdottiDataBase.COLUMN_NOME);
        int prezzo = cursor.getColumnIndexOrThrow(Contract.ProdottiDataBase.COLUMN_PREZZO);
        int category = cursor.getColumnIndexOrThrow(Contract.ProdottiDataBase.COLUMN_CATEGORY);

        String nomeProdotti = cursor.getString(nome);
        String prezzoProdotti = cursor.getString(prezzo);


        mTitle.setText(nomeProdotti);
        mPrezzo.setText(prezzoProdotti);
        mCategory.setText(cursor.getString(category));
    }
}
