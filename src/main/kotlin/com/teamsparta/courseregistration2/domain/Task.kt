package com.teamsparta.courseregistration2.domain

import com.fasterxml.jackson.annotation.JsonView
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Task(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var title: String = "",
    var content: String = "",
    var writer: String = "",
    var createdAt: LocalDateTime = LocalDateTime.now(),

    // 완료 여부 필드 추가
    var isCompleted: Boolean = false,

    @JsonView(Views.Detail::class)
    @OneToMany(mappedBy = "task", cascade = [CascadeType.ALL], orphanRemoval = true)
    var comments: List<Comment> = ArrayList()
) {
    constructor() : this(id = null)

    // 완료 상태 변경 메소드 추가
    fun completeTask() {
        this.isCompleted = true
    }
}

interface Views {
    interface Summary
    interface Detail : Summary
}