# PushAlarm Service
> 클라이언트에게 알림을 보내기 위한 서비스 

FCM, email등, 다양한 방식으로 알림을 보낼 수 있도록 설계되었습니다.

## MessageSubscription
알람 전송에 실패할시 재시도하기위해, MessageQueue를 통해 알림요청을 수신받습니다.
### sendAlarmEvent
```json5
//routing key: sendAlarmRequested
{
  "type": "FCM", //알림을 보낼 방식입니다. (EnumType)
  "receiver": 12, //알람을 수신할 계정의 id입니다.
  "title": "알람의 제목입니다!",
  "description": "알람의 내용입니다. 제목보다 더 자세한 내용을 담고 있습니다."
}
```