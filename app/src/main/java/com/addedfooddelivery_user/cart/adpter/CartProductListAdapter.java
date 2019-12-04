package com.addedfooddelivery_user.cart.adpter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.cart.holder.CartChildViewHolders;
import com.addedfooddelivery_user.cart.holder.CartParentViewHolder;
import com.addedfooddelivery_user.cart.model.CartDetail;
import com.addedfooddelivery_user.cart.model.ParentCartData;
import com.addedfooddelivery_user.cart.model.RestaurantItem;
import com.squareup.picasso.Picasso;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.models.ExpandableListPosition;

import java.util.ArrayList;
import java.util.List;

public class CartProductListAdapter extends ExpandableRecyclerViewAdapter<CartParentViewHolder, CartChildViewHolders> {
    List<? extends ExpandableGroup> grupo;
    public Activity context;
    ArrayList<CartDetail> cartDetails;
    private final OnItemClickListener listener;

    public CartProductListAdapter(Activity context, List<? extends ExpandableGroup> groups, ArrayList<CartDetail> restaurantDetails, OnItemClickListener listener) {
        super(groups);
        this.context = context;
        grupo = groups;
        this.cartDetails = restaurantDetails;
        this.listener = listener;
    }

    @Override
    public CartChildViewHolders onCreateChildViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.row_item_child_cart, parent, false);

        return new CartChildViewHolders(view);
    }


    @Override
    public void onBindChildViewHolder(CartChildViewHolders holder, int flatPosition, ExpandableGroup group, int childIndex) {
        RestaurantItem childData = ((ParentCartData) group).getItems().get(childIndex);
        holder.txtItemName.setText(TextUtils.isEmpty(childData.getItemName().toString()) ? "" : childData.getItemName().toString());
        holder.txtItemPrise.setText(TextUtils.isEmpty(childData.getItemPrice().toString()) ? "" : childData.getItemPrice().toString());
        holder.txtItemDesc.setText(TextUtils.isEmpty(childData.getItemDescription().toString()) ? "" : childData.getItemDescription().toString());
        holder.tickerView.setText(childData.getItemQuantity().toString());
        if (childData.getItemType().equalsIgnoreCase("Veg")) {
            holder.imgItemType.setImageResource(R.drawable.ic_veg_icons);
        } else {
            holder.imgItemType.setImageResource(R.drawable.ic_nonveg_icons);
        }
        holder.itemAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.itemAdd.setClickable(false);
                holder.itemAdd.setEnabled(false);
                int count = Integer.parseInt(holder.tickerView.getText().toString());
                // holder.tickerView.setText(String.valueOf(count + 1));
                listener.onUpdateItemClick(flatPosition, view, group.getTitle(), count + 1, childData.getItemID());

            }
        });
        holder.itemMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.itemMinus.setClickable(false);
                holder.itemMinus.setEnabled(false);
                int count = Integer.parseInt(holder.tickerView.getText().toString());
                listener.onUpdateItemClick(flatPosition, view, group.getTitle(), count - 1, childData.getItemID());
               /* if (count == 1) {
                    RestDetailsActivity.rlCartFooter.setVisibility(View.GONE);
                } else {
                    //holder.tickerView.setText(String.valueOf(count - 1));
                    listener.onUpdateItemClick(flatPosition, view, count - 1, childData.getItemID());

                }*/
            }
        });


    }

    @Override
    public CartParentViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.row_item_parent_cart, parent, false);

        return new CartParentViewHolder(view);
    }


    @Override
    public void onBindGroupViewHolder(CartParentViewHolder holder, int flatPosition, ExpandableGroup group) {
        for (int i = 0; i < cartDetails.size(); i++) {
            if (String.valueOf(cartDetails.get(i).getRestaurantID()).equalsIgnoreCase(group.getTitle())) {
                CartDetail restDetails = cartDetails.get(i);
                holder.txtResNameCart.setText(TextUtils.isEmpty(restDetails.getRestaurantName()) ? "" : restDetails.getRestaurantName().toString());
                holder.txtRestAddressCart.setText(TextUtils.isEmpty(restDetails.getRestaurantAddress()) ? "" : restDetails.getRestaurantAddress().toString());
                if (restDetails.getRestaurantImages().size() != 0) {
                    Picasso.with(context)
                            .load(restDetails.getRestaurantImages().get(0).getRestauantImage())
                            .into(holder.imgRestCart);
                }

            }
        }

    }

    public interface OnItemClickListener {
        void onUpdateItemClick(int position, View view, String restId, int count, int itemID);
    }

    //expand all views
    public void expandAllGroups() {
        for (int i = 0; i < grupo.size(); i++) {
            if (!isGroupExpanded(grupo.get(i))) {
                onGroupClick(expandableList.getFlattenedGroupIndex(ExpandableListPosition.obtain(0, i, 0, 0)));
            }
        }
    }
}
 