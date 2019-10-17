package com.addedfooddelivery_user.home.fragement.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.addedfooddelivery_user.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddressListAdpter extends RecyclerView.Adapter<AddressListAdpter.ViewHolder> {
    private final OnItemClickListener listener;
    private ArrayList<String> listData;
    private Context context;

    public AddressListAdpter(Context context, ArrayList<String> notificationModelArrayList, OnItemClickListener listener) {
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_search_listing, parent, false);
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


        ViewHolder(@NonNull View view, final OnItemClickListener mListener) {
            super(view);
            ButterKnife.bind(this, view);

        }

        @OnClick()
        void clickEvent(View view) {
            switch (view.getId()) {

            }
        }

    }
}
