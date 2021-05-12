package com.example.instazoo.entity

import com.example.instazoo.entity.enum.ERole
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(nullable = false)
    val name: String,
    @Column(unique = true, updatable = false)
    val username: String,
    @Column(nullable = false)
    val lastname: String,
    @Column(unique = true)
    val email: String,
    @Column(columnDefinition = "TEXT")
    val bio: String,
    @Column(length = 3000)
    val password: String,
    @ElementCollection(targetClass = ERole::class)
    @CollectionTable(name = "user_role", joinColumns = [JoinColumn(name = "user_id")])
    val role: Set<ERole> = mutableSetOf(),
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
    val posts: MutableList<Post> = mutableListOf(),
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    @Column(updatable = false)
    var createdDate: LocalDateTime
) {
    @PrePersist
    private fun onCreate() {
        createdDate = LocalDateTime.now()
    }
}
