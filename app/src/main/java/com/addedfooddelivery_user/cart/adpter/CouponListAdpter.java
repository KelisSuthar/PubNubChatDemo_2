package com.addedfooddelivery_user.cart.adpter;

import android.app.Activity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.cart.model.CouponListData;
import com.addedfooddelivery_user.common.views.CustomTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CouponListAdpter extends RecyclerView.Adapter<CouponListAdpter.ViewHolder> {
    private final OnItemClickListener listener;
    private ArrayList<CouponListData> couponListData;
    private Activity context;

    public CouponListAdpter(Activity context, ArrayList<CouponListData> notificationModelArrayList, OnItemClickListener listener) {
        this.context = context;
        this.couponListData = notificationModelArrayList;
        this.listener = listener;

    }

    // Add a list of items -- change to type used
    public void addAll(List<CouponListData> list) {
        couponListData.addAll(list);
        notifyDataSetChanged();
    }

    public void clear() {
        couponListData.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_coupon_item_listing, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CouponListData data = couponListData.get(position);
        holder.txtCouponTitle.setText(TextUtils.isEmpty(data.getCouponCode()) ? "" : data.getCouponCode());
        holder.txtApplyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCouponItemClick(position, v);
            }
        });

    }

    @Override
    public int getItemCount() {
        return couponListData.size();
    }


    public interface OnItemClickListener {
        void onCouponItemClick(int position, View view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtCouponTitle)
        CustomTextView txtCouponTitle;
        @BindView(R.id.txtApplyBtn)
        CustomTextView txtApplyBtn;

        ViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }
}
