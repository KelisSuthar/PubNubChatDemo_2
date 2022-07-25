package com.example.quickbloxdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.quickblox.auth.session.QBSession
import com.quickblox.auth.session.QBSessionManager
import com.quickblox.auth.session.QBSessionParameters
import com.quickblox.auth.session.QBSettings
import com.quickblox.chat.QBChatService
import com.quickblox.conference.ConferenceClient
import com.quickblox.conference.ConferenceConfig
import com.quickblox.conference.ConferenceSession
import com.quickblox.conference.WsException
import com.quickblox.conference.callbacks.ConferenceEntityCallback
import com.quickblox.conference.callbacks.ConferenceSessionCallbacks
import com.quickblox.core.LogLevel
import com.quickblox.core.QBEntityCallback
import com.quickblox.core.ServiceZone
import com.quickblox.core.exception.QBResponseException
import com.quickblox.users.QBUsers
import com.quickblox.users.model.QBUser
import com.quickblox.videochat.webrtc.QBRTCConfig
import com.quickblox.videochat.webrtc.QBRTCMediaConfig
import com.quickblox.videochat.webrtc.QBRTCTypes

class MainActivity : AppCompatActivity() {
    private val APPLICATION_ID = "67895"
    private val AUTH_KEY = "lkjdueksu7392kj"
    private  val AUTH_SECRET = "BTFsj7Rtt27DAmT"
    private  val ACCOUNT_KEY = "9yvTe17TmjNPqDoYtfqp"
    private val API_ENDPOINT = "https://apicustomdomain.quickblox.com"
    private val CHAT_ENDPOINT = "chatcustomdomain.quickblox.com"
    val isVideoCall=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        QBSettings.getInstance().init(applicationContext, APPLICATION_ID, AUTH_KEY, AUTH_SECRET)
        QBSettings.getInstance().accountKey = ACCOUNT_KEY
        QBSettings.getInstance().setEndpoints(API_ENDPOINT, CHAT_ENDPOINT, ServiceZone.PRODUCTION)
        QBSettings.getInstance().zone = ServiceZone.PRODUCTION
        QBSettings.getInstance().logLevel = LogLevel.DEBUG
        QBRTCConfig.setDebugEnabled(true)
        QBChatService.getInstance().isReconnectionAllowed = true

        QBSettings.getInstance().isAutoCreateSession = false


        QBRTCMediaConfig.setAudioCodec(QBRTCMediaConfig.AudioCodec.ISAC)
        QBRTCMediaConfig.setAudioCodec(QBRTCMediaConfig.AudioCodec.OPUS)

        QBRTCMediaConfig.setVideoCodec(QBRTCMediaConfig.VideoCodec.H264)
        QBRTCMediaConfig.setVideoCodec(QBRTCMediaConfig.VideoCodec.VP8)
        QBRTCMediaConfig.setVideoCodec(QBRTCMediaConfig.VideoCodec.VP9)

        QBRTCMediaConfig.setAudioStartBitrate(audioStartBitrate)

        QBRTCMediaConfig.setVideoStartBitrate(videoStartBitrate)

        QBRTCMediaConfig.setVideoWidth(videoWidth)
        QBRTCMediaConfig.setVideoHeight(videoHeight)


        QBRTCMediaConfig.setVideoHWAcceleration(true)

        QBRTCMediaConfig.setVideoFps(600)

        QBRTCMediaConfig.setUseBuildInAEC(true)

        QBRTCMediaConfig.setUseOpenSLES(true)

        QBRTCMediaConfig.setAudioProcessingEnabled(true)

        //manage session using this

        QBSessionManager.getInstance().addListener(object : QBSessionManager.QBSessionListener {
            override fun onSessionCreated(session: QBSession) {
                // calls when session was created firstly or after it has been expired
            }

            override fun onSessionUpdated(sessionParameters: QBSessionParameters) {
                // calls when user signed in or signed up
                // QBSessionParameters stores information about signed in user.
            }

            override fun onSessionDeleted() {
                // calls when user signed Out or session was deleted
            }

            override fun onSessionRestored(session: QBSession) {
                // calls when session was restored from local storage
            }

            override fun onSessionExpired() {
                // calls when session is expired
            }

            override fun onProviderSessionExpired(provider: String) {
                // calls when provider's access token is expired or invalid
            }
        })


