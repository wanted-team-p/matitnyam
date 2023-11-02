package com.wandted.matitnyam.domain.dto;

import java.io.Serial;
import javax.persistence.Column;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author 민경수
 * @description user dto
 * @since 2023.11.01
 **********************************************************************************************************************/
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto extends AbstractDto{

    @Serial
    private static final long serialVersionUID = -8808530353445716566L;


    private String id;
    private String password;
    private String address;
    private Float longitude;
    private Float latitude;
    private Boolean suggestAlarm;

    public UserDto(String id, String password, String address, Float longitude, Float latitude, Boolean suggestAlarm) {
        this.id = id;
        this.password = password;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.suggestAlarm = suggestAlarm;
    }

}
