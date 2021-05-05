package com.example.instazoo.entity

import com.example.instazoo.entity.enum.ERole
import java.time.LocalDateTime
import javax.persistence.PrePersist

data class User(
    val id: Long,
    val name: String,
    val username: String,
    val lastname: String,
    val email: String,
    val bio: String,
    val password: String,
    val role: MutableSet<ERole> = mutableSetOf(),
    val posts: MutableList<Post> = mutableListOf(),
    var createdDate: LocalDateTime
) {
    @PrePersist
    private fun onCreate() {
        createdDate = LocalDateTime.now()
    }
}
