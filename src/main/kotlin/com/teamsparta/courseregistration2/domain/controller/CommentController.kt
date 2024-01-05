package com.teamsparta.courseregistration2.domain.controller

import com.teamsparta.courseregistration2.domain.Comment
import com.teamsparta.courseregistration2.domain.repository.CommentRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/comments")
class CommentController(private val commentRepository: CommentRepository) {
    @PostMapping
    fun createComment(@RequestBody comment: Comment): ResponseEntity<Comment> {
        val savedComment = commentRepository.save(comment)
        return ResponseEntity.ok(savedComment)
    }

    @GetMapping("/{commentId}")
    fun getComment(@PathVariable commentId: Long): ResponseEntity<Comment> {
        val comment = commentRepository.findById(commentId)
            .orElseThrow { IllegalArgumentException("Comment not found with id $commentId") }
        return ResponseEntity.ok(comment)
    }

    @PutMapping("/{commentId}")
    fun updateComment(@PathVariable commentId: Long, @RequestBody commentRequest: Comment): ResponseEntity<Comment> {
        val existingComment = commentRepository.findById(commentId)
            .orElseThrow { IllegalArgumentException("Comment not found with id $commentId") }
        existingComment.content = commentRequest.content
        val updatedComment = commentRepository.save(existingComment)
        return ResponseEntity.ok(updatedComment)
    }

    @DeleteMapping("/{commentId}")
    fun deleteComment(@PathVariable commentId: Long): ResponseEntity<Void> {
        val comment = commentRepository.findById(commentId)
            .orElseThrow { IllegalArgumentException("Comment not found with id $commentId") }
        commentRepository.delete(comment)
        return ResponseEntity.ok().build()
    }
}