package com.example.informationthematicbackend.repository.dsl;

import com.example.informationthematicbackend.model.entity.QSubjectEntity;
import com.example.informationthematicbackend.model.entity.SubjectEntity;
import com.example.informationthematicbackend.request.subject.GetListSubjectRequest;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SubjectDslRepository {
    private final QSubjectEntity subject = QSubjectEntity.subjectEntity;
    private final JPAQueryFactory queryFactory;

    public List<SubjectEntity> listSubject(GetListSubjectRequest request) {
        JPAQuery<SubjectEntity> query = queryFactory.select(subject)
                .from(subject);
        if (StringUtils.hasText(request.getSubject())) {
            query.where(subject.subject.containsIgnoreCase(request.getSubject()));
        }
        if (StringUtils.hasText(request.getCode())) {
            query.where(subject.code.containsIgnoreCase(request.getCode()));
        }
        return query.fetch();
    }
}