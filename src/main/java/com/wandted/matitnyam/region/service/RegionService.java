package com.wandted.matitnyam.region.service;

import com.wandted.matitnyam.region.domain.City;
import com.wandted.matitnyam.region.domain.District;
import com.wandted.matitnyam.region.dto.CityListResponse;
import com.wandted.matitnyam.region.dto.DistrictListResponse;
import com.wandted.matitnyam.region.repository.CityRepository;
import com.wandted.matitnyam.region.repository.DistrictRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class RegionService {

    private final CityRepository cityRepository;
    private final DistrictRepository districtRepository;

    public List<CityListResponse> getCityList(){
        return cityRepository.findAll()
                .stream()
                .map(City::toListResponse)
                .collect(Collectors.toList());
    }

    public List<DistrictListResponse> getDistrictList(Long cityId){
        return districtRepository.findAllByCity_Id(cityId)
                .stream()
                .map(District::toListResponse)
                .collect(Collectors.toList());
    }

    public District findDistrict(String districtName, Long cityId){
        return districtRepository.findByNameAndCity_Id(districtName , cityId);
    }
}
