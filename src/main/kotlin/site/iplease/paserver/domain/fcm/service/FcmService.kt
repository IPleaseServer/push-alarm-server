package site.iplease.paserver.domain.fcm.service

interface FcmService {
    fun sendFcm(token: String, title: String, content: String)
}
