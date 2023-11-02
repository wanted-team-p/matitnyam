package com.wandted.matitnyam.service.impl;

import com.wandted.matitnyam.domain.entity.User;
import com.wandted.matitnyam.domain.vo.UserVo.Create;
import com.wandted.matitnyam.domain.vo.UserVo.Read;
import com.wandted.matitnyam.domain.vo.UserVo.SignIn;
import com.wandted.matitnyam.domain.vo.UserVo.Update;
import com.wandted.matitnyam.repository.UserRepository;
import com.wandted.matitnyam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author 민경수
 * @description user service impl
 * @since 2023.11.02
 **********************************************************************************************************************/
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;


    @Override
    public void set(Create create) {
        repository.save(create.toEntity());
    }

    @Override
    public void signIn(SignIn signIn) {

    }

    @Override
    public User get(Long seq) {
        return repository.findById(seq).orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
    }

    @Override
    public void get(Read read) {

    }

    @Override
    public void modify(Update update) {
    }

}