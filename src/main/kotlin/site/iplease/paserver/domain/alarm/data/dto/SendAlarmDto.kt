package site.iplease.paserver.domain.alarm.data.dto

import site.iplease.paserver.global.alarm.data.type.AlarmType

data class SendAlarmDto (
    val type: AlarmType,
    val receiverId: Long,
    val title: String,
    val description: String
)
