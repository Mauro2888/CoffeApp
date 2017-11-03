package com.order.coffee.coffeapp;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.order.coffee.coffeapp.DatabaseProdotti.Contract;

public class AddProductActivity extends AppCompatActivity {
    private EditText mEditNome;
    private EditText mEditPrezzo;
    private Button mAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        mEditNome = findViewById(R.id.edit_add_nome_product);
        mEditPrezzo = findViewById(R.id.edit_add_price);
        mAdd = findViewById(R.id.btn_add);
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues mContentvalues = new ContentValues();
                mContentvalues.put(Contract.ProdottiDataBase.COLUMN_NOME,mEditNome.getText().toString());
                mContentvalues.put(Contract.ProdottiDataBase.COLUMN_PREZZO,mEditPrezzo.getText().toString());
                getContentResolver().insert(Contract.URI_CONTENT,mContentvalues);
                Toast.makeText(AddProductActivity.this, "Dati inseriti", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }



}
