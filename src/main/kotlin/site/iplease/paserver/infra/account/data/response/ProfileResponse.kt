package site.iplease.paserver.infra.account.data.response

import site.iplease.paserver.infra.account.data.type.AccountType
import site.iplease.paserver.infra.account.data.type.DepartmentType
import site.iplease.paserver.infra.account.data.type.PermissionType

data class ProfileResponse (
    val type: AccountType,
    val common: CommonProfileResponse,
    val student: StudentProfileResponse?,
    val teacher: TeacherProfileResponse?
)

data class CommonProfileResponse (
    val accountId: Long,
    val permission: PermissionType,
    val name: String,
    val email: String,
    val profileImage: String
)

data class StudentProfileResponse (
    val studentNumber: Int,
    val department: DepartmentType
)

class TeacherProfileResponse()