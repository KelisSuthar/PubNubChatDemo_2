package com.addedfooddelivery_user.cart.adpter;

import android.app.Activity;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ItemLikeListAdpter extends RecyclerView.Adapter<ItemLikeListAdpter.ViewHolder> {
    private ArrayList<MayLike> mayLikes;
    private Activity context;

    public ItemLikeListAdpter(Activity context, ArrayList<MayLike> notificationModelArrayList) {
        this.context = context;
        this.mayLikes = notificationModelArrayList;

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

    }

    @Override
    public int getItemCount() {
        return mayLikes.size();
    }


    public interface OnItemClickListener {
        void onItemClick(int position, View view);
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
