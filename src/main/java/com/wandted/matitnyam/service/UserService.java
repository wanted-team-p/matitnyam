package com.wandted.matitnyam.service;

import com.wandted.matitnyam.domain.entity.User;
import com.wandted.matitnyam.domain.vo.UserVo;

public interface UserService {

    void set(UserVo.Create create);

    void signIn(UserVo.SignIn signIn);

    User get(Long userSeq);

    void get(UserVo.Read read);

    void modify(UserVo.Update update);

}
