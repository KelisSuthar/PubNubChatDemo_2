package com.addedfooddelivery_user.cart.holder;

import android.view.View;
import android.widget.ImageView;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.common.views.CustomTextView;
import com.robinhood.ticker.TickerView;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartChildViewHolders extends ChildViewHolder {
    @BindView(R.id.imgItemType)
    public ImageView imgItemType;
    @BindView(R.id.txtItemName)
    public CustomTextView txtItemName;
    @BindView(R.id.txtItemDesc)
    public CustomTextView txtItemDesc;
    @BindView(R.id.txtItemPrise)
    public CustomTextView txtItemPrise;
    @BindView(R.id.itemMinus)
    public ImageView itemMinus;
    @BindView(R.id.tickerView)
    public TickerView tickerView;
    @BindView(R.id.itemAdd)
    public ImageView itemAdd;


    public CartChildViewHolders(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setChildText(String name) {

    }
}