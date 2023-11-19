package com.wandted.matitnyam.restaurant.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.wandted.matitnyam.region.service.RegionService;
import com.wandted.matitnyam.restaurant.domain.ApiProperties;
import com.wandted.matitnyam.restaurant.domain.Restaurant;
import com.wandted.matitnyam.restaurant.dto.ApiResponse;
import com.wandted.matitnyam.restaurant.dto.Row;
import com.wandted.matitnyam.restaurant.repository.RestaurantRepository;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Transactional
@RequiredArgsConstructor
@Service
public class RestaurantApiService {

    private final RestTemplate restTemplate;
    private final RestaurantRepository restaurantRepository;
    private final RegionService regionService;

    public void getAllAvailableData(String path){
        ApiResponse response = new ApiResponse();
        int pageIndex =1;

        do{
            response = sendApiRequest(path, pageIndex);
            saveRestaurantData(response);

            pageIndex++;
        }while(response.head.list_total_count() > (pageIndex -1) * ApiProperties.PAGE_SIZE);
    }

    private void saveRestaurantData(ApiResponse apiResponse){
        List<Restaurant> restaurants = new ArrayList<>();

        for(Row data : apiResponse.row){
            try{
                restaurants.add(
                    Restaurant.builder()
                            .name(data.BIZPLC_NM().replace("\"", ""))
                            .district(regionService.findDistrict(data.SIGUN_NM().replace("\"", ""), ApiProperties.CITY_ID))
                            .address(data.REFINE_ROADNM_ADDR().replace("\"", ""))
                            .latitude(Double.parseDouble(data.REFINE_WGS84_LAT().replace("\"", "")))
                            .longitude(Double.parseDouble(data.REFINE_WGS84_LOGT().replace("\"", "")))
                            .build()
                );
            }catch (NumberFormatException e){
                return;
            }catch (NullPointerException e){
                return;
            }
        }
        restaurantRepository.saveAll(restaurants);
    }

    private ApiResponse sendApiRequest(String path, int pageIndex){
        URI uri = UriComponentsBuilder
                .fromUriString(ApiProperties.BASE_URL)
                .path(path)
                .queryParam("KEY", ApiProperties.API_KEY)
                .queryParam("pSize", ApiProperties.PAGE_SIZE)
                .queryParam("pIndex", pageIndex)
                .encode()
                .build()
                .toUri();

        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        return parseXmlStringToObject(restTemplate.getForObject(uri, String.class));
    }

    private ApiResponse parseXmlStringToObject(String xml) {
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        XmlMapper xmlMapper = new XmlMapper(module);
        ApiResponse response = new ApiResponse();

        try{
            response = xmlMapper.readValue(xml, ApiResponse.class);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}
