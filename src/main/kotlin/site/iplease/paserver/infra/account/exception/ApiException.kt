package site.iplease.paserver.infra.account.exception

data class ApiException(private val errorDetail: String) : RuntimeException(ERROR_MESSAGE + errorDetail) {
    companion object { const val ERROR_MESSAGE = "API 요청중 오류가 발생하였습니다!" }
}