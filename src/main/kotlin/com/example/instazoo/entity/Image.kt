package com.example.instazoo.entity

data class Image(
    val id: Long,
    val name: String,
    val byte: ByteArray,
    val userId: Long,
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
