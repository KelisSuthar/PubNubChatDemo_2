package com.addedfooddelivery_user.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.common.views.CustomButton;
import com.addedfooddelivery_user.payment.api.PaymentPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CardListActivity extends AppCompatActivity {
    @BindView(R.id.btAddCard)
    CustomButton btAddCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btAddCard)
    public void eventClick(View view) {
        switch (view.getId()) {
            case R.id.btAddCard:

                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.leftto, R.anim.right);
        finish();
    }
}
