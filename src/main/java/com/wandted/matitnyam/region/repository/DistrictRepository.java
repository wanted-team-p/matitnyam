package com.wandted.matitnyam.region.repository;

import com.wandted.matitnyam.region.domain.District;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository<District, Long> {
    List<District> findAllByCity_Id(Long cityId);
}
