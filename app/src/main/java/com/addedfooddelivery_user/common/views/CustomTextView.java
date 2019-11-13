package com.addedfooddelivery_user.common.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.common.AppConstants;
import com.addedfooddelivery_user.common.TypeFactory;

public class CustomTextView extends AppCompatTextView {

    private int typefaceType;
    private TypeFactory mFontFactory;

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context, attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        applyCustomFont(context, attrs);
    }

    public CustomTextView(Context context) {
        super(context);
    }

    private void applyCustomFont(Context context, AttributeSet attrs) {


        TypedArray array = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CustomTextView,
                0, 0);
        try {
            typefaceType = array.getInteger(R.styleable.CustomTextView_font_name, 0);
        } finally {
            array.recycle();
        }
        if (!isInEditMode()) {
            setTypeface(getTypeFace(typefaceType));
        }

    }

    public Typeface getTypeFace(int type) {
        if (mFontFactory == null)
            mFontFactory = new TypeFactory(getContext());

        switch (type) {
            case AppConstants.Montserrat_Bold:
                return TypeFactory.montserratBold;

            case AppConstants.Montserrat_Medium:
                return TypeFactory.montserratMedium;

            case AppConstants.Montserrat_SemiBold:
                return TypeFactory.montserratSemiBold;

            default:
                return TypeFactory.montserratREGULAR;
        }

    }


}
