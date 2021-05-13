package com.example.instazoo.entity

import net.minidev.json.annotate.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "images")
class Image(
    @Column(nullable = false)
    val name: String,
    @Lob
    @Column(columnDefinition = "BYTEA")
    val byte: ByteArray,
    @JsonIgnore
    val userId: Long,
    @JsonIgnore
    val postId: Long
) : BaseEntity<Long>() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Image

        if (id != other.id) return false
        if (name != other.name) return false
        if (!byte.contentEquals(other.byte)) return false
        if (userId != other.userId) return false
        if (postId != other.postId) return false

        return true
    }
}
