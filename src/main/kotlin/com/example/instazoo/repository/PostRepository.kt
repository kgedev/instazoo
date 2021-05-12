package com.example.instazoo.repository

import com.example.instazoo.entity.Post
import com.example.instazoo.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PostRepository: JpaRepository<Post, Long> {

    //SELECT * FROM POST as p WHERE User='user' SORT DESC
    fun findAllByUserOrderByCreatedDateDesc(user: User): List<Post>

    fun findAllByOrderByCreatedDateDesc(): List<Post>

    fun findPostById(postId: Long, user: User): Optional<Post>

}