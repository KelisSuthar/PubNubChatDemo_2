package com.addedfooddelivery_user.cart.adpter;

import android.app.Activity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.cart.model.MayLike;
import com.addedfooddelivery_user.common.views.CustomTextView;
import com.github.siyamed.shapeimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ItemLikeListAdpter extends RecyclerView.Adapter<ItemLikeListAdpter.ViewHolder> {
    private final OnItemClickListener listener;
    private ArrayList<MayLike> mayLikes;
    private Activity context;

    public ItemLikeListAdpter(Activity context, ArrayList<MayLike> notificationModelArrayList, OnItemClickListener listener) {
        this.context = context;
        this.mayLikes = notificationModelArrayList;
        this.listener = listener;

    }

    // Add a list of items -- change to type used
    public void addAll(List<MayLike> list) {
        mayLikes.addAll(list);
        notifyDataSetChanged();
    }

    public void clear() {
        mayLikes.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_like_item_listing, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MayLike mayLike = mayLikes.get(position);
        if (mayLike.getItemImage() != null && !mayLike.getItemImage().equalsIgnoreCase("")) {
            Picasso.with(context)
                    .load(mayLike.getItemImage())
                    .into(holder.imgRestLike);
        }
        holder.txtRestNameLike.setText(TextUtils.isEmpty(mayLike.getItemName().toString()) ? "" : mayLike.getItemName().toString());
        holder.txtPriseLike.setText(TextUtils.isEmpty(mayLike.getItemPrice().toString()) ? "" : mayLike.getItemPrice().toString());
        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onAddItemClick(position, v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mayLikes.size();
    }


    public interface OnItemClickListener {
        void onAddItemClick(int position, View view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.linearEventImages)
        LinearLayout linearEventImages;
        @BindView(R.id.imgRestLike)
        RoundedImageView imgRestLike;
        @BindView(R.id.linearEvent)
        LinearLayout linearEvent;
        @BindView(R.id.txtRestNameLike)
        CustomTextView txtRestNameLike;
        @BindView(R.id.linearEventDetail)
        RelativeLayout linearEventDetail;
        @BindView(R.id.txtPriseLike)
        CustomTextView txtPriseLike;
        @BindView(R.id.btnAdd)
        CustomTextView btnAdd;

        ViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }
}
