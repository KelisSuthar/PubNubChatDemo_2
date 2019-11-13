package com.addedfooddelivery_user.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.common.ReusedMethod;
import com.addedfooddelivery_user.common.views.CustomEditText;
import com.addedfooddelivery_user.common.views.CustomTextView;
import com.addedfooddelivery_user.chat.adpter.ChatAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends AppCompatActivity {
    @BindView(R.id.rcyChat)
    RecyclerView rcyChat;
    @BindView(R.id.edChat)
    CustomEditText etChat;
    @BindView(R.id.imgChatSend)
    ImageView imgChatSend;
    @BindView(R.id.img_back_chat)
    ImageView imgChatBack;
    @BindView(R.id.imgChatUser)
    ImageView imgUser;
    @BindView(R.id.txtChatUname)
    CustomTextView etUsername;

    LinearLayoutManager mLayoutManagerChat;
    private ArrayList<String> chatConvList;
    ChatAdapter adpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        chatConvList = new ArrayList<>();
        fillRecords();
        setAddressData();
    }

    @OnClick({R.id.img_back_chat, R.id.imgChatSend})
    public void Clickevent(View view) {
        switch (view.getId()) {
            case R.id.imgChatUser:
                onBackPressed();
                break;
            case R.id.imgChatSend:
                if (etChat.getText().toString().trim().equalsIgnoreCase("")) {
                    ReusedMethod.showSnackBar(ChatActivity.this, getResources().getString(R.string.val_message), 1);
                } else {
                    chatConvList.add("1");
                    adpter.notifyDataSetChanged();
                    etChat.setText("");
                    rcyChat.scrollToPosition(chatConvList.size() - 1);
                    ReusedMethod.hideKeyboard(ChatActivity.this);
                }
                break;
            case R.id.img_back_chat:
                onBackPressed();
                break;
        }
    }

    private void setAddressData() {
        adpter = new ChatAdapter(ChatActivity.this, chatConvList);

        mLayoutManagerChat = new LinearLayoutManager(ChatActivity.this, RecyclerView.VERTICAL, false);
        mLayoutManagerChat.setStackFromEnd(true);
        rcyChat.setLayoutManager(mLayoutManagerChat);

        rcyChat.setItemAnimator(new DefaultItemAnimator());
        rcyChat.setAdapter(adpter);
    }

    private void fillRecords() {
        chatConvList.add("1");
        chatConvList.add("2");
        chatConvList.add("3");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.leftto, R.anim.right);
        finish();
    }
}
