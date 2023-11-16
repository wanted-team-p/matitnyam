package com.wandted.matitnyam.domain.dto;

import java.io.Serial;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author 민경수
 * @description abstract dto
 * @since 2023.11.01
 **********************************************************************************************************************/
@Getter
@Setter
@EqualsAndHashCode(callSuper = false, of = {"seq"})
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class AbstractDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -713764464634092034L;


    /**
     * 일련번호
     */
    private Long seq;

}