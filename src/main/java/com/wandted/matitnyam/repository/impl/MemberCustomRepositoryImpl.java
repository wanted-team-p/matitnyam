package com.wandted.matitnyam.repository.impl;

import com.wandted.matitnyam.domain.Member;
import com.wandted.matitnyam.dto.MemberDetails;
import com.wandted.matitnyam.dto.MemberRequest;
import com.wandted.matitnyam.dto.PrincipalDto;
import com.wandted.matitnyam.repository.MemberCustomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberCustomRepositoryImpl implements MemberCustomRepository {

    private final EntityManager entityManager;

    @Override
    public boolean hasDuplicatedName(String username) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<String> query = builder.createQuery(String.class);
        Root<Member> member = query.from(Member.class);
        query.select(member.get("name"));
        query.where(builder.equal(builder.literal(username), member.get("name")));

        Optional<String> mayBeFoundMember = entityManager
                .createQuery(query)
                .getResultStream()
                .findFirst();
        return mayBeFoundMember.isPresent();
    }

    @Override
    public Optional<PrincipalDto> findByNameAndPassword(MemberRequest memberRequest) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PrincipalDto> query = builder.createQuery(PrincipalDto.class);
        Root<Member> member = query.from(Member.class);
        query.select(builder.construct(PrincipalDto.class, member.get("name"), member.get("authority")));

        String name = memberRequest.name();
        String password = memberRequest.password();
        Predicate predicateForName = builder.equal(builder.literal(name), member.get("name"));
        Predicate predicateForPassword = builder.equal(builder.literal(password), member.get("password"));
        query.where(builder.and(predicateForName, predicateForPassword));

        return entityManager
                .createQuery(query)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Optional<Member> findByUsername(String username) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Member> query = builder.createQuery(Member.class);
        Root<Member> member = query.from(Member.class);
        query.select(member);

        Predicate predicateForName = builder.equal(builder.literal(username), member.get("name"));
        query.where(predicateForName);

        return entityManager
                .createQuery(query)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Optional<MemberDetails> findDetails(String username) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MemberDetails> query = builder.createQuery(MemberDetails.class);
        Root<Member> member = query.from(Member.class);
        query.select(builder.construct(MemberDetails.class, member.get("seq"), member.get("name"), member.get("authority"),
                member.get("latitude"), member.get("longitude")));

        Predicate predicateForName = builder.equal(builder.literal(username), member.get("name"));
        query.where(predicateForName);

        return entityManager
                .createQuery(query)
                .getResultStream()
                .findFirst();
    }

}
