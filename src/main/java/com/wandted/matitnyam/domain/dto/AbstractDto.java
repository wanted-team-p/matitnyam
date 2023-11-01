package com.wandted.matitnyam.domain.dto;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author 민경수
 * @description abstract dto
 * @since 2023.11.01
 **********************************************************************************************************************/
public class AbstractDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -713764464634092034L;


    /**
     * 일련번호
     */
    private Long seq;

}