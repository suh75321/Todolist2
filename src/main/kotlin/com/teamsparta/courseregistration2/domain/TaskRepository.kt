package com.teamsparta.courseregistration2.domain

import org.springframework.data.jpa.repository.JpaRepository

interface TaskRepository : JpaRepository<Task, Long>