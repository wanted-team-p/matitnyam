package com.wandted.matitnyam.repository.querydsl;

import com.wandted.matitnyam.domain.entity.Region;
import java.util.List;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface RegionRepositoryQueryDsl {

    List<Region> findAllBy(String city, String district);

}
