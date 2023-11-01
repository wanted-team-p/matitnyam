package com.wandted.matitnyam.domain.dto;

import java.io.Serial;
import javax.persistence.Column;

/**
 * @author 민경수
 * @description user dto
 * @since 2023.11.01
 **********************************************************************************************************************/
public class UserDto extends AbstractDto{

    @Serial
    private static final long serialVersionUID = -8808530353445716566L;


    private String id;
    private String password;
    private String address;
    private Float longitude;
    private Float latitude;

    public UserDto(String id, String password, String address, Float longitude, Float latitude) {
        this.id = id;
        this.password = password;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
    }

}
