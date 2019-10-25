package com.addedfooddelivery_user.RestaurantDetails.holder;

import android.view.View;
import android.widget.TextView;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user._common.views.CustomTextView;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;
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



    public ChildViewHolders(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
 
    public void setChildText(String name){
        txtItemName.setText(name);
    }
}