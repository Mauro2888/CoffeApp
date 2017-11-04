package com.order.coffee.coffeapp;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.order.coffee.coffeapp.Database.Contract;

import java.util.ArrayList;

public class AddProductActivity extends AppCompatActivity {
    private EditText mEditNome;
    private EditText mEditPrezzo;
    private Button mAdd;
    private Spinner mSpinnerCategory;
    private ArrayList<String>mCategoryList;
    private ArrayAdapter<String>mAdapterSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        mEditNome = findViewById(R.id.edit_add_nome_product);
        mEditPrezzo = findViewById(R.id.edit_add_price);
        mSpinnerCategory = findViewById(R.id.spinnerCategory);

        mCategoryList = new ArrayList<>();
        mCategoryList.add("Caffetteria");
        mCategoryList.add("Alcolici");
        mCategoryList.add("Analcolici");
        mCategoryList.add("Alimenti");
        mAdapterSpinner = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,mCategoryList);
        mSpinnerCategory.setAdapter(mAdapterSpinner);

        mSpinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selected = (String) adapterView.getItemAtPosition(i);
                SharedPreferences mSharedCategory = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor edit = mSharedCategory.edit();
                edit.putString("Item",selected);
                edit.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        mAdd = findViewById(R.id.btn_add);
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences getPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String get = getPref.getString("Item",null);
                ContentValues mContentvalues = new ContentValues();
                mContentvalues.put(Contract.ProdottiDataBase.COLUMN_NOME,mEditNome.getText().toString());
                mContentvalues.put(Contract.ProdottiDataBase.COLUMN_PREZZO,mEditPrezzo.getText().toString());
                mContentvalues.put(Contract.ProdottiDataBase.COLUMN_CATEGORY,get);
                getContentResolver().insert(Contract.URI_CONTENT,mContentvalues);
                Toast.makeText(AddProductActivity.this, "Dati inseriti", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }



}
