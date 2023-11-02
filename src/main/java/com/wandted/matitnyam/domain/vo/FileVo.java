package com.wandted.matitnyam.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 민경수
 * @description file vo
 * @since 2023.11.02
 **********************************************************************************************************************/
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_ABSENT, content = JsonInclude.Include.NON_EMPTY)
public class FileVo {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Create {
        private MultipartFile file;
    }

}