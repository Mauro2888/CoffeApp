package com.order.coffee.coffeapp;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.order.coffee.coffeapp.Adapters.AdapterMainProducts;
import com.order.coffee.coffeapp.Models.ProductsMainModel;
import com.order.coffee.coffeapp.Utils.ClickItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ClickItem {
    private RecyclerView mRecyclerMain;
    private RecyclerView.LayoutManager mLayoutManger;
    private AdapterMainProducts mAdapterProducts;
    private ArrayList<ProductsMainModel> mListProducts;
    FloatingActionButton mAddProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAddProduct = findViewById(R.id.Fab_add_product);

        mListProducts = new ArrayList<>();
        mListProducts.add(new ProductsMainModel("Caffetteria",R.drawable.ic_coffe));
        mListProducts.add(new ProductsMainModel("Alcolici",R.drawable.ic_alcolici));
        mListProducts.add(new ProductsMainModel("Analcolici",R.drawable.ic_analcolici));
        mListProducts.add(new ProductsMainModel("Alimenti",R.drawable.ic_food));

        mRecyclerMain = findViewById(R.id.recycler_main);
        mLayoutManger = new GridLayoutManager(this,calculateNoOfColumns(getBaseContext()));
        mRecyclerMain.setHasFixedSize(true);
        mRecyclerMain.setLayoutManager(mLayoutManger);
        mAdapterProducts = new AdapterMainProducts(getApplicationContext(),mListProducts);
        mRecyclerMain.setAdapter(mAdapterProducts);
        mAdapterProducts.setmClickItem(this);

    }

    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 180);
        return noOfColumns >= 2 ? noOfColumns : 2;
    }

    @Override
    public void onClickItemRecycler(View view, int pos) {
        switch (pos){
            case 0:
                Intent caffetteriaActivity = new Intent(MainActivity.this,CaffetteriaActivity.class);
                startActivity(caffetteriaActivity);
                break;
            case 1:
                Intent alcoliciActivity = new Intent(MainActivity.this,AlcoliciActivity.class);
                startActivity(alcoliciActivity);
                break;
            case 2:
            Intent analcoliciActivity = new Intent(MainActivity.this,AnalcoliciActivity.class);
            startActivity(analcoliciActivity);
                break;
            case 3:
                Intent alimentiActvity = new Intent(MainActivity.this,AlimentiActivity.class);
                startActivity(alimentiActvity);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:

        }
        return super.onOptionsItemSelected(item);
    }

    public void AddProduct(View view) {
        Intent addProduct = new Intent(MainActivity.this,AddProductActivity.class);
        startActivity(addProduct);
    }
}
