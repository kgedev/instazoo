package com.example.instazoo.entity

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "comments")
class Comment(
    @ManyToOne(fetch = FetchType.EAGER)
    val post: Post,
    @Column(nullable = false)
    val username: String,
    @Column(nullable = false)
    val userId: Long,
    @Column(columnDefinition = "text", nullable = false)
    val message: String,
    @Column(nullable = false)
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    var createdDate: LocalDateTime
) : BaseEntity<Long>(){
    @PrePersist
    private fun onCreate() {
        this.createdDate = LocalDateTime.now()
    }
}
