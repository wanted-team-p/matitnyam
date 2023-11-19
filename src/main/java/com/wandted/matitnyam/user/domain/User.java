package com.wandted.matitnyam.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Comment;

@Getter
@Entity
@Table(name = "tb_user")
public class User {

    @Id
    private String id;

    @Comment("사용자명")
    private String username;

    @Comment("비밀번호")
    @Column(nullable = false)
    private String password;

    @Comment("주소")
    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    @Comment("위도")
    private Long latitude;

    @Comment("경도")
    @Column(nullable = false)
    private Long longitude;

    @Enumerated(EnumType.STRING)
    @Comment("권한")
    @Column(nullable = false)
    private Role role;

    @Comment("점심추천여부")
    @Column(nullable = false)
    private Boolean useRecommendLunch;

    @Comment("사용여부")
    @Column(nullable = false)
    private Boolean isActive;

}
