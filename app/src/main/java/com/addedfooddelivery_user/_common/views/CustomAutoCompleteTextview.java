package com.addedfooddelivery_user._common.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.KeyEvent;

import androidx.appcompat.widget.AppCompatAutoCompleteTextView;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user._common.AppConstants;
import com.addedfooddelivery_user._common.TypeFactory;

public class CustomAutoCompleteTextview extends AppCompatAutoCompleteTextView {

    OnKeyboardDownListener mListener;
    private int typefaceType;
    private TypeFactory mFontFactory;

    public CustomAutoCompleteTextview(Context context) {
        super(context);

    }

    public CustomAutoCompleteTextview(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context, attrs);

    }

    public CustomAutoCompleteTextview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context, attrs);
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

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            if (mListener != null)
                mListener.onKeyDown();
            return false;
        }
        return super.dispatchKeyEvent(event);
    }

    public void setListener(OnKeyboardDownListener listener) {
        this.mListener = listener;


    }

    public interface OnKeyboardDownListener {
        void onKeyDown();
    }


}
