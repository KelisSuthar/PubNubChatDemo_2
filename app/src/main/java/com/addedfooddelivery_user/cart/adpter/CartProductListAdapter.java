package com.addedfooddelivery_user.cart.adpter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.cart.holder.CartChildViewHolders;
import com.addedfooddelivery_user.cart.holder.CartParentViewHolder;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.models.ExpandableListPosition;

import java.util.List;

public class CartProductListAdapter extends ExpandableRecyclerViewAdapter<CartParentViewHolder, CartChildViewHolders> {
    List<? extends ExpandableGroup> grupo;
    public Activity context;

    public CartProductListAdapter(Activity context, List<? extends ExpandableGroup> groups) {
        super(groups);
        this.context = context;
        grupo = groups;
    }
    @Override
    public CartChildViewHolders onCreateChildViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.row_item_child_cart, parent, false);

        return new CartChildViewHolders(view);
    }


    @Override
    public void onBindChildViewHolder(CartChildViewHolders holder, int flatPosition, ExpandableGroup group, int childIndex) {
      /*  final ChildData childData = ((ParentData) group).getItems().get(childIndex);
        final ChildData childData = ((ParentData) group).getItems().get(childIndex);

        holder.setChildText(childData.getName());
        holder.itemAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(holder.tickerView.getText().toString());
                holder.tickerView.setText(String.valueOf(count + 1));
                notifyDataSetChanged();
            }
        });
        holder.itemMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(holder.tickerView.getText().toString());
                if (count == 1) {
                    if (group.getItemCount() <= 1) {
                        if (grupo.size() <= 1) {
                            //if only one group discard cart
                            context.startActivity(new Intent(context, MainActivity.class));
                            context.overridePendingTransition(R.anim.leftto, R.anim.right);
                            context.finish();
                        } else {
                            //if only one child remove group
                            grupo.remove(childIndex);
                            notifyDataSetChanged();
                        }
                    } else {
                        //remove one item in group
                        ((ParentData) group).getItems().remove(childIndex);
                        notifyDataSetChanged();
                    }
                } else {
                    holder.tickerView.setText(String.valueOf(count - 1));
                }
            }
        });*/
    }

    @Override
    public CartParentViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.row_item_parent_cart, parent, false);

        return new CartParentViewHolder(view);
    }


    @Override
    public void onBindGroupViewHolder(CartParentViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setGroupName(group);

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
 