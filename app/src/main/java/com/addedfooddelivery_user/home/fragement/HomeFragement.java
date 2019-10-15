package com.addedfooddelivery_user.home.fragement;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.home.fragement.adpter.PopularRestaurantListAdpter;
import com.addedfooddelivery_user.home.fragement.adpter.TrendingRestaurantListAdpter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragement extends Fragment {
    @BindView(R.id.recyclerViewWeek)
    RecyclerView rcyWeek;
    @BindView(R.id.recyclerMostPopular)
    RecyclerView rcyMost;
    private Context context;
    TrendingRestaurantListAdpter adpter;
    PopularRestaurantListAdpter mAdpter;
    private ArrayList<String> trendingRestaurantList;
    private ArrayList<String> popularRestaurantList;
    LinearLayoutManager mLayoutManagerWeek;
    GridLayoutManager mLayoutManagerPopular;


    public static HomeFragement newInstance() {
        return new HomeFragement();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // ReusedMethod.updateStatusBarColor((AppCompatActivity) getContext(),R.color.colorWhite,0);
        ButterKnife.bind(this, view);
        trendingRestaurantList = new ArrayList<>();
        popularRestaurantList=new ArrayList<>();
        fillRecords();
        setRestaurantData();
        return view;
    }

    private void setRestaurantData() {
        adpter = new TrendingRestaurantListAdpter(context, trendingRestaurantList, new TrendingRestaurantListAdpter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                switch (view.getId()) {

                }
            }
        });

        mLayoutManagerWeek = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        rcyWeek.setLayoutManager(mLayoutManagerWeek);

        rcyWeek.setItemAnimator(new DefaultItemAnimator());
        rcyWeek.setAdapter(adpter);

        /*mostpopular restaurant list*/
        mAdpter = new PopularRestaurantListAdpter(context, trendingRestaurantList, new PopularRestaurantListAdpter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                switch (view.getId()) {

                }
            }
        });

        mLayoutManagerPopular = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        rcyMost.setLayoutManager(mLayoutManagerPopular);

        rcyMost.setItemAnimator(new DefaultItemAnimator());
        rcyMost.setAdapter(mAdpter);
    }

    private void fillRecords() {
        trendingRestaurantList.add("1");
        trendingRestaurantList.add("2");
        trendingRestaurantList.add("3");
        trendingRestaurantList.add("4");
        trendingRestaurantList.add("5");


        popularRestaurantList.add("1");
        popularRestaurantList.add("2");
        popularRestaurantList.add("3");
        popularRestaurantList.add("4");
        popularRestaurantList.add("5");
    }


    @Override
    public void onDetach() {
        super.onDetach();

    }

    @OnClick({})
    public void OnViewClicked(View view) {
        switch (view.getId()) {

        }

    }
}

