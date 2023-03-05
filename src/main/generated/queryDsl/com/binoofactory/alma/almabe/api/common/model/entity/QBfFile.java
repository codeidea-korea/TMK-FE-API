package com.binoofactory.alma.almabe.api.common.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBfFile is a Querydsl query type for BfFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBfFile extends EntityPathBase<BfFile> {

    private static final long serialVersionUID = 998431186L;

    public static final QBfFile bfFile = new QBfFile("bfFile");

    public final DateTimePath<java.time.LocalDateTime> createTime = createDateTime("createTime", java.time.LocalDateTime.class);

    public final NumberPath<Integer> fileGroupNo = createNumber("fileGroupNo", Integer.class);

    public final StringPath fileName = createString("fileName");

    public final NumberPath<Long> fileSeq = createNumber("fileSeq", Long.class);

    public final StringPath type = createString("type");

    public final StringPath webPath = createString("webPath");

    public QBfFile(String variable) {
        super(BfFile.class, forVariable(variable));
    }

    public QBfFile(Path<? extends BfFile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBfFile(PathMetadata metadata) {
        super(BfFile.class, metadata);
    }

}

