package com.example.instazoo.entity

import com.example.instazoo.entity.enum.ERole
import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.security.core.GrantedAuthority
import java.time.LocalDateTime
import javax.persistence.*
import kotlin.jvm.Transient
import org.springframework.security.core.userdetails.UserDetails as UserDetails

@Entity
@Table(name = "users")
class User(
    @Column(nullable = false)
    val name: String? = null,
    @Column(unique = true, updatable = false)
    private val username: String,
    @Column(nullable = false)
    val lastname: String? = null,
    @Column(unique = true)
    val email: String,
    @Column(columnDefinition = "TEXT")
    val bio: String? = null,
    @Column(length = 3000)
    private val password: String,
    @ElementCollection(targetClass = ERole::class)
    @CollectionTable(name = "user_role", joinColumns = [JoinColumn(name = "user_id")])
    val role: Set<ERole> = mutableSetOf(),
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
    val posts: MutableList<Post> = mutableListOf(),
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    @Column(updatable = false)
    var createdDate: LocalDateTime? = null,
    @Transient
    private val authorities: MutableCollection<out GrantedAuthority>? = null
) : BaseEntity<Long>(), UserDetails {

    @PrePersist
    private fun onCreate() {
        createdDate = LocalDateTime.now()
    }

    /**
     * SECURITY
     * */

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return authorities!!
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }

    override fun isAccountNonExpired(): Boolean {
       return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}
