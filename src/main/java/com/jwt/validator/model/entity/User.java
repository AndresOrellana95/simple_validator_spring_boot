package com.jwt.validator.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name="user")
public class User {
    @Id
    @Column(name="id_user", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="IdGeneratorU")
    @SequenceGenerator(name="IdGeneratorU", sequenceName="user_seq")
    private Long idUser;
    @Column(name = "username", nullable=false, length = 50)
    private String username;
    @Column(name = "password", nullable=false, length = 100)
    private String password;
    @ManyToOne
    @JoinColumn(name ="id_platform", nullable = false)
    private Platform idPlatform;


    public Long getIdUser() {
        return this.idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Platform getIdPlatform() {
        return this.idPlatform;
    }

    public void setIdPlatform(Platform idPlatform) {
        this.idPlatform = idPlatform;
    }

}
