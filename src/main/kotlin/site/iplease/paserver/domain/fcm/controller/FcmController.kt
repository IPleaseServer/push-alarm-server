package site.iplease.paserver.domain.fcm.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import site.iplease.paserver.domain.fcm.data.request.AddTokenRequest
import site.iplease.paserver.domain.fcm.service.FcmService

@RestController
@RequestMapping("/api/v1/alarm/fcm")
class FcmController(
    private val fcmService: FcmService
) {
    @PostMapping("/token")
    fun addToken(@RequestHeader("X-Authorization-Id") issuerId: Long,
                 @RequestBody request: AddTokenRequest): Mono<ResponseEntity<Unit>> =
        fcmService.addToken(issuerId, request.token)
            .map { ResponseEntity.ok(it) }

    @DeleteMapping("/token")
    fun removeToken(@RequestHeader("X-Authorization-Id") issuerId: Long): Mono<ResponseEntity<Unit>> =
        fcmService.removeToken(issuerId)
            .map { ResponseEntity.noContent().build() }
}