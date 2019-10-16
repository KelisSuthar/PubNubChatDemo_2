package com.addedfooddelivery_user.home.fragement;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.home.fragement.adpter.PopularRestaurantListAdpter;
import com.addedfooddelivery_user.home.fragement.adpter.SearchListAdpter;
import com.addedfooddelivery_user.home.fragement.adpter.TrendingRestaurantListAdpter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchFragement extends Fragment {
    @BindView(R.id.rcy_search)
    RecyclerView rcySearch;

    private Context context;
    SearchListAdpter mAdpter;
    private ArrayList<String> searchList;
    LinearLayoutManager mLayoutManagerSearch;


    public static SearchFragement newInstance() {
        return new SearchFragement();
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
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        // ReusedMethod.updateStatusBarColor((AppCompatActivity) getContext(),R.color.colorWhite,0);
        ButterKnife.bind(this, view);


        searchList = new ArrayList<>();

        fillRecords();
        setRestaurantData();
        return view;
    }

    private void setRestaurantData() {
        mAdpter = new SearchListAdpter(context, searchList, new SearchListAdpter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                switch (view.getId()) {

                }
            }
        });

        mLayoutManagerSearch = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        rcySearch.setLayoutManager(mLayoutManagerSearch);

        rcySearch.setItemAnimator(new DefaultItemAnimator());
        rcySearch.setAdapter(mAdpter);
    }

    private void fillRecords() {
        searchList.add("1");
        searchList.add("2");
        searchList.add("3");
        searchList.add("4");
        searchList.add("5");
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