        val sessionParameters = QBSessionManager.getInstance().getSessionParameters()
        sessionParameters.userId
        sessionParameters.userLogin
        sessionParameters.userEmail
        sessionParameters.socialProvider
        sessionParameters.accessToken

        val isLoggedIn = QBSessionManager.getInstance().sessionParameters != null

            // For Signup Users
        val user = QBUser()
        user.login = "johnsmith"
        user.password = "johnPassword"

        QBUsers.signUp(user).performAsync(object : QBEntityCallback<QBUser> {
            override fun onSuccess(user: QBUser?, bundle: Bundle?) {

            }

            override fun onError(exception: QBResponseException?) {

            }
        })
            //login User
//        val user = QBUser()
        user.login = "johnsmith"
        user.password = "johnPassword"

        QBUsers.signIn(user).performAsync(object : QBEntityCallback<QBUser> {
            override fun onSuccess(user: QBUser?, bundle: Bundle?) {

            }

            override fun onError(exception: QBResponseException?) {

            }
        })

        //set existing session
//        val token = "3Fjs1su8ery463gjd8hf"
//        QBSessionManager.getInstance().createActiveSession(token, tokenExpirationDate)

        setVideoCallFuntionality()
    }

    private fun setVideoCallFuntionality() {
        val conferenceServer = "your_conference_server"
        ConferenceConfig.setUrl(conferenceServer)

        val client = ConferenceClient.getInstance(applicationContext)

        val conferenceType = if (isVideoCall) {
            QBRTCTypes.QBConferenceType.QB_CONFERENCE_TYPE_VIDEO
        } else {
            QBRTCTypes.QBConferenceType.QB_CONFERENCE_TYPE_AUDIO
        }

        client.isAutoSubscribeAfterJoin = true

        //user id for conferance
        val userId : Int = -1
        client.createSession(userId, conferenceType, object :
            ConferenceEntityCallback<ConferenceSession> {
            override fun onSuccess(conferenceSession: ConferenceSession?) {
                // session Created Successfully
            }

            override fun onError(exception: WsException?) {
                // create Session Error
            }
        })

        val sessionStateCallback = object : QBRTCSessionStateCallback<ConferenceSession> {
            override fun onStateChanged(conferenceSession: ConferenceSession?, sessionState: BaseSession.QBRTCSessionState?) {

            }

            override fun onConnectedToUser(conferenceSession: ConferenceSession?, userId: Int?) {

            }

            override fun onDisconnectedFromUser(conferenceSession: ConferenceSession?, userId: Int?) {

            }

            override fun onConnectionClosedForUser(conferenceSession: ConferenceSession?, userId: Int?) {

            }
        }

        currentConferenceSession.addSessionCallbacksListener(sessionStateCallback)
// or
        currentConferenceSession.removeSessionCallbacksListener(sessionStateCallback)

        val conferenceSessionCallbacks = object : ConferenceSessionCallbacks {
            override fun onPublishersReceived(publishersList: ArrayList<Int>?) {
                // publisher or Publishers (users) joined
            }

            override fun onPublisherLeft(userId: Int?) {
                // publisher left
            }

            override fun onMediaReceived(type: String?, success: Boolean) {
                // media received (audio or video type)
            }

            override fun onSlowLinkReceived(uplink: Boolean, nacks: Int) {
                // called when slowLink is received from server.
                // slowLink with uplink = true - several missing packets from server;
                // uplink = false means server is not receiving all your packets.
            }

            override fun onError(exception: WsException?) {
                // error from Server Received
            }

            override fun onSessionClosed(conferenceSession: ConferenceSession?) {
                // cession Closed
            }
        }

        currentConferenceSession.addConferenceSessionListener(conferenceSessionCallbacks)
// or
        currentConferenceSession.removeConferenceSessionListener(conferenceSessionCallbacks)
    }
}