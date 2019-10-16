package com.addedfooddelivery_user.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user._common.views.CustomButton;
import com.addedfooddelivery_user._common.views.CustomTextView;
import com.addedfooddelivery_user.login.loginEmail.LoginEmailActivity;
import com.addedfooddelivery_user.signup.SignupActivity;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class FiltersActivity extends AppCompatActivity {
    @BindView(R.id.chipsFood)
    ChipGroup chipGroupFood;
    @BindView(R.id.txtClear)
    CustomTextView txtClear;
    @BindView(R.id.img_back_filter)
    ImageView imgFillter;

    @BindView(R.id.imgRelevance)
    CheckBox imgRelevance;
    @BindView(R.id.imgRating)
    CheckBox imgRating;
    @BindView(R.id.imgTime)
    CheckBox imgTime;
    @BindView(R.id.imgCostLowToHigh)
    CheckBox imgCostLowToHigh;
    @BindView(R.id.imgCostHighToLow)
    CheckBox imgCostHighToLow;
    @BindView(R.id.btFilter)
    CustomButton btFilter;


    private ArrayList<String> foodCategoryList = new ArrayList<>();
    private ArrayList<String> selectedCategory = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);
        ButterKnife.bind(this);

        setFood();
    }

    @OnClick(R.id.img_back_filter)
    public void eventClick(View view) {
        switch (view.getId()) {
            case R.id.img_back_filter:
                onBackPressed();
                break;
            case R.id.txtClear:
                onBackPressed();
                break;
        }
    }

    private void setFood() {
        foodCategoryList.add("All");
        foodCategoryList.add("Breakfast");
        foodCategoryList.add("Meals");
        foodCategoryList.add("Starters");
        foodCategoryList.add("Baked Dishes");
        foodCategoryList.add("All");
        foodCategoryList.add("Breakfast");
        foodCategoryList.add("Meals");


        chipGroupFood.removeAllViews();
        if (foodCategoryList != null && foodCategoryList.size() > 0) {
            for (int i = 0; i < foodCategoryList.size(); i++) {
                final Chip entryChip = getChip(chipGroupFood, foodCategoryList.get(i).toString(), i);

                chipGroupFood.addView(entryChip);
                chipGroupFood.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(ChipGroup chipGroup, int checkedId) {

                        Chip chip = chipGroup.findViewById(checkedId);

                        if (chip != null) {
                            selectedCategory.clear();
                            if (chip.isCheckable()) {
                                int position = (Integer) chip.getTag();
                                Log.v("@@@@", foodCategoryList.get(position).toString());
                                //get selection id
                                selectedCategory.add(foodCategoryList.get(position).toString());

                            } else {

                                selectedCategory.remove(chip.getTag().toString());
                            }
                        }


                    }
                });
            }
        }
    }

    private Chip getChip(final ChipGroup entryChipGroup, String text, int position) {
        final Chip chip = new Chip(this);
        chip.setChipDrawable(ChipDrawable.createFromResource(this, R.xml.my_chip));
        int paddingDp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 10,
                getResources().getDisplayMetrics()
        );
        chip.setPadding(paddingDp, 2, paddingDp, 2);
        chip.setText(text);
        chip.setTextColor(getResources().getColor(R.color.bg_chip_state_list));
        chip.setBackgroundColor(getResources().getColor(R.color.bg_chip_state_list));
        Typeface font = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Montserrat-Bold.ttf");
        chip.setTypeface(font);
        chip.setTag(position);
        chip.setTextAppearanceResource(R.style.chipTextAppearance);

        return chip;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(FiltersActivity.this, MainActivity.class));
        overridePendingTransition(R.anim.leftto, R.anim.right);
        finish();
    }
}
