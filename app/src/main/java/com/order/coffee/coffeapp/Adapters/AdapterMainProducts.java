package com.order.coffee.coffeapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.order.coffee.coffeapp.Models.ProductsMainModel;
import com.order.coffee.coffeapp.R;
import com.order.coffee.coffeapp.Utils.ClickItem;

import java.util.List;

/**
 * Created by Mauro on 03/11/2017.
 */

public class AdapterMainProducts extends RecyclerView.Adapter<AdapterMainProducts.ViewHolderProducts> {

    Context mContext;
    List<ProductsMainModel>ListElements;
    ClickItem mClickItem;

    public void setmClickItem(ClickItem mClickItem) {
        this.mClickItem = mClickItem;
    }

    public AdapterMainProducts(Context mContext, List<ProductsMainModel> listElements) {
        this.mContext = mContext;
        ListElements = listElements;
    }

    @Override
    public ViewHolderProducts onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_layout,parent,false);
        ViewHolderProducts viewHolderProducts = new ViewHolderProducts(view);
        return viewHolderProducts;
    }

    @Override
    public void onBindViewHolder(ViewHolderProducts holder, int position) {
        ProductsMainModel productsMainModel = ListElements.get(position);
        holder.mTitleProduct.setText(productsMainModel.getmTitile());
        Glide.with(mContext).load(productsMainModel.getmImage()).into(holder.mImageProduct);

    }

    @Override
    public int getItemCount() {
        return ListElements.size();
    }

    public class ViewHolderProducts extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mTitleProduct;
        ImageView mImageProduct;

        public ViewHolderProducts(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleProduct = itemView.findViewById(R.id.name_product);
            mImageProduct = itemView.findViewById(R.id.image_product);
        }


        @Override
        public void onClick(View view) {
            if (mClickItem != null){
                mClickItem.onClickItemRecycler(view,getAdapterPosition());
            }
        }
    }
}
