package com.wandted.matitnyam.repository;

import com.wandted.matitnyam.domain.entity.Region;
import com.wandted.matitnyam.repository.querydsl.RegionRepositoryQueryDsl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long>, RegionRepositoryQueryDsl {
}
