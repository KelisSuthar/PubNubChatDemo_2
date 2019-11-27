package com.addedfooddelivery_user.home_search;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.common.CustomeToast;
import com.addedfooddelivery_user.common.views.CustomEditText;
import com.addedfooddelivery_user.home.MainActivity;
import com.addedfooddelivery_user.home_search.adpter.SearchListAdpter;
import com.addedfooddelivery_user.home_search.api.SearchConstructor;
import com.addedfooddelivery_user.home_search.api.SearchPresenter;
import com.addedfooddelivery_user.home_search.model.CategoryData;
import com.addedfooddelivery_user.home_search.model.CategoryResponse;
import com.addedfooddelivery_user.restaurantList.RestaurantListActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.addedfooddelivery_user.common.GlobalData.rest_type;

public class SearchFragement extends Fragment implements SearchConstructor.View {
    @BindView(R.id.rcy_search)
    RecyclerView rcySearch;
    @BindView(R.id.edSearch)
    CustomEditText edSearch;

    private Context context;
    SearchListAdpter mAdpter;
    private ArrayList<CategoryData> searchList;
    LinearLayoutManager mLayoutManagerSearch;
    SearchPresenter searchPresenter;
    Dialog dialog;

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
        searchPresenter = new SearchPresenter(this);
        searchPresenter.requestCategoryData(getActivity());

        setRestaurantData();
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(false) {
            @Override
            public void handleOnBackPressed() {

                MainActivity.navController.navigate(R.id.navigation_home);
            }
        });
        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mAdpter.getFilter().filter(charSequence.toString().trim());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return view;
    }

    private void setRestaurantData() {
        mAdpter = new SearchListAdpter(getActivity(), searchList);

        mLayoutManagerSearch = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        rcySearch.setLayoutManager(mLayoutManagerSearch);

        rcySearch.setItemAnimator(new DefaultItemAnimator());
        rcySearch.setAdapter(mAdpter);
    }


    @Override
    public void onDetach() {
        super.onDetach();

    }


    @Override
    public void onSearchResponseFailure(Throwable throwable) {
        displayMessage(throwable.getMessage());
    }

    @Override
    public void onSearchResponseSuccess(CategoryResponse response) {
        if (response.getStatus() == 1) {
            if (searchList.size() >= 0) {
                searchList.clear();
            }
            searchList.addAll(response.getData());
            mAdpter.notifyDataSetChanged();
        }
    }

    @Override
    public void showLoadingIndicator(boolean isShow) {
        if (dialog != null) {
            if (isShow) {
                dialog.show();
            } else {
                dialog.dismiss();
                dialog.cancel();
            }
        }
    }

    @Override
    public void displayMessage(String message) {
        CustomeToast.showToast(
                getActivity(),
                message,
                true,
                getResources().getColor(R.color.white),
                getResources().getColor(R.color.colorPrimary),
                true);
    }

    @Override
    public void initProgressBar() {
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progress_dialog);

        dialog.setCancelable(false);
    }

    @Override
    public Activity getContext() {
        return getActivity();
    }
}

