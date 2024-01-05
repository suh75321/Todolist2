package com.teamsparta.courseregistration2.domain.dto

import com.teamsparta.courseregistration2.domain.Comment
import java.time.LocalDateTime

data class TaskDto(
    var id: Long? = null,
    var title: String,
    var content: String,
    var writer: String,
    var createdAt: LocalDateTime = LocalDateTime.now()
)
data class TaskDetails(

    val id: Long?,

    val title: String,

    val content: String,

    val writer: String,

    val createdAt: LocalDateTime?,

    val comments: List<Comment>
)