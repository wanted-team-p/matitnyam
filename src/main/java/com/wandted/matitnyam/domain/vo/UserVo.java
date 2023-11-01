package com.wandted.matitnyam.domain.vo;

import com.wandted.matitnyam.domain.entity.User;
import java.io.Serial;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author 민경수
 * @description user vo
 * @since 2023.11.02
 **********************************************************************************************************************/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 7429183420912418595L;


    @Getter
    @Setter
    @NoArgsConstructor
    public static class Create {

        private String id;
        private String password;

        public User toEntity() {
            return User.builder().id(id).password(password).build();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class SignIn {

    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Read {

    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Update {

    }

}