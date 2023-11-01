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

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRestaurant restaurant = new QRestaurant("restaurant");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath address = createString("address");

    public final QBusinessType businessType;

    public final NumberPath<Float> latitude = createNumber("latitude", Float.class);

    public final StringPath licenseNumber = createString("licenseNumber");

    public final NumberPath<Float> longitude = createNumber("longitude", Float.class);

    public final StringPath name = createString("name");

    public final BooleanPath outOfBusiness = createBoolean("outOfBusiness");

    public final StringPath roadNameAddress = createString("roadNameAddress");

    //inherited
    public final NumberPath<Long> seq = _super.seq;

    public final StringPath zipCode = createString("zipCode");

    public QRestaurant(String variable) {
        this(Restaurant.class, forVariable(variable), INITS);
    }

    public QRestaurant(Path<? extends Restaurant> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRestaurant(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRestaurant(PathMetadata metadata, PathInits inits) {
        this(Restaurant.class, metadata, inits);
    }

    public QRestaurant(Class<? extends Restaurant> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.businessType = inits.isInitialized("businessType") ? new QBusinessType(forProperty("businessType"), inits.get("businessType")) : null;
    }

}

