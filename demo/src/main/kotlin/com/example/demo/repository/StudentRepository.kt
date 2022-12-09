package com.example.demo.repository

import com.example.demo.entity.Student

interface StudentRepository {
    fun getStudentByFacilitatorId(
            facilitatorId: Int,
            page: Int?,
            limit: Int?,
            sort: String?,
            order: String?,
            nameLike: String?,
            loginIdLike: String?,
    ): List<Student>
}
