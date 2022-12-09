package com.example.demo.controller

import com.example.demo.model.db.response.StudentResponse
import com.example.demo.repository.StudentRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping()
class SampleController(
        private val studentRepository: StudentRepository,
) {
    @GetMapping("student")
    fun getStudent(
            @RequestParam("facilitator_id") facilitatorId: Int,
            @RequestParam("page") page: Int?,
            @RequestParam("limit") limit: Int?,
            @RequestParam("sort") sort: String?,
            @RequestParam("order") order: String?,
            @RequestParam("name_like") nameLike: String?,
            @RequestParam("loginid_like") loginIdLike: String?,
    ): StudentResponse {
        val students = studentRepository.getStudentByFacilitatorId(
                facilitatorId,
                page,
                limit,
                sort,
                order,
                nameLike,
                loginIdLike
        )
        return StudentResponse(
                students,
                totalCount = students.count()
        )
    }
}
