package com.example.demo.repository.impl

import com.example.demo.entity.ClassRoom
import com.example.demo.entity.Student
import com.example.demo.repository.StudentRepository
import com.example.demo.repository.mapper.StudentMapper
import org.springframework.stereotype.Repository

@Repository
class StudentRepositoryImpl(
        private val studentMapper: StudentMapper,
) : StudentRepository {
    override fun getStudentByFacilitatorId(
            facilitatorId: Int,
            page: Int?,
            limit: Int?,
            sort: String?,
            order: String?,
            nameLike: String?,
            loginIdLike: String?,
    ): List<Student> {
        return studentMapper.findByFacilitator(
                facilitatorId,
                page,
                limit,
                sort,
                order,
                nameLike,
                loginIdLike,
        ).map {
            Student(
                    id = it.studentId,
                    name = it.studentName,
                    loginId = it.loginId,
                    classRoom = ClassRoom(
                            id = it.classRoomId,
                            name = it.classRoomName
                    ),
            )
        }
    }
}
