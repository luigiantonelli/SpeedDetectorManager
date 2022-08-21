package it.uniroma1.commons.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    private String username;

    @OneToMany(mappedBy = "user")
    private List<Fine> fines;

    @Column(name = "region")
    private String region;

    @Column(name = "passwordSalt")
    private String passwordSalt;

    @Column(name = "passwordHash")
    private String passwordHash;

    @Column(name = "role")
    private Role role;

    @Column(name = "activationCode")
    private String activationCode;

    @Column(name = "active")
    private boolean active;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @OneToMany(mappedBy = "creator")
    private List<User> users;

    @ManyToOne
    @JoinColumn(name="creator_id",referencedColumnName = "username")
    private User creator;

}