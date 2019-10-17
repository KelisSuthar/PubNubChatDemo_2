package com.addedfooddelivery_user.home.fragement;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user._common.views.CustomTextView;
import com.addedfooddelivery_user.home.MainActivity;
import com.addedfooddelivery_user.home.fragement.adpter.ProfileListAdpter;
import com.addedfooddelivery_user.home.fragement.adpter.SearchListAdpter;
import com.addedfooddelivery_user.home.model.ProfileItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileFragement extends Fragment {

    @BindView(R.id.rcy_profile_list)
    RecyclerView rcyProfileList;
    private Context context;
    ProfileListAdpter mAdpter;
    private ArrayList<ProfileItem> profileList;
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
        setRestaurantData();
        fillRecords();

        return view;
    }

    private void setRestaurantData() {
        mAdpter = new ProfileListAdpter(getActivity(), profileList);

        mLayoutManagerProfile = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        rcyProfileList.setLayoutManager(mLayoutManagerProfile);

        rcyProfileList.setItemAnimator(new DefaultItemAnimator());
        rcyProfileList.setAdapter(mAdpter);
    }

    private void fillRecords() {
        profileList.clear();

        profileList.add(new ProfileItem(context.getResources().getString(R.string.my_profile),
                context.getResources().getString(R.string.demo_uname),
                context.getResources().getDrawable(R.drawable.ic_editprofile_orange),
                context.getResources().getDrawable(R.drawable.ic_editprofile_gray),
                MainActivity.class));

        profileList.add(new ProfileItem(context.getResources().getString(R.string.manage_address),
                context.getResources().getString(R.string.change_your_delivery_address),
                context.getResources().getDrawable(R.drawable.ic_addressmanage_orange),
                context.getResources().getDrawable(R.drawable.ic_addressmanage_gray),
                MainActivity.class));

        profileList.add(new ProfileItem(context.getResources().getString(R.string.help),
                context.getResources().getString(R.string.help_added_com),
                context.getResources().getDrawable(R.drawable.ic_needhelp_orange),
                context.getResources().getDrawable(R.drawable.ic_needhelp_gray),
                MainActivity.class));

        profileList.add(new ProfileItem(context.getResources().getString(R.string.gift_card),
                context.getResources().getString(R.string.not_available_gift_card),
                context.getResources().getDrawable(R.drawable.ic_giftcard_orange),
                context.getResources().getDrawable(R.drawable.ic_giftcard_gray),
                MainActivity.class));

        profileList.add(new ProfileItem(context.getResources().getString(R.string.past_order),
                context.getResources().getString(R.string.orders_10),
                context.getResources().getDrawable(R.drawable.ic_pastorderlist_orange),
                context.getResources().getDrawable(R.drawable.ic_pastorderlist_gray),
                MainActivity.class));

        profileList.add(new ProfileItem(context.getResources().getString(R.string.payment),
                context.getResources().getString(R.string.update_payment_option),
                context.getResources().getDrawable(R.drawable.ic_payment_orange),
                context.getResources().getDrawable(R.drawable.ic_payment_gray_),
                MainActivity.class));

        profileList.add(new ProfileItem(context.getResources().getString(R.string.delivery_with_added),
                context.getResources().getString(R.string.check_status),
                context.getResources().getDrawable(R.drawable.ic_take_away_orange),
                context.getResources().getDrawable(R.drawable.ic_take_away_gray),
                MainActivity.class));

        profileList.add(new ProfileItem(context.getResources().getString(R.string.get),
                context.getResources().getString(R.string.add_more_in_wallet),
                context.getResources().getDrawable(R.drawable.ic_getdolor_orange),
                context.getResources().getDrawable(R.drawable.ic_getdolor_gray),
                MainActivity.class));

        profileList.add(new ProfileItem(context.getResources().getString(R.string.settings),
                context.getResources().getString(R.string.notification_restaurants_profile_status),
                context.getResources().getDrawable(R.drawable.ic_setting_orange),
                context.getResources().getDrawable(R.drawable.ic_setting_gray),
                MainActivity.class));


        mAdpter.notifyDataSetChanged();

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

