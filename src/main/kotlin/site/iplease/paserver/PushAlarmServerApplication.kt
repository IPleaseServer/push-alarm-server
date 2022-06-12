package site.iplease.paserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PushAlarmServerApplication

fun main(args: Array<String>) {
	runApplication<PushAlarmServerApplication>(*args)
}
