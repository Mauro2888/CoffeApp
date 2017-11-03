package com.order.coffee.coffeapp.Models;

import android.media.Image;

/**
 * Created by Mauro on 03/11/2017.
 */

public class ProductsMainModel {

    String mTitile;
    int mImage;

    public ProductsMainModel(String mTitile, int mImage) {
        this.mTitile = mTitile;
        this.mImage = mImage;
    }

    public String getmTitile() {
        return mTitile;
    }

    public void setmTitile(String mTitile) {
        this.mTitile = mTitile;
    }

    public int getmImage() {
        return mImage;
    }

    public void setmImage(int mImage) {
        this.mImage = mImage;
    }
}
