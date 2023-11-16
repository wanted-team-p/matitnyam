package com.wandted.matitnyam.domain.entity;


import java.io.Serial;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author 민경수
 * @description user
 * @since 2023.11.01
 **********************************************************************************************************************/
@Getter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "tb_user")
public class User extends AbstractEntity {

    @Serial
    private static final long serialVersionUID = 513834638556181945L;


    @Column(name = "id", nullable = false)
    private String id;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "longitude", nullable = false)
    private Double longitude;
    @Column(name = "latitude", nullable = false)
    private Double latitude;
    @Column(name = "suggest_alarm", nullable = false)
    private Boolean suggestAlarm;

    @Builder
    public User(String id, String password, String address, Double longitude, Double latitude, Boolean suggestAlarm) {
        this.id = id;
        this.password = password;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.suggestAlarm = suggestAlarm;
    }

}