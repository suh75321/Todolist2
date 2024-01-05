package com.teamsparta.courseregistration2.domain.controller

import com.fasterxml.jackson.annotation.JsonView
import com.teamsparta.courseregistration2.domain.Task
import com.teamsparta.courseregistration2.domain.Views
import com.teamsparta.courseregistration2.domain.repository.CommentRepository
import com.teamsparta.courseregistration2.domain.repository.TaskRepository
import com.teamsparta.courseregistration2.domain.service.TaskService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/tasks")
class TaskController(
    private val taskService: TaskService,
    private val taskRepository: TaskRepository,
    private val commentRepository: CommentRepository
) {

    @JsonView(Views.Summary::class)
    @GetMapping("")
    fun getTasks(): ResponseEntity<List<Task>> {
        val tasks = taskService.getAllTasks()
        return ResponseEntity(tasks, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getTask(@PathVariable id: Long): ResponseEntity<Task> {
        val task = taskService.getTaskById(id).orElseThrow { NoSuchElementException("Task with id $id not found") }
        return ResponseEntity(task, HttpStatus.OK)
    }

    @PostMapping("")
    fun createTask(@RequestBody task: Task): ResponseEntity<Task> {
        val newTask = taskService.createTask(task)
        return ResponseEntity(newTask, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateTask(@PathVariable id: Long, @RequestBody task: Task): ResponseEntity<Optional<Task>> {
        val updatedTask = taskService.updateTask(id, task)
        return ResponseEntity(updatedTask, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteTask(@PathVariable id: Long): ResponseEntity<Void> {
        taskService.deleteTask(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @PutMapping("/{id}/complete")
    fun completeTask(@PathVariable id: Long): ResponseEntity<Task> {
        val task = taskService.getTaskById(id).orElseThrow { NoSuchElementException("Task with id $id not found") }

        val completedTask = taskService.updateTask(id, task).orElseThrow { NoSuchElementException("Task with id $id not found after update") }
        return ResponseEntity(completedTask, HttpStatus.OK)
    }

    @DeleteMapping("")
    fun deleteAllTasks(): ResponseEntity<Void> {
        taskService.deleteAllTasks()
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}