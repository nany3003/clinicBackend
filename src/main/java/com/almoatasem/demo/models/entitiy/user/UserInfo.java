package com.almoatasem.demo.models.entitiy.user;

import com.almoatasem.demo.models.entitiy.Role;
import com.almoatasem.demo.models.enums.GENDER;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
@Data
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@DiscriminatorColumn(name = "user_type")
@Table(name = "users")
public abstract class UserInfo implements UserDetails {

    public UserInfo(String username, String email, String password, Set<Role> authorities) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }
    public UserInfo(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Setter(AccessLevel.NONE)
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "date_of_birth")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private GENDER gender;

    @Column(unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role_junction",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Role> authorities = new HashSet<>();

    @CreatedDate
    @Setter(AccessLevel.NONE)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createDate;

    @LastModifiedDate
    @Setter(AccessLevel.NONE)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime lastModifiedDate;

    private boolean isAccountNonExpired = true;

    private boolean isAccountNonLocked = true;

    private boolean isCredentialsNonExpired = true;

    private boolean isEnabled = true;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return Objects.equals(id, userInfo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void addRole(Role role) {
        authorities.add(role);
    }
    public void removeRole(Role role) {
        authorities.remove(role);
    }


}