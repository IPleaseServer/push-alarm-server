package site.iplease.paserver.global.alarm.data.message

import site.iplease.paserver.global.alarm.data.type.AlarmType

data class SendAlarmMessage (
    val type: AlarmType,
    val receiverId: Long,
    val title: String,
    val description: String
)
