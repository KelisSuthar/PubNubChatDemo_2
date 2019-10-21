package com.addedfooddelivery_user.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user._common.views.CustomEditText;
import com.addedfooddelivery_user._common.views.CustomTextView;
import com.addedfooddelivery_user.chat.adpter.ChatAdapter;
import com.addedfooddelivery_user.home.DeliveryListActivity;
import com.addedfooddelivery_user.home.fragement.adpter.AddressListAdpter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    private void setAddressData() {
        adpter = new ChatAdapter(ChatActivity.this, chatConvList);

        mLayoutManagerChat = new LinearLayoutManager(ChatActivity.this, RecyclerView.VERTICAL, false);
        rcyChat.setLayoutManager(mLayoutManagerChat);

        rcyChat.setItemAnimator(new DefaultItemAnimator());
        rcyChat.setAdapter(adpter);
    }

    private void fillRecords() {
        chatConvList.add("1");
        chatConvList.add("2");
        chatConvList.add("3");
    }
}
