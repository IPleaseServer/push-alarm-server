package site.iplease.paserver.global.alarm.subscriber

import site.iplease.paserver.global.alarm.data.message.SendAlarmMessage

interface SendAlarmSubscriber {
    fun subscribe(message: SendAlarmMessage)
}