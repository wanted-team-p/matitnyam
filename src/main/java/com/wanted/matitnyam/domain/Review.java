package com.wanted.matitnyam.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Review {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long seq;

    @ManyToOne
    @JoinColumn(referencedColumnName = "seq")
    Member member;

    @ManyToOne
    @JoinColumn(referencedColumnName = "seq")
    Restaurant restaurant;

    Integer rating;

    String opinion;

    @Builder
    public Review(final Long seq, final Member member, final Restaurant restaurant, final Integer rating,
                  final String opinion) {
        this.seq = seq;
        this.member = member;
        this.restaurant = restaurant;
        this.rating = rating;
        this.opinion = opinion;
    }

}