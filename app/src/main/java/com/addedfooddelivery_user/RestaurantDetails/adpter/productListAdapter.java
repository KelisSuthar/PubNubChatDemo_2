package com.addedfooddelivery_user.RestaurantDetails.adpter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.RestaurantDetails.RestDetailsActivity;
import com.addedfooddelivery_user.RestaurantDetails.holder.ChildViewHolders;
import com.addedfooddelivery_user.RestaurantDetails.holder.ParentViewHolder;
import com.addedfooddelivery_user.RestaurantDetails.model.CategoryList;
import com.addedfooddelivery_user.RestaurantDetails.model.ParentData;
import com.addedfooddelivery_user.common.ReusedMethod;
import com.addedfooddelivery_user.common.SharedPreferenceManager;
import com.squareup.picasso.Picasso;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.models.ExpandableListPosition;

import java.util.List;

import static com.addedfooddelivery_user.common.AppConstants.IS_LOGIN;

public class productListAdapter extends ExpandableRecyclerViewAdapter<ParentViewHolder, ChildViewHolders> {

    public Activity context;
    private final OnItemClickListener listener;
    List<? extends ExpandableGroup> grupo;

    public productListAdapter(Activity context, List<? extends ExpandableGroup> groups, OnItemClickListener listener) {
        super(groups);
        this.context = context;
        this.listener = listener;
        this.grupo = groups;
    }

    @Override
    public ParentViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.row_item_parent, parent, false);

        return new ParentViewHolder(view);
    }

    @Override
    public ChildViewHolders onCreateChildViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.row_item_child, parent, false);

        return new ChildViewHolders(view);
    }

    @Override
    public void onBindChildViewHolder(ChildViewHolders holder, int flatPosition, ExpandableGroup group, int childIndex) {
        CategoryList childData = ((ParentData) group).getItems().get(childIndex);
        holder.txtItemName.setText(TextUtils.isEmpty(childData.getItemName().toString()) ? "" : childData.getItemName().toString());
        holder.txtItemPrise.setText(TextUtils.isEmpty(childData.getItemPrice().toString()) ? "" : childData.getItemPrice().toString());
        holder.txtItemDesc.setText(TextUtils.isEmpty(childData.getItemDescription().toString()) ? "" : childData.getItemDescription().toString());
        holder.tickerView.setText(childData.getItemQuantity().toString());

        if (childData.getItemImage() != null && !childData.getItemImage().equalsIgnoreCase("")) {
            Picasso.with(context)
                    .load(childData.getItemImage().toString())
                    .into(holder.imgRestItem);
        }


        if (childData.getItemQuantity() == 0) {
            holder.btnAddProduct.setVisibility(View.VISIBLE);
            holder.addItemProduct.setVisibility(View.GONE);
        } else {
            holder.btnAddProduct.setVisibility(View.GONE);
            holder.addItemProduct.setVisibility(View.VISIBLE);
        }
        holder.btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean userLogin;
                userLogin = SharedPreferenceManager.getBoolean(IS_LOGIN, false);
                if (userLogin) {
                    holder.btnAddProduct.setVisibility(View.GONE);
                    holder.addItemProduct.setVisibility(View.VISIBLE);
                    listener.onAddItemClick(flatPosition, view, 1, childData.getItemPrice(), childData.getItemID());
                } else
                    ReusedMethod.showSnackBar(context, context.getResources().getString(R.string.please_login), 1);
            }
        });
        holder.itemAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int count = Integer.parseInt(holder.tickerView.getText().toString());
                holder.tickerView.setText(String.valueOf(count + 1));
                listener.onUpdateItemClick(flatPosition, view, count + 1, childData.getItemID());
                RestDetailsActivity.txtItemCount.setText(String.valueOf(count + 1 + " Items"));
                notifyDataSetChanged();
            }
        });
        holder.itemMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(holder.tickerView.getText().toString());
                if (count == 1) {

                    RestDetailsActivity.rlCartFooter.setVisibility(View.GONE);
                } else {
                    holder.tickerView.setText(String.valueOf(count - 1));
                    listener.onUpdateItemClick(flatPosition, view, count - 1, childData.getItemID());
                    RestDetailsActivity.txtItemCount.setText(String.valueOf(count - 1 + " Items"));
                }
            }
        });
        if (childData.getItemQuantity() != 0) {
            if (!isGroupExpanded(grupo.get(childIndex))) {
                onGroupClick(expandableList.getFlattenedGroupIndex(ExpandableListPosition.obtain(0, childIndex, 0, 0)));
            }
        }

    }

    @Override
    public void onBindGroupViewHolder(ParentViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setGroupName(group);

    }

    public interface OnItemClickListener {
        void onUpdateItemClick(int position, View view, int count, int itemID);

        void onAddItemClick(int position, View view, int count, String itemPrice, int itemID);

    }

}
