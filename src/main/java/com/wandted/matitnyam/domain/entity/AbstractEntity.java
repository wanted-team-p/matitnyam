package com.wandted.matitnyam.domain.entity;


import java.io.Serial;
import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * @author 민경수
 * @description abstract entity
 * @since 2023.11.01
 **********************************************************************************************************************/
@Getter
@EqualsAndHashCode(callSuper = false, of = {"seq"})
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -5785158509172208133L;

    /**
     * 일련번호
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    protected Long seq;

}