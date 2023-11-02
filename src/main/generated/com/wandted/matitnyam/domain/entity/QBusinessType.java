package com.wandted.matitnyam.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBusinessType is a Querydsl query type for BusinessType
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBusinessType extends EntityPathBase<BusinessType> {

    private static final long serialVersionUID = -962407761L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBusinessType businessType = new QBusinessType("businessType");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath name = createString("name");

    public final QRestaurant restaurant;

    //inherited
    public final NumberPath<Long> seq = _super.seq;

    public QBusinessType(String variable) {
        this(BusinessType.class, forVariable(variable), INITS);
    }

    public QBusinessType(Path<? extends BusinessType> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBusinessType(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBusinessType(PathMetadata metadata, PathInits inits) {
        this(BusinessType.class, metadata, inits);
    }

    public QBusinessType(Class<? extends BusinessType> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.restaurant = inits.isInitialized("restaurant") ? new QRestaurant(forProperty("restaurant")) : null;
    }

}

