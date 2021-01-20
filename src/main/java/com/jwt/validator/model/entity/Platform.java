package com.jwt.validator.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="platform")
public class Platform {
    @Id
    @Column(name="id_platform", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="IdGeneratorP")
    @SequenceGenerator(name="IdGeneratorP", sequenceName="id_platform_seq")
    private Long idPlatform;
    @Column(name = "keyValue", nullable=false, length = 10)
    private String keyValue;
    @Column(name = "value1", nullable=false, length = 10)
    private String value1;
    @Column(name = "value2", nullable=false, length = 10)
    private String value2;


    public Long getIdPlatform() {
        return this.idPlatform;
    }

    public void setIdPlatform(Long idPlatform) {
        this.idPlatform = idPlatform;
    }

    public String getKeyValue() {
        return this.keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public String getValue1() {
        return this.value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return this.value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

}
