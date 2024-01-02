package com.teamsparta.courseregistration2.domain.dto

import java.time.LocalDateTime

data class TaskDto(
    var id: Long? = null,
    var title: String,
    var content: String,
    var writer: String,
    var createdAt: LocalDateTime = LocalDateTime.now()
)