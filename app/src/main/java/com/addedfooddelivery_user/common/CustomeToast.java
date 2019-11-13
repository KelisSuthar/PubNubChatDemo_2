package com.addedfooddelivery_user.common;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.common.views.CustomTextView;

public class CustomeToast {

    public static void showToast(Context context, String toast_title, boolean duration, int txtColor, int bgColor,  boolean postion) {
        Toast toast;
        if (duration) {
            toast = Toast.makeText(context, toast_title, Toast.LENGTH_LONG);
        } else {
            toast = Toast.makeText(context, toast_title, Toast.LENGTH_SHORT);
        }

        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View toastView = li.inflate(R.layout.toast_hint_layout, null);


        CustomTextView text = toastView.findViewById(R.id.txtToast);
        LinearLayout ll = toastView.findViewById(R.id.ll_toast);
       // ImageView ivStatus = toastView.findViewById(R.id.ivStatus);
        ll.setBackgroundColor(bgColor);
        text.setText(toast_title);
        text.setTextColor(txtColor);
       /* if (drawable != 0) {
            ivStatus.setVisibility(View.VISIBLE);
            ivStatus.setImageResource(drawable);

        }*/
        // text.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);

        /*if (postion) {
            toast.setGravity(Gravity.TOP, 0, 50);
        } else
            toast.setGravity(Gravity.BOTTOM, 0, 50);*/
        toast.setGravity(Gravity.BOTTOM|Gravity.FILL_HORIZONTAL, 0, 0);
        toast.setView(toastView);

        toast.show();
    }

}