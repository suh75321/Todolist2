package com.teamsparta.courseregistration2.domain

data class CommentRequest(
    val content: String,
    val author: String,
    val password: String,
    val taskId: Long
)