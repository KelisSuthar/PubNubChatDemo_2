package com.addedfooddelivery_user.orderHistory.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user._common.SimpleDividerItemDecoration;
import com.addedfooddelivery_user._common.views.CustomTextView;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class OrderHistoryListAdpter extends RecyclerView.Adapter<OrderHistoryListAdpter.ViewHolder> {
    private final OnItemClickListener listener;
    private ArrayList<String> listData;
    private Context context;
    int pos;

    public OrderHistoryListAdpter(Context context, ArrayList<String> notificationModelArrayList, OnItemClickListener listener) {
        this.context = context;
        this.listData = notificationModelArrayList;
        this.listener = listener;
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_order_history_listing, parent, false);
        return new ViewHolder(v, listener);
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
        @BindView(R.id.order_label)
        CustomTextView orderLabel;
        @BindView(R.id.txtOrderDate)
        CustomTextView txtOrderDate;
        @BindView(R.id.txtOrderPrise)
        CustomTextView txtOrderPrise;
        @BindView(R.id.rcyOrderItem)
        RecyclerView rcyOrderItem;
        @BindView(R.id.txtPayementstatus)
        CustomTextView txtPayementstatus;
        @BindView(R.id.txtRepetOrder)
        CustomTextView txtRepetOrder;
        @BindView(R.id.materialCardHistory)
        MaterialCardView cardViewHistory;

        LinearLayoutManager mLayoutManagerOrderItem;
        private ArrayList<String> orderOrderItemList;
        OrderItemListAdpter adpter;

        ViewHolder(@NonNull View view, final OnItemClickListener mListener) {
            super(view);
            ButterKnife.bind(this, view);

            orderOrderItemList = new ArrayList<>();
            fillRecords(orderOrderItemList);

            adpter = new OrderItemListAdpter(context, orderOrderItemList);
            mLayoutManagerOrderItem = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
            rcyOrderItem.setLayoutManager(mLayoutManagerOrderItem);
            rcyOrderItem.addItemDecoration(new SimpleDividerItemDecoration(context));

            rcyOrderItem.setItemAnimator(new DefaultItemAnimator());
            rcyOrderItem.setAdapter(adpter);

        }

    private void fillRecords(ArrayList<String> orderOrderItemList) {
        orderOrderItemList.add("1");
        orderOrderItemList.add("2");
        orderOrderItemList.add("3");
    }
        @OnClick(R.id.materialCardHistory)
        void clickEvent(View view) {
            switch (view.getId()) {
                case R.id.materialCardHistory:
                if (pos != RecyclerView.NO_POSITION) {
                    if (listener != null) {
                        listener.onItemClick(getAdapterPosition(), view);
                    }
                }
                break;
            }

        }

    }

}
