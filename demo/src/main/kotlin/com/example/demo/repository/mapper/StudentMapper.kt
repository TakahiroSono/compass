package com.example.demo.repository.mapper

import com.example.demo.model.db.StudentDTO
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.SelectProvider
import org.apache.ibatis.jdbc.SQL

@Mapper
interface StudentMapper {
    @SelectProvider(type = StudentProvider::class, method = "findByFacilitator")
    fun findByFacilitator(
            @Param("id") id: Int,
            @Param("page") page: Int?,
            @Param("limit") limit: Int?,
            @Param("sort") sort: String?,
            @Param("order") order: String?,
            @Param("name_like") nameLike: String?,
            @Param("loginid_like") loginIdLike: String?,
    ): List<StudentDTO>

    class StudentProvider {
        companion object {
            @JvmStatic
            fun findByFacilitator(
                    @Param("id") id: Int,
                    @Param("page") page: Int?,
                    @Param("limit") limit: Int?,
                    @Param("sort") sort: String?,
                    @Param("order") order: String?,
                    @Param("name_like") nameLike: String?,
                    @Param("loginid_like") loginIdLike: String?,
            ): String = object : SQL() {
                init {
                    SELECT(
                            "S.id as student_id",
                            "S.name as student_name",
                            "S.login_id",
                            "C.id as class_room_id",
                            "C.name as class_room_name")
                    FROM("facilitator as F")
                    LEFT_OUTER_JOIN("class_room C on C.facilitator_id = F.id", "student S on S.class_room_id = C.id")
                    WHERE("F.id = #{id}")
                    limit?.let { LIMIT("$it") }
                    page?.let { OFFSET("${it * (limit ?: 0)}") }
                    sort?.let {
                        val o = when (order) {
                            "asc", "desc" -> order
                            else -> ""
                        }
                        when (it) {
                            "name" -> ORDER_BY("S.name $o")
                            "loginId" -> ORDER_BY("S.login_id $o")
                            else -> return@let
                        }
                    }
                    nameLike?.let { WHERE("S.name like '%$it%'") }
                    loginIdLike?.let { WHERE("S.login_id like '%$it%'") }
                }
            }.toString()
        }
    }
}
