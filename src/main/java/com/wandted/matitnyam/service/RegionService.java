package com.wandted.matitnyam.service;

import com.wandted.matitnyam.domain.entity.Region;
import java.util.List;

public interface RegionService {

    List<Region> get();

    void set(Region region);

}
