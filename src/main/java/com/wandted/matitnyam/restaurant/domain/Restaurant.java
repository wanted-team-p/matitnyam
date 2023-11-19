package com.wandted.matitnyam.restaurant.domain;

import com.wandted.matitnyam.region.domain.District;
import com.wandted.matitnyam.restaurant.dto.RestaurantDetailResponse;
import com.wandted.matitnyam.restaurant.dto.RestaurantListResponse;
import com.wandted.matitnyam.review.domain.Review;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import org.hibernate.annotations.Comment;

@Table(name = "tb_restaurant")
@Entity
public class Restaurant {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Comment("식당명")
    private String name;

    @Comment("시군구")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="district_id",referencedColumnName = "id")
    private District district;

    @Column(nullable = false)
    @Comment("주소")
    private String address;

    @Column(nullable = false)
    @Comment("위도")
    private Long latitude;

    @Column(nullable = false)
    @Comment("경도")
    private Long longitude;

    @OneToMany(mappedBy = "restaurant")
    List<Review> reviews;

    public RestaurantDetailResponse toDetailResponse(){
        return new RestaurantDetailResponse(id, name, district, address, latitude, longitude);
    }

    public RestaurantListResponse toListResponse(){
        return new RestaurantListResponse(id, name, latitude, longitude);
    }

}
