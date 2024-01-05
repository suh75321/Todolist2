package com.teamsparta.courseregistration2.domain.repository

import com.teamsparta.courseregistration2.domain.Task
import org.springframework.data.jpa.repository.JpaRepository

interface TaskRepository : JpaRepository<Task, Long>