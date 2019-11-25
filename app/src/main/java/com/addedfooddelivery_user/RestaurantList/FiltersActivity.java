package com.addedfooddelivery_user.RestaurantList;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.RadioButton;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.common.views.CustomButton;
import com.addedfooddelivery_user.common.views.CustomTextView;
import com.addedfooddelivery_user.home.MainActivity;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.crystal.crystalrangeseekbar.widgets.CrystalSeekbar;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.addedfooddelivery_user.common.GlobalData.category;
import static com.addedfooddelivery_user.common.GlobalData.direction;
import static com.addedfooddelivery_user.common.GlobalData.price;
import static com.addedfooddelivery_user.common.GlobalData.sort_by;


public class FiltersActivity extends AppCompatActivity {
    @BindView(R.id.chipsFood)
    ChipGroup chipGroupFood;
    @BindView(R.id.ragSortBy)
    RadioGroup ragSortBy;
    @BindView(R.id.txtClear)
    CustomTextView txtClear;
    @BindView(R.id.img_back_filter)
    ImageView imgFillter;
    @BindView(R.id.imgRelevance)
    RadioButton imgRelevance;
    @BindView(R.id.imgRating)
    RadioButton imgRating;
    @BindView(R.id.imgTime)
    RadioButton imgTime;
    @BindView(R.id.imgCostLowToHigh)
    RadioButton imgCostLowToHigh;
    @BindView(R.id.imgCostHighToLow)
    RadioButton imgCostHighToLow;
    @BindView(R.id.btFilter)
    CustomButton btFilter;
    @BindView(R.id.rngPrise)
    CrystalSeekbar rngPrise;
    @BindView(R.id.txt_min_prise)
    CustomTextView txtMinPrise;
    @BindView(R.id.txt_max_prise)
    CustomTextView txtMaxPrise;

    private ArrayList<String> foodCategoryList = new ArrayList<>();
    private ArrayList<String> selectedCategory = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);
        ButterKnife.bind(this);

        setFood();
        setView();
        rngPrise.setOnSeekbarChangeListener(new OnSeekbarChangeListener() {
            @Override
            public void valueChanged(Number value) {

                txtMaxPrise.setText(String.valueOf(value + ".00"));

                price = String.valueOf(value);
                if (price.equalsIgnoreCase("0")) {
                    price = "";
                }
            }
        });

        ragSortBy.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton rb = (RadioButton) radioGroup.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    if (rb.getId() == R.id.imgCostLowToHigh) {
                        sort_by = "price";
                        direction = "asc";
                    } else if (rb.getId() == R.id.imgCostHighToLow) {
                        sort_by = "price";
                        direction = "desc";
                    } else
                        sort_by = rb.getText().toString();


                }

            }
        });


    }

    private void setView() {
        //for already filter
        if (!price.equalsIgnoreCase("")) {
            rngPrise.setMinStartValue(Float.parseFloat(price));
            rngPrise.setMinStartValue(Float.parseFloat(price));
            txtMaxPrise.setText(String.valueOf(price + ".00"));
            rngPrise.apply();
        }

        if (sort_by.equalsIgnoreCase(getString(R.string.relvance))) {
            ragSortBy.check(ragSortBy.getChildAt(0).getId());
        } else if (sort_by.equalsIgnoreCase(getString(R.string.rating))) {
            ragSortBy.check(ragSortBy.getChildAt(1).getId());
        } else if (sort_by.equalsIgnoreCase(getString(R.string.prise))) {
            if (direction.equalsIgnoreCase("asc"))
                ragSortBy.check(ragSortBy.getChildAt(3).getId());
            else
                ragSortBy.check(ragSortBy.getChildAt(4).getId());
        }

        if (!category.equalsIgnoreCase("")) {
            for (int j = 0; j < chipGroupFood.getChildCount(); j++) {
                Chip nextchip = (Chip) chipGroupFood.getChildAt(j);
                if (nextchip.getText().equals(category)) {
                    nextchip.setChecked(true);
                } else {
                    nextchip.setChecked(false);
                }
            }
        }

    }


    @OnClick({R.id.img_back_filter, R.id.txtClear, R.id.btFilter})
    public void eventClick(View view) {
        switch (view.getId()) {
            case R.id.img_back_filter:
                onBackPressed();
                break;
            case R.id.txtClear:
                chipGroupFood.clearCheck();
                ragSortBy.clearCheck();
                rngPrise.setMinStartValue(0);
                rngPrise.apply();

                sort_by = "";
                direction = "asc";
                category = "";
                price = "";
                break;
            case R.id.btFilter:
                Log.v("Data", sort_by + "__" + direction + "__" + category + "__" + price);
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
        foodCategoryList.add("Fried Rice");
        foodCategoryList.add("Accompaniments");
        foodCategoryList.add("Chinese");
        foodCategoryList.add("Noodles");
        foodCategoryList.add("Rice");
        foodCategoryList.add("Oven");


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
                                category = foodCategoryList.get(position).toString();
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
        chip.setTag(text);
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
        Intent intent = new Intent();
        intent.putExtra("editTextValue", "value_here");
        setResult(RESULT_OK, intent);
        overridePendingTransition(R.anim.leftto, R.anim.right);
        finish();
    }
}
