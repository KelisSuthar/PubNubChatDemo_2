package com.addedfooddelivery_user.RestaurantDetails;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.addedfooddelivery_user.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestDetailsActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBarLayout;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout collapseToolBarEvent;

    private AppBarLayout.OnOffsetChangedListener onOffsetChangedListener = new AppBarLayout.OnOffsetChangedListener() {
        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            //  Vertical offset == 0 indicates appBar is fully expanded.
            if (Math.abs(verticalOffset) > 300) {
                toolbar.setTitle(getResources().getString(R.string.title_activity_rest_details));
                collapseToolBarEvent.setTitle(getResources().getString(R.string.title_activity_rest_details));
            }else if(Math.abs(verticalOffset) <250){
                collapseToolBarEvent.setTitle("");
                toolbar.setTitle("");
            } else {
                collapseToolBarEvent.setTitle("");
                toolbar.setTitle("");
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_rest_details);
        ButterKnife.bind(this);


        appBarLayout.addOnOffsetChangedListener(onOffsetChangedListener);
    }


}
