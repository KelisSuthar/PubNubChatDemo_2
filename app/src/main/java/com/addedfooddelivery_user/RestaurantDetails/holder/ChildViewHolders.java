package com.addedfooddelivery_user.RestaurantDetails.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.common.views.CustomTextView;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;
import com.robinhood.ticker.TickerView;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChildViewHolders extends ChildViewHolder {
    @BindView(R.id.imgRestItem)
    public PorterShapeImageView imgRestItem;
    @BindView(R.id.txtItemName)
    public CustomTextView txtItemName;
    @BindView(R.id.txtItemPrise)
    public CustomTextView txtItemPrise;
    @BindView(R.id.txtItemDesc)
    public CustomTextView txtItemDesc;
    @BindView(R.id.btnAddProduct)
    public CustomTextView btnAddProduct;
    @BindView(R.id.add_item)
    public LinearLayout addItemProduct;
    @BindView(R.id.itemMinus)
    public ImageView itemMinus;
    @BindView(R.id.tickerView)
    public TickerView tickerView;
    @BindView(R.id.itemAdd)
    public ImageView itemAdd;

    public ChildViewHolders(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


}