package com.example.pubnubchatdemo.dataclass

data class ChatMessages(
    val  msg:String?="",
    val  sender:String?="",
    val  is_image:Boolean?=false,
)
