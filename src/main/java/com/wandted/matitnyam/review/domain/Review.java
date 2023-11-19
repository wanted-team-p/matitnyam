package com.wandted.matitnyam.review.domain;

import com.wandted.matitnyam.restaurant.domain.Restaurant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Comment;
@Getter
@Table(name = "tb_review")
@Entity
public class Review {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("식당")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="restaurant_id", referencedColumnName = "id")
    private Restaurant restaurant;

    @Comment("평점")
    @Column(nullable = false)
    private int rate;

    @Comment("후기 내용")
    @Column(nullable = false)
    private String comment;
}
