package com.teamsparta.courseregistration2.domain.service

import com.teamsparta.courseregistration2.domain.Comment
import com.teamsparta.courseregistration2.domain.dto.CommentDto
import com.teamsparta.courseregistration2.domain.dto.CreateCommentDto
import com.teamsparta.courseregistration2.domain.dto.UpdateCommentDto
import com.teamsparta.courseregistration2.domain.repository.CommentRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CommentService(
    private val commentRepository: CommentRepository
) {
    fun createComment(dto: CreateCommentDto): CommentDto {
        val comment = Comment(
            content = dto.content,
            author = dto.author,
            password = dto.password,
            createdDate = LocalDateTime.now()
        )
        commentRepository.save(comment)
        return CommentDto(
            id = comment.id,
            content = comment.content,
            author = comment.author,
            createdDate = comment.createdDate
        )
    }

    fun getAllComments(): List<CommentDto> {
        return commentRepository.findAll().map { comment ->
            CommentDto(
                id = comment.id,
                content = comment.content,
                author = comment.author,
                createdDate = comment.createdDate
            )
        }
    }

    fun updateComment(dto: UpdateCommentDto): CommentDto {
        val comment = commentRepository.findById(dto.id)
            .orElseThrow { NoSuchElementException("No comment found with id ${dto.id}") }
        if (comment.password != dto.password) {
            throw IllegalArgumentException("Invalid password")
        }
        comment.content = dto.content
        commentRepository.save(comment)
        return CommentDto(
            id = comment.id,
            content = comment.content,
            author = comment.author,
            createdDate = comment.createdDate
        )
    }

    fun deleteComment(id: Long, password: String) {
        val comment = commentRepository.findById(id)
            .orElseThrow { NoSuchElementException("No comment found with id $id") }
        if (comment.password != password) {
            throw IllegalArgumentException("Invalid password")
        }
        commentRepository.delete(comment)
    }
}