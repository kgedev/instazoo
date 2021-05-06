package com.example.instazoo.entity

import net.minidev.json.annotate.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "images")
data class Image(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(nullable = false)
    val name: String,
    @Lob
    @Column(columnDefinition = "BYTEA")
    val byte: ByteArray,
    @JsonIgnore
    val userId: Long,
    @JsonIgnore
    val postId: Long
) {
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

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + byte.contentHashCode()
        result = 31 * result + userId.hashCode()
        result = 31 * result + postId.hashCode()
        return result
    }
}
