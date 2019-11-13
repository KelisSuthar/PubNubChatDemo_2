package com.addedfooddelivery_user.cart.holder;

import android.view.View;
import android.widget.ImageView;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.common.views.CustomTextView;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartParentViewHolder extends GroupViewHolder {
    @BindView(R.id.imgRestCart)
    public PorterShapeImageView imgRestCart;
    @BindView(R.id.txtResNameCart)
    public CustomTextView txtResNameCart;
    @BindView(R.id.txtRestAddressCart)
    public CustomTextView txtRestAddressCart;
    @BindView(R.id.imgDownCart)
    public ImageView imgDownCart;
    @BindView(R.id.view_parent)
    public View viewParent;


    public CartParentViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        expand();
    }

    @Override
    public void expand() {
        super.expand();
        imgDownCart.setImageResource(R.drawable.ic_dropdown_black);
        // viewParent.setVisibility(View.VISIBLE);
    }

    @Override
    public void collapse() {
        super.collapse();
        imgDownCart.setImageResource(R.drawable.ic_dropup_black);

        // viewParent.setVisibility(View.GONE);
    }

    public void setGroupName(ExpandableGroup groupName) {
        // textView_parent.setText(groupName.getTitle());
    }
}