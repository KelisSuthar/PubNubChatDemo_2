package com.addedfooddelivery_user.home_deliverylist.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.common.views.CustomTextView;
import com.addedfooddelivery_user.home_deliverylist.model.ListAddData;
import com.addedfooddelivery_user.home_deliverylist.model.ListAddResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddressListAdpter extends RecyclerView.Adapter<AddressListAdpter.ViewHolder> {
    private final OnItemClickListener listener;
    private ArrayList<ListAddData> addressListData;
    private Context context;

    public AddressListAdpter(Context context, ArrayList<ListAddData> notificationModelArrayList, OnItemClickListener listener) {
        this.context = context;
        this.addressListData = notificationModelArrayList;
        this.listener = listener;
    }

    // Add a list of items -- change to type used
    public void addAll(List<ListAddData> list) {
        addressListData.addAll(list);
        notifyDataSetChanged();
    }

    public void clear() {
        addressListData.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_address_listing, parent, false);
        return new ViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (addressListData.get(position).getAdderessType().equalsIgnoreCase(context.getResources().getString(R.string.work))) {
            holder.imgLocationType.setImageResource(R.drawable.ic_location_workgray);
            holder.txtLocationTitle.setText(context.getResources().getString(R.string.work));
        } else if (addressListData.get(position).getAdderessType().equalsIgnoreCase(context.getResources().getString(R.string.home))) {
            holder.imgLocationType.setImageResource(R.drawable.ic_location_homegray);
            holder.txtLocationTitle.setText(context.getResources().getString(R.string.home));
        } else {
            holder.imgLocationType.setImageResource(R.drawable.ic_location_othergray);
            holder.txtLocationTitle.setText(context.getResources().getString(R.string.other));
        }
        holder.txtLocationAdd.setText(addressListData.get(position).getAdderess());
    }

    @Override
    public int getItemCount() {
        return addressListData.size();
    }


    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.imgLocationType)
        ImageView imgLocationType;
        @BindView(R.id.txtLocationTitle)
        CustomTextView txtLocationTitle;
        @BindView(R.id.txtLocationAdd)
        CustomTextView txtLocationAdd;


        ViewHolder(@NonNull View view, final OnItemClickListener mListener) {
            super(view);
            ButterKnife.bind(this, view);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            listener.onItemClick(getAdapterPosition(),view);
        }
    }
}
