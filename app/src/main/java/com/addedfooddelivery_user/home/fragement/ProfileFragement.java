package com.addedfooddelivery_user.home.fragement;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.addedfooddelivery_user.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileFragement extends Fragment {
    public static ProfileFragement newInstance() {
        return new ProfileFragement();
    }

    private Context context;

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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        // ReusedMethod.updateStatusBarColor((AppCompatActivity) getContext(),R.color.colorWhite,0);
        ButterKnife.bind(this, view);

        return view;
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

