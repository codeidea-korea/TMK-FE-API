package com.binoofactory.alma.almabe.api.user.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUsers is a Querydsl query type for Users
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUsers extends EntityPathBase<Users> {

    private static final long serialVersionUID = -1298797514L;

    public static final QUsers users = new QUsers("users");

    public final StringPath campainIds = createString("campainIds");

    public final BooleanPath collectPrivacyAllowed = createBoolean("collectPrivacyAllowed");

    public final DateTimePath<java.time.LocalDateTime> collectPrivacyAllowedAt = createDateTime("collectPrivacyAllowedAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final BooleanPath deleted = createBoolean("deleted");

    public final DateTimePath<java.time.LocalDateTime> deletedAt = createDateTime("deletedAt", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath mailAllowed = createBoolean("mailAllowed");

    public final BooleanPath marketingAllowed = createBoolean("marketingAllowed");

    public final DateTimePath<java.time.LocalDateTime> marketingAllowedAt = createDateTime("marketingAllowedAt", java.time.LocalDateTime.class);

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final StringPath phoneNo = createString("phoneNo");

    public final BooleanPath pushAllowed = createBoolean("pushAllowed");

    public final BooleanPath smsAllowed = createBoolean("smsAllowed");

    public final EnumPath<com.binoofactory.alma.almabe.api.user.data.UserStatus> status = createEnum("status", com.binoofactory.alma.almabe.api.user.data.UserStatus.class);

    public final StringPath statusDetail = createString("statusDetail");

    public final StringPath teamName = createString("teamName");

    public final StringPath tellNo = createString("tellNo");

    public final EnumPath<com.binoofactory.alma.almabe.api.user.data.UserType> type = createEnum("type", com.binoofactory.alma.almabe.api.user.data.UserType.class);

    public final StringPath userId = createString("userId");

    public final BooleanPath visiabledPhoneNo = createBoolean("visiabledPhoneNo");

    public QUsers(String variable) {
        super(Users.class, forVariable(variable));
    }

    public QUsers(Path<? extends Users> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUsers(PathMetadata metadata) {
        super(Users.class, metadata);
    }

}

