package com.teamsparta.courseregistration2.domain.repository

import com.teamsparta.courseregistration2.domain.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {
    fun findByTaskId(taskId: Long): List<Comment>
}