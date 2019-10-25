package com.addedfooddelivery_user.RestaurantDetails.holder;

import android.view.View;
import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user._common.views.CustomTextView;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParentViewHolder extends GroupViewHolder {
    @BindView(R.id.txtItemHeader)
    CustomTextView textView_parent;
    @BindView(R.id.view_parent)
    View viewParent;

    public ParentViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }

    @Override
    public void expand() {
        super.expand();
        textView_parent.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_dropdown_black, 0);
        viewParent.setVisibility(View.VISIBLE);
    }

    @Override
    public void collapse() {
        super.collapse();
        textView_parent.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_dropup_black, 0);
        viewParent.setVisibility(View.GONE);
    }

    public void setGroupName(ExpandableGroup groupName) {
        textView_parent.setText(groupName.getTitle());
    }
}