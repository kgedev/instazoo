package com.example.instazoo.entity

import com.example.instazoo.entity.enum.ERole
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "posts")
class Post(
    val title: String,
    val caption: String,
    val location: String,
    val likes: Int,
    @Column
    @ElementCollection(targetClass = String::class)
    val likedUsers: MutableSet<String> = mutableSetOf(),
    @ManyToOne(fetch = FetchType.LAZY)
    val user: User,
    @OneToMany(cascade = [CascadeType.REFRESH], fetch = FetchType.EAGER, mappedBy = "post", orphanRemoval = true)
    val comment: MutableList<Comment> = mutableListOf(),
    @Column(updatable = false)
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    var createdDate: LocalDateTime
) : BaseEntity<Long>() {
    @PrePersist
    private fun onCreate() {
        this.createdDate = LocalDateTime.now()
    }
}
