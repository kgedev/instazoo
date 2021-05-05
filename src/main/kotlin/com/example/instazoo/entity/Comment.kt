package com.example.instazoo.entity

import java.time.LocalDateTime
import javax.persistence.PrePersist

data class Comment(
    val id: Long,
    val post: Post,
    val username: String,
    val userId: Long,
    val message: String,
    var createdDate: LocalDateTime
) {
    @PrePersist
    private fun onCreate() {
        this.createdDate = LocalDateTime.now()
    }
}
