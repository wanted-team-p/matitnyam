package com.wandted.matitnyam.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRestaurant is a Querydsl query type for Restaurant
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRestaurant extends EntityPathBase<Restaurant> {

    private static final long serialVersionUID = 2097617938L;

    public static final QRestaurant restaurant = new QRestaurant("restaurant");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath address = createString("address");

    public final SetPath<BusinessType, QBusinessType> businessType = this.<BusinessType, QBusinessType>createSet("businessType", BusinessType.class, QBusinessType.class, PathInits.DIRECT2);

    public final NumberPath<Double> latitude = createNumber("latitude", Double.class);

    public final StringPath licenseNumber = createString("licenseNumber");

    public final NumberPath<Double> longitude = createNumber("longitude", Double.class);

    public final StringPath name = createString("name");

    public final BooleanPath outOfBusiness = createBoolean("outOfBusiness");

    public final SetPath<Review, QReview> reviews = this.<Review, QReview>createSet("reviews", Review.class, QReview.class, PathInits.DIRECT2);

    public final StringPath roadNameAddress = createString("roadNameAddress");

    //inherited
    public final NumberPath<Long> seq = _super.seq;

    public final StringPath zipCode = createString("zipCode");

    public QRestaurant(String variable) {
        super(Restaurant.class, forVariable(variable));
    }

    public QRestaurant(Path<? extends Restaurant> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRestaurant(PathMetadata metadata) {
        super(Restaurant.class, metadata);
    }

}

