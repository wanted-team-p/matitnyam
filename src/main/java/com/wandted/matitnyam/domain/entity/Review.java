package com.wandted.matitnyam.domain.entity;


import java.io.Serial;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
 * @description review
 * @since 2023.11.01
 **********************************************************************************************************************/
@Getter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "tb_review")
public class Review extends AbstractEntity{

    @Serial
    private static final long serialVersionUID = -8306631523913350006L;


    private Integer rating;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_seq", nullable = false)
    private User user;

    @Builder
    public Review(Integer rating, String content, User user) {
        this.rating = rating;
        this.content = content;
        this.user = user;
    }
}