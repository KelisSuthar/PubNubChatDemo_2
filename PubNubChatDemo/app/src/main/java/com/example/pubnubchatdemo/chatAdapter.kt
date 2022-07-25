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
    var SENDER = 0
    var RECIEVER = 1
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            SENDER -> {
                return MessageViewHolder1(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.chat_row_layout_sender_msg, parent, false)
                )
            }
            RECIEVER -> {
                MessageViewHolder2(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.chat_row_layout_reciever_msg, parent, false)
                )
            }
            else -> {
                return MessageViewHolder1(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.chat_row_layout_sender_msg, parent, false)
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MessageViewHolder1 -> holder.bind(array[position], position)
            is MessageViewHolder2 -> holder.bind(array[position], position)
        }

    }

    override fun getItemCount(): Int {
        return array.size
    }

    inner class MessageViewHolder1(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtSendMsg: TextView = itemView.findViewById(R.id.txtSendMsg)
        var imgRight: ImageView = itemView.findViewById(R.id.imgRight)


        fun bind(data: ChatMessages, position: Int) {
            txtSendMsg.text = data.msg
            imgRight.LoadImg(data.url.toString())
            if (data.is_image == true) {
                imgRight.visibility = View.VISIBLE
                txtSendMsg.visibility = View.GONE
            } else {
                imgRight.visibility = View.GONE
                txtSendMsg.visibility = View.VISIBLE
            }

        }

    }

    inner class MessageViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtRecieveMsg: TextView = itemView.findViewById(R.id.txtRecieveMsg)
        var imgLeft: ImageView = itemView.findViewById(R.id.imgLeft)

        fun bind(data: ChatMessages, position: Int) {
            imgLeft.LoadImg(data.url.toString())
            txtRecieveMsg.text = data.msg

            if (data.is_image == true) {

                imgLeft.visibility = View.VISIBLE
                txtRecieveMsg.visibility = View.GONE
            } else {
                imgLeft.visibility = View.GONE
                txtRecieveMsg.visibility = View.VISIBLE
            }

        }

    }

    override fun getItemViewType(position: Int): Int {
        val type: Int = if (array[position].sender == Appconstants.UUID) {
            SENDER
        } else {
            RECIEVER
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
