package com.addedfooddelivery_user.orderTracking.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user._common.views.CustomTextView;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class OrderTrackingItemListAdpter extends RecyclerView.Adapter<OrderTrackingItemListAdpter.ViewHolder> {
    private ArrayList<String> listData;
    private Context context;

    public OrderTrackingItemListAdpter(Context context, ArrayList<String> notificationModelArrayList) {
        this.context = context;
        this.listData = notificationModelArrayList;

    }

    // Add a list of items -- change to type used
    public void addAll(List<String> list) {
        listData.addAll(list);
        notifyDataSetChanged();
    }

    public void clear() {
        listData.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_order_tracking_item_listing, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }


    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgRestaurantTracking)
        PorterShapeImageView imgRestaurantTracking;
        @BindView(R.id.txtResNameTracking)
        CustomTextView txtResNameTracking;
        @BindView(R.id.txtItemNameTracking)
        CustomTextView txtItemNameTracking;
        @BindView(R.id.txtCall)
        CustomTextView txtCall;

        ViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);


        }

    }
}
