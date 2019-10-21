package com.addedfooddelivery_user.chat.adpter;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.addedfooddelivery_user.R;

import java.util.ArrayList;
import java.util.List;



public class ChatAdapter extends RecyclerView.Adapter {

    private int VIEW_SEND = 0;
    private int VIEW_RECIVED = 1;

    private Context context;
    private List<String> chatObjects;

    public ChatAdapter(Context context, ArrayList<String> chatObjects) {
        this.context = context;
        this.chatObjects = chatObjects;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_SEND) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_msg_outgoing, parent, false);
            return new SendViewHolder(v);
        } else if (viewType == VIEW_RECIVED) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_msg_incoming, parent, false);
            return new RecievedViewHolder(v);
        }
        return null;
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof SendViewHolder) {
            final SendViewHolder sendViewHolder = (SendViewHolder) holder;

           /* sendViewHolder.messageChatTv.setText(chatObjects.get(position).getAsString("message"));
            sendViewHolder.timeChatTv.setText(SmartUtils.getTime(chatObjects.get(position).getAsString("message_time")));*/

        } else if (holder instanceof RecievedViewHolder) {
            final RecievedViewHolder recievedViewHolder = (RecievedViewHolder) holder;
           /* recievedViewHolder.timeChatTv.setText(SmartUtils.getTime(chatObjects.get(position).getAsString("message_time")));*/
        }
    }

    @Override
    public int getItemCount() {
        if (chatObjects != null) {
            return chatObjects.size();
        } else {
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        int result = 0;

        if (chatObjects.get(position).equalsIgnoreCase("1")) {
            result = VIEW_SEND;
        } else {
            result = VIEW_RECIVED;
        }
        return result;
    }


    private class SendViewHolder extends RecyclerView.ViewHolder {

        private SendViewHolder(View itemView) {
            super(itemView);


        }
    }

    private class RecievedViewHolder extends RecyclerView.ViewHolder {

        private RecievedViewHolder(View itemView) {
            super(itemView);


        }
    }
}