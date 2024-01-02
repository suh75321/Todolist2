package com.teamsparta.courseregistration2.domain.dto

data class CommentDto(
    val author: String,
    val password: String,  // password 필드 추가
    val content: String
)