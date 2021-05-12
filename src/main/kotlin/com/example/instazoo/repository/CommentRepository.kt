package com.example.instazoo.repository

import com.example.instazoo.entity.Comment
import com.example.instazoo.entity.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository: JpaRepository<Comment, Long> {

    fun findAllByPost(post: Post): List<Comment>

    fun findByIdAndUserId(commentId: Long): Comment
}