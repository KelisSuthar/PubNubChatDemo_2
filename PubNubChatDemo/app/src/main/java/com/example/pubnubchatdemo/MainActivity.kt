package com.example.pubnubchatdemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.pubnubchatdemo.dataclass.ChatMessages
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.textfield.TextInputEditText
import com.pubnub.api.PNConfiguration
import com.pubnub.api.PubNub
import com.pubnub.api.callbacks.SubscribeCallback
import com.pubnub.api.enums.PNLogVerbosity
import com.pubnub.api.enums.PNReconnectionPolicy
import com.pubnub.api.models.consumer.PNStatus
import com.pubnub.api.models.consumer.history.PNFetchMessageItem
import com.pubnub.api.models.consumer.pubsub.PNMessageResult
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult
import com.pubnub.api.models.consumer.pubsub.message_actions.PNMessageActionResult
import com.pubnub.api.models.consumer.pubsub.objects.PNObjectEventResult
import java.io.File
import java.io.FileInputStream
import java.io.InputStream


class MainActivity : AppCompatActivity() {
    var IMG_CODE = 101
    var img = ""
    var selectedFile: File? = null
    var btn: Button? = null
    var file_attech: ImageView? = null
    var image: ImageView? = null
    var pubnub: PubNub? = null
    var adapter: chatAdapter? = null
    var recyclerView: RecyclerView? = null
    var editText: TextInputEditText? = null
    private val mMessages: ArrayList<ChatMessages> = ArrayList()
    private val message_list: ArrayList<PNFetchMessageItem> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUp_PubNub()
        recyclerView = findViewById(R.id.recyclerview)
        btn = findViewById(R.id.send)
        editText = findViewById(R.id.text)
        file_attech = findViewById(R.id.appCompatImageView)
        image = findViewById(R.id.image)
        adapter = chatAdapter(mMessages)
        recyclerView?.adapter = adapter
        btn!!.setOnClickListener {
            if (!selectedFile?.absolutePath.isNullOrEmpty()) {
                sendImage()
            } else {
                if (editText!!.text?.trim().toString().isNullOrEmpty()) {
                    Toast.makeText(this, "PLease Enter Text", Toast.LENGTH_SHORT).show()
                } else {
                    createSendMsg()
                }
            }
            image!!.visibility = View.GONE
            editText!!.visibility = View.VISIBLE
            file_attech!!.visibility = View.VISIBLE

        }
        file_attech!!.setOnClickListener {
            ImagePicker.with(this)
                .compress(1024)
                .maxResultSize(
                    1080,
                    1080
                )
                .start(IMG_CODE)

        }
        getHistory()
        pubnub!!.addListener(object : SubscribeCallback() {
            override fun status(pubnub: PubNub, status: PNStatus) {
                println("Status category: ${status.category}")
                println("Status operation: ${status.operation}")
                println("Status error: ${status.error}")
            }

            override fun message(pubnub: PubNub, pnMessageResult: PNMessageResult) {
                println("Message: ${pnMessageResult}")
                println("Message ID: ${pubnub.instanceId}")
                println("Message payload: ${pnMessageResult.message}")
                println("Message channel: ${pnMessageResult.channel}")
                println("Message publisher: ${pnMessageResult.publisher}")
                println("Message timetoken: ${pnMessageResult.timetoken}")

                runOnUiThread {
                    mMessages.add(
                        ChatMessages(
                            pnMessageResult.message.asString,
                            pnMessageResult.publisher.toString()
                        )
                    )
                    adapter!!.notifyDataSetChanged()
                    recyclerView?.scrollToPosition(mMessages.size - 1)
                }
            }

            override fun presence(pubnub: PubNub, pnPresenceEventResult: PNPresenceEventResult) {
                println("Presence: ${pnPresenceEventResult}")
                println("Presence event: ${pnPresenceEventResult.event}")
                println("Presence channel: ${pnPresenceEventResult.channel}")
                println("Presence uuid: ${pnPresenceEventResult.uuid}")
                println("Presence timetoken: ${pnPresenceEventResult.timetoken}")
                println("Presence occupancy: ${pnPresenceEventResult.occupancy}")
            }

            override fun messageAction(
                pubnub: PubNub,
                pnMessageActionResult: PNMessageActionResult
            ) {
                with(pnMessageActionResult.messageAction) {
                    println("Message action type: $type")
                    println("Message action value: $value")
                    println("Message action uuid: $uuid")
                    println("Message action actionTimetoken: $actionTimetoken")
                    println("Message action messageTimetoken: $messageTimetoken")
                }

                println("Message action subscription: ${pnMessageActionResult.subscription}")
                println("Message action channel: ${pnMessageActionResult.channel}")
                println("Message action timetoken: ${pnMessageActionResult.timetoken}")
            }

            override fun objects(pubnub: PubNub, objectEvent: PNObjectEventResult) {
                println("Object event channel: ${objectEvent.channel}")
                println("Object event publisher: ${objectEvent.publisher}")
                println("Object event subscription: ${objectEvent.subscription}")
                println("Object event timetoken: ${objectEvent.timetoken}")
                println("Object event userMetadata: ${objectEvent.userMetadata}")

                with(objectEvent.extractedMessage) {
                    println("Object event event: $event")
                    println("Object event source: $source")
                    println("Object event type: $type")
                    println("Object event version: $version")
                }
            }

        })


    }

    private fun sendImage() {

        val inputstream: InputStream = FileInputStream(selectedFile!!.absolutePath)

        pubnub!!.sendFile(
            Appconstants.CHANNEL_NAME,
            selectedFile!!.absolutePath,
            inputstream,
            message = null
        )
            .async { result, status ->

                Log.e("PUB_NUB", result!!.file.name)
                Log.e("PUB_NUB", result.timetoken.toString())
                Log.e("PUB_NUB", result.status.toString())
                if (status.error) {
                    //handle error
                } else if (result != null) {
                    //handle result
                }
                pubnub!!.getFileUrl(
                    Appconstants.CHANNEL_NAME,
                    result!!.file.name,
                    result!!.file.id,
                ).async { result, status ->
                    if (!status.error || result != null) {
                        mMessages.add(
                            ChatMessages(
                                result?.url,
                                "",
                                true
                            )
                        )

                    }
                    adapter?.notifyDataSetChanged()
                    recyclerView?.scrollToPosition(mMessages.size - 1)
                }
            }
    }


    private fun setUp_PubNub() {
        val config = PNConfiguration(Appconstants.UUID).apply {
            subscribeKey = "sub-c-3adb09b0-9bab-11ec-879a-86a1e6519840"
            publishKey = "pub-c-f511c054-ae12-4ca5-8cf3-1751e82190f6"
            uuid = Appconstants.UUID
            logVerbosity = PNLogVerbosity.BODY
            reconnectionPolicy = PNReconnectionPolicy.LINEAR
            maximumReconnectionRetries = 10
        }
        pubnub = PubNub(config)

        pubnub!!.subscribe(channels = listOf(Appconstants.CHANNEL_NAME))//List OF Channels

    }

    private fun createSendMsg() {

        this.pubnub!!.publish(
            channel = Appconstants.CHANNEL_NAME,
            message = editText?.text?.trim().toString()
        )
            .async { result, status ->

                if (!status.error) {
                    println("Message timetoken: ${result!!.timetoken}")
                    editText?.setText("")
                    recyclerView?.scrollToPosition(mMessages.size - 1)
                } else {
                    status.exception!!.printStackTrace()
                }
            }


    }

    private fun getHistory() {
        pubnub!!.history(
            Appconstants.CHANNEL_NAME, // where to fetch history from
            count = 100 // how many items to fetch
        ).async { result, status ->
            result!!.messages.forEach {
                Log.e("PUB_NUB", it.entry.toString())
            }
        }
        pubnub!!.listFiles(
            Appconstants.CHANNEL_NAME
        ).async { result, status ->
            if (!status.error && result != null) {
//                Log.e("PUB_NUB", result.toString())
            }
            result!!.data.forEach {
//                Log.e("PUB_NUB",it.id)
            }
        }
        pubnub?.fetchMessages(
            channels = listOf(Appconstants.CHANNEL_NAME)
        )?.async { result, status ->
            if (!status.error) {
                result!!.channels.forEach { (channel, messages) ->
                    println("Channel: $channel")
                    messages.forEach { messageItem: PNFetchMessageItem ->
                        message_list.add(messageItem)
                        println(messageItem.message)// actual message payload
//                        println(messageItem.timetoken) // included by default
//                        println(messageItem.uuid) // included by default
                        if (messageItem.message.isJsonObject) {
                            val file = messageItem.message.asJsonObject.get("file").asJsonObject
                            getFileUrl(file.get("name").asString, file.get("id").asString)

                        } else {
                            mMessages.add(
                                ChatMessages(
                                    messageItem.message.asString,
                                    messageItem.uuid.toString(),
                                    false
                                )
                            )
                        }



                        adapter?.notifyDataSetChanged()
                    }

//                    recyclerView?.scrollToPosition(adapter!!.itemCount-1)
                    recyclerView?.scrollToPosition(mMessages.size - 1)


                }
            } else {
                // handle error
                status.exception?.printStackTrace()
            }

        }
    }

    private fun getFileUrl(name: String, id: String) {
        pubnub!!.getFileUrl(
            Appconstants.CHANNEL_NAME,
            name,
            id,
        ).async { result, status ->
//            Log.e("PUB_NUB", result!!.url)
            if (!status.error && result != null) {
                mMessages.add(
                    ChatMessages(
                        result.url,
                        "",
                        true
                    )
                )

            }
            recyclerView!!.post { adapter!!.notifyDataSetChanged() }
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == IMG_CODE) {
            image!!.visibility = View.VISIBLE
            editText!!.visibility = View.GONE
            file_attech!!.visibility = View.GONE
            image!!.setImageURI(data?.data!!)

            selectedFile = File(
                data.getStringExtra("extra.file_path")
            )
        }
    }
}



