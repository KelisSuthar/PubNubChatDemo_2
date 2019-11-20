package com.addedfooddelivery_user.home_profile.adpter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.common.views.CustomTextView;
import com.addedfooddelivery_user.home_profile.model.ProfileItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ProfileListAdpter extends RecyclerView.Adapter<ProfileListAdpter.ViewHolder> {

    private ArrayList<ProfileItem> listData;
    private Activity context;
    private static int lastCheckedPos = -1;

    public ProfileListAdpter(Activity context, ArrayList<ProfileItem> notificationModelArrayList) {
        this.context = context;
        this.listData = notificationModelArrayList;

    }

    // Add a list of items -- change to type used
    public void addAll(List<ProfileItem> list) {
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_profile_listing, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.profileItemHead.setText(listData.get(position).getItemTitle());
        holder.profileItemSub.setText(listData.get(position).getItemSubTitle());
        if (position == lastCheckedPos) {
            holder.mainContainer.setBackgroundResource(R.drawable.profile_select_bg);
            holder.profileItemIcon.setImageDrawable(listData.get(position).getActiveIcon());
            holder.profileItemArrow.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_fillrightarrow_orange));
            holder.profileItemHead.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            holder.profileItemSub.setTextColor(context.getResources().getColor(R.color.black));

           /* context.startActivity(new Intent(context,listData.get(position).getIntentClass()));
            context.overridePendingTransition(R.anim.rightto, R.anim.left);*/
        } else {
            holder.mainContainer.setBackgroundResource(R.drawable.profile_unselect_bg);
            holder.profileItemIcon.setImageDrawable(listData.get(position).getInActiveIcon());
            holder.profileItemArrow.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_fillrightarrow_gray));
            holder.profileItemHead.setTextColor(context.getResources().getColor(R.color.black));
            holder.profileItemSub.setTextColor(context.getResources().getColor(R.color.text_gray_1));
        }
        /*holder.mainContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context,listData.get(position).getIntentClass()));
                context.overridePendingTransition(R.anim.rightto, R.anim.left);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mainContainer)
        LinearLayout mainContainer;
        @BindView(R.id.profile_item_icon)
        ImageView profileItemIcon;
        @BindView(R.id.profile_item_arrow)
        ImageView profileItemArrow;
        @BindView(R.id.profile_item_head)
        CustomTextView profileItemHead;
        @BindView(R.id.profile_item_sub)
        CustomTextView profileItemSub;


        ViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);

        }

        @OnClick(R.id.mainContainer)
        void clickEvent(View view) {
            switch (view.getId()) {
                case R.id.mainContainer:
                    lastCheckedPos = getAdapterPosition();
                    notifyDataSetChanged();
                    context.startActivity(new Intent(context, listData.get(lastCheckedPos).getIntentClass()));
                    context.overridePendingTransition(R.anim.rightto, R.anim.left);
                    break;
            }
        }

    }
}
