package com.teamsparta.courseregistration2.domain.dto

import com.teamsparta.courseregistration2.domain.Comment
import java.time.LocalDateTime

data class CommentDto(
    val id: Long?,
    val content: String?,
    val author: String?,
    val createdDate: LocalDateTime?
) {
    constructor(comment: Comment) : this(
        id = comment.id,
        content = comment.content,
        author = comment.author,
        createdDate = comment.createdDate ?: LocalDateTime.now()
    )
}
data class CreateCommentDto(
    val content: String,
    val author: String,
    val password: String
)
data class UpdateCommentDto(
    val id: Long,
    val content: String,
    val password: String
)