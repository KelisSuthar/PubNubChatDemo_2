package com.addedfooddelivery_user.home_search.adpter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.common.views.CustomTextView;
import com.addedfooddelivery_user.home_search.model.CategoryData;
import com.addedfooddelivery_user.restaurantList.RestaurantListActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SearchListAdpter extends RecyclerView.Adapter<SearchListAdpter.ViewHolder> implements Filterable {

    private ArrayList<CategoryData> categoryData = new ArrayList<>();
    private ArrayList<CategoryData> tempCategoryData = new ArrayList<>();
    private Activity context;


    public SearchListAdpter(Activity context, ArrayList<CategoryData> notificationModelArrayList) {
        this.context = context;
        this.categoryData = notificationModelArrayList;
        this.tempCategoryData = notificationModelArrayList;
    }

    // Add a list of items -- change to type used
    public void addAll(List<CategoryData> list) {
        categoryData.addAll(list);
        notifyDataSetChanged();
    }

    public void clear() {
        categoryData.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_search_listing, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtSearchCategory.setText(tempCategoryData.get(position).getFoodCategoryName());
        Picasso.with(context)
                .load(tempCategoryData.get(position).getFoodCategoryImage())
                .into(holder.imgCategory);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // listener.onItemClick(getAdapterPosition(), view);
                context.startActivity(new Intent(context, RestaurantListActivity.class).putExtra("foodCategoryName", categoryData.get(position).getFoodCategoryName()));
                context.overridePendingTransition(R.anim.rightto, R.anim.left);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tempCategoryData.size();
    }


    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtSearchCategory)
        CustomTextView txtSearchCategory;
        @BindView(R.id.imgCategory)
        ImageView imgCategory;

        ViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);

        }

    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    tempCategoryData = categoryData;
                } else {
                    ArrayList<CategoryData> filteredList = new ArrayList<>();
                    for (CategoryData FriendModel : categoryData) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (FriendModel.getFoodCategoryName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(FriendModel);
                        }
                    }

                    tempCategoryData = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = tempCategoryData;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                tempCategoryData = (ArrayList<CategoryData>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}

