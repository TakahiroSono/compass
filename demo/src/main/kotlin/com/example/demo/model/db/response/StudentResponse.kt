package com.example.demo.model.db.response

import com.example.demo.entity.Student

data class StudentResponse(
        val students: List<Student>,
        val totalCount: Int,
)
