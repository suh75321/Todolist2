package com.teamsparta.courseregistration2.domain.controller

import com.teamsparta.courseregistration2.domain.Task
import com.teamsparta.courseregistration2.domain.dto.CommentDto
import com.teamsparta.courseregistration2.domain.service.TaskService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tasks")
class TaskController(private val taskService: TaskService) {

    @GetMapping
    fun getAllTasks(): ResponseEntity<List<Task>> {
        val tasks = taskService.getAllTasks()
        return ResponseEntity(tasks, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getTaskById(@PathVariable id: Long): ResponseEntity<Task> {
        val task = taskService.getTaskById(id)
        return task.map { t -> ResponseEntity(t, HttpStatus.OK) }
            .orElse(ResponseEntity(HttpStatus.NOT_FOUND))
    }

    @PostMapping
    fun createTask(@RequestBody task: Task): ResponseEntity<Task> {
        val newTask = taskService.createTask(task)
        return ResponseEntity(newTask, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateTask(@PathVariable id: Long, @RequestBody task: Task): ResponseEntity<Task> {
        val updatedTask = taskService.updateTask(id, task)
        return updatedTask.map { t -> ResponseEntity(t, HttpStatus.OK) }
            .orElse(ResponseEntity(HttpStatus.NOT_FOUND))
    }

    @DeleteMapping("/{id}")
    fun deleteTask(@PathVariable id: Long): ResponseEntity<Void> {
        taskService.deleteTask(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
    @DeleteMapping
    fun deleteAllTasks(): ResponseEntity<Void> {
        taskService.deleteAllTasks()
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
    @PostMapping("/{id}/comments") // New endpoint to add a comment to a task
    fun addCommentToTask(@PathVariable id: Long, @RequestBody comment: CommentDto): ResponseEntity<Task> {
        val task = taskService.getTaskById(id)
        if(task.isPresent) {
            val updatedTask = taskService.addCommentToTask(task.get(), comment)
            return ResponseEntity(updatedTask, HttpStatus.OK)
        } else {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

}