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
import com.addedfooddelivery_user.home.fragement.adpter.ProfileListAdpter;
import com.addedfooddelivery_user.home.fragement.adpter.SearchListAdpter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileFragement extends Fragment {

    @BindView(R.id.rcy_profile_list)
    RecyclerView rcyProfileList;
    private Context context;
    ProfileListAdpter mAdpter;
    private ArrayList<String> profileList;
    LinearLayoutManager mLayoutManagerProfile;
    public static ProfileFragement newInstance() {
        return new ProfileFragement();
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

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);

        profileList = new ArrayList<>();

        fillRecords();
        setRestaurantData();
        return view;
    }

    private void setRestaurantData() {
        mAdpter = new ProfileListAdpter(context, profileList, new ProfileListAdpter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                switch (view.getId()) {

                }
            }
        });

        mLayoutManagerProfile = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        rcyProfileList.setLayoutManager(mLayoutManagerProfile);

        rcyProfileList.setItemAnimator(new DefaultItemAnimator());
        rcyProfileList.setAdapter(mAdpter);
    }

    private void fillRecords() {

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

