package site.iplease.paserver.domain.fcm.data.event

data class SendFcmRequestedEvent(
    val receiverId: Long,
    val title: String,
    val content: String
)