package com.wandted.matitnyam.service;

import com.wandted.matitnyam.domain.entity.Region;
import com.wandted.matitnyam.domain.vo.RegionVo.Search;
import java.util.List;
import java.util.Map;

public interface RegionService {

    Map<String, List<String>> get(Search search);

    void set(Region region);

}
