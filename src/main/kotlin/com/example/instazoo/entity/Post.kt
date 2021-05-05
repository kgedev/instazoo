package com.example.instazoo.entity

import java.time.LocalDateTime
import javax.persistence.PrePersist

data class Post(
    val id: Long,
    val title: String,
    val caption: String,
    val location: String,
    val likes: Int,
    val likedUsers: MutableSet<String> = mutableSetOf(),
    val user: User,
    val comment: MutableList<Comment> = mutableListOf(),
    var createdDate: LocalDateTime
) {
    @PrePersist
    private fun onCreate() {
        this.createdDate = LocalDateTime.now()
    }
}
