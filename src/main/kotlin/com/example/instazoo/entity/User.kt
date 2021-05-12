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
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(nullable = false)
    val name: String,
    @Column(unique = true, updatable = false)
    private val username: String,
    @Column(nullable = false)
    val lastname: String,
    @Column(unique = true)
    val email: String,
    @Column(columnDefinition = "TEXT")
    val bio: String,
    @Column(length = 3000)
    private val password: String,
    @ElementCollection(targetClass = ERole::class)
    @CollectionTable(name = "user_role", joinColumns = [JoinColumn(name = "user_id")])
    val role: Set<ERole> = mutableSetOf(),
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
    val posts: MutableList<Post> = mutableListOf(),
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    @Column(updatable = false)
    var createdDate: LocalDateTime
) : UserDetails {

    @Transient
    private val authorities: MutableCollection<out GrantedAuthority>? = null

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
