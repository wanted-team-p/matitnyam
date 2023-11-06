package com.wandted.matitnyam.repository;

import com.wandted.matitnyam.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberCustomRepositoryImpl implements MemberCustomRepository {

    private final EntityManager entityManager;

    @Override
    public Long findIdByName(String name) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Member> member = query.from(Member.class);
        query.select(member.get("seq"));
        query.where(builder.equal(builder.literal(name), member.get("name")));

        try {
            return entityManager
                    .createQuery(query)
                    .getSingleResult();
        } catch (Exception exception) {
            return null;
        }
    }

}
