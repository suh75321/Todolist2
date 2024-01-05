package com.teamsparta.courseregistration2.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
class Comment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = true)
    var content: String? = null,

    @Column(nullable = true)
    var author: String? = null,

    @Column(nullable = true)
    var password: String? = null,

    @Column(nullable = false)
    var createdDate: LocalDateTime = LocalDateTime.now()
) {
    // Task 정보는 JSON으로 변환 시 제외
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    var task: Task? = null

    constructor(content: String, author: String, password: String): this(null, content, author, password, LocalDateTime.now())
}