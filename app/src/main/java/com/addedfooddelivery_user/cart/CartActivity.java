package com.addedfooddelivery_user.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.RestaurantDetails.model.ChildData;
import com.addedfooddelivery_user.RestaurantDetails.model.ParentData;
import com.addedfooddelivery_user.cart.adpter.CartProductListAdapter;
import com.addedfooddelivery_user.cart.adpter.ItemLikeListAdpter;
import com.addedfooddelivery_user.home.fragement.adpter.TrendingRestaurantListAdpter;
import com.thoughtbot.expandablerecyclerview.listeners.GroupExpandCollapseListener;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartActivity extends AppCompatActivity {
    @BindView(R.id.img_back_cart)
    ImageView imgBackCart;
    @BindView(R.id.rcyCartProductList)
    RecyclerView rcyProductCart;
    @BindView(R.id.rcyLikeItem)
    RecyclerView rcyItemLike;
    ItemLikeListAdpter itemLikeAdpter;
    CartProductListAdapter myAdapter;
    LinearLayoutManager mLayoutManagerLike;
    private ArrayList<String> itemLikeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);
        setupItemRecycleview();
        itemLikeList = new ArrayList<>();
        fillRecords();
        setRestaurantData();
    }

    private void setRestaurantData() {
        itemLikeAdpter = new ItemLikeListAdpter(CartActivity.this, itemLikeList);

        mLayoutManagerLike = new LinearLayoutManager(CartActivity.this, RecyclerView.HORIZONTAL, false);
        rcyItemLike.setLayoutManager(mLayoutManagerLike);

        rcyItemLike.setItemAnimator(new DefaultItemAnimator());
        rcyItemLike.setAdapter(itemLikeAdpter);
    }

    private void fillRecords() {
        itemLikeList.add("1");
        itemLikeList.add("2");
        itemLikeList.add("3");
        itemLikeList.add("4");
        itemLikeList.add("5");
    }

    private void setupItemRecycleview() {
        List<ParentData> list = getList();

        rcyProductCart.setLayoutManager(new LinearLayoutManager(this));

        myAdapter = new CartProductListAdapter(CartActivity.this, list);
        rcyProductCart.setAdapter(myAdapter);
        rcyProductCart.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rcyProductCart.setAdapter(myAdapter);
        myAdapter.expandAllGroups();

    }

    private List<ParentData> getList() {

        List<ParentData> list_parent = new ArrayList<>();
        List<ChildData> list_data_child = new ArrayList<>();
        List<ChildData> list_data_child1 = new ArrayList<>();

        list_data_child.add(new ChildData("First"));
        list_data_child.add(new ChildData("Second"));

        list_data_child1.add(new ChildData("Third"));
        list_data_child1.add(new ChildData("Four"));


        list_parent.add(new ParentData("Parent 1", list_data_child));
        list_parent.add(new ParentData("Parent 2", list_data_child1));


        return list_parent;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        myAdapter.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        myAdapter.onRestoreInstanceState(savedInstanceState);
    }
}
