package com.addedfooddelivery_user.RestaurantDetails.adpter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.RestaurantDetails.RestDetailsActivity;
import com.addedfooddelivery_user.RestaurantDetails.holder.ChildViewHolders;
import com.addedfooddelivery_user.RestaurantDetails.holder.ParentViewHolder;
import com.addedfooddelivery_user.RestaurantDetails.model.ChildData;
import com.addedfooddelivery_user.RestaurantDetails.model.ParentData;
import com.addedfooddelivery_user.common.ReusedMethod;
import com.addedfooddelivery_user.common.SharedPreferenceManager;
import com.addedfooddelivery_user.home.MainActivity;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

import static com.addedfooddelivery_user.common.AppConstants.IS_LOGIN;

public class productListAdapter extends ExpandableRecyclerViewAdapter<ParentViewHolder, ChildViewHolders> {

    public Activity context;

    public productListAdapter(Activity context, List<? extends ExpandableGroup> groups) {
        super(groups);
        this.context = context;
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
        final ChildData childData = ((ParentData) group).getItems().get(childIndex);
        holder.setChildText(childData.getName());
        holder.btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean userLogin;
                userLogin = SharedPreferenceManager.getBoolean(IS_LOGIN, false);
                if(userLogin) {
                    holder.btnAddProduct.setVisibility(View.GONE);
                    holder.addItemProduct.setVisibility(View.VISIBLE);
                    RestDetailsActivity.rlCartFooter.setVisibility(View.VISIBLE);
                    RestDetailsActivity.txtItemCount.setText(String.valueOf("1" + " Items"));
                }else {
                    ReusedMethod.showSnackBar(context,context.getResources().getString(R.string.please_login),1);
                }
            }
        });
        holder.itemAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int count = Integer.parseInt(holder.tickerView.getText().toString());
                holder.tickerView.setText(String.valueOf(count + 1));

                RestDetailsActivity.txtItemCount.setText(String.valueOf(count + 1+" Items"));
                notifyDataSetChanged();
            }
        });
        holder.itemMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(holder.tickerView.getText().toString());
                if (count == 1) {
                    holder.btnAddProduct.setVisibility(View.VISIBLE);
                    holder.addItemProduct.setVisibility(View.GONE);
                    RestDetailsActivity.rlCartFooter.setVisibility(View.GONE);
                } else {
                    holder.tickerView.setText(String.valueOf(count - 1));
                    RestDetailsActivity.txtItemCount.setText(String.valueOf(count - 1+" Items"));
                }
            }
        });

    }

    @Override
    public void onBindGroupViewHolder(ParentViewHolder holder, int flatPosition, ExpandableGroup group) {

        holder.setGroupName(group);

    }
}
 