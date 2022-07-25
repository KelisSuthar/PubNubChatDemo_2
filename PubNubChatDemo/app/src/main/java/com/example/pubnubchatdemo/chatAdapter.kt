package com.example.pubnubchatdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pubnubchatdemo.dataclass.ChatMessages

class chatAdapter(var array: ArrayList<ChatMessages>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    //    var MSG = 0
//    var IMG = 1
    var SENDER_IMG = 0
    var RECIEVER_IMG = 1
    var SENDER_MSG = 2
    var RECIEVER_MSG = 3
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            SENDER_IMG -> {
                return MessageViewHolder1(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.chat_row_layout_sender_img, parent, false)
                )
            }
            RECIEVER_IMG -> {
                MessageViewHolder2(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.chat_row_layout_reciever_img, parent, false)
                )
            }
            SENDER_MSG -> {
                return MessageViewHolder3(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.chat_row_layout_sender_msg_2, parent, false)
                )
            }
            RECIEVER_MSG -> {
                MessageViewHolder4(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.chat_row_layout_reciever_msg_2, parent, false)
                )
            }
            else -> {
                return MessageViewHolder1(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.chat_row_layout_sender_msg_2, parent, false)
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MessageViewHolder1 -> holder.bind(array[position], position)
            is MessageViewHolder2 -> holder.bind(array[position], position)
            is MessageViewHolder3 -> holder.bind(array[position], position)
            is MessageViewHolder4 -> holder.bind(array[position], position)
        }

    }

    override fun getItemCount(): Int {
        return array.size
    }

    inner class MessageViewHolder1(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgRight: ImageView = itemView.findViewById(R.id.imgRight)

        fun bind(data: ChatMessages, position: Int) {

            if (data.is_image == true) {
                imgRight.LoadImg(data.url.toString())
            }

        }

    }

    inner class MessageViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgLeft: ImageView = itemView.findViewById(R.id.imgLeft)

        fun bind(data: ChatMessages, position: Int) {
            if (data.is_image == true) {
                imgLeft.LoadImg(data.url.toString())
            }

        }

    }

    inner class MessageViewHolder3(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtSendMsg: TextView = itemView.findViewById(R.id.txtSendMsg)
        fun bind(data: ChatMessages, position: Int) {
            txtSendMsg.text = data.msg
        }

    }

    inner class MessageViewHolder4(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtRecieveMsg: TextView = itemView.findViewById(R.id.txtRecieveMsg)

        fun bind(data: ChatMessages, position: Int) {
            txtRecieveMsg.text = data.msg
        }

    }

    override fun getItemViewType(position: Int): Int {
        val type: Int = if (array[position].sender == Appconstants.UUID) {
            if (array[position].is_image == true) {
                SENDER_IMG
            } else {
                SENDER_MSG
            }
        } else {
            if (array[position].is_image == true) {
                RECIEVER_IMG
            } else {
                RECIEVER_MSG
            }
        }
        return type
    }
}

fun ImageView.LoadImg(url: String) {
    Glide.with(this.context)
        .load(url)
        .placeholder(R.mipmap.ic_launcher)
        .into(this)

}
