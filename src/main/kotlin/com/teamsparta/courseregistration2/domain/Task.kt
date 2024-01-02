package com.teamsparta.courseregistration2.domain

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Task(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var title: String,
    var content: String,
    var writer: String,
    var createdAt: LocalDateTime = LocalDateTime.now(),
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    var comments: List<Comment> = mutableListOf() // Add comments field to Task
)

@Entity
data class Comment(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var author: String,
    var password: String, // Add password field to Comment
    var content: String
)