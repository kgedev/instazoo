package com.example.instazoo.repository

import com.example.instazoo.entity.Image
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ImageRepository: JpaRepository<Image, Long> {

    fun findByUserId(userId: Long): Optional<Image>

    fun findByPostId(postId: Long): Optional<Image>

}