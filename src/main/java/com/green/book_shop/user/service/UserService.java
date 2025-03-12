package com.green.book_shop.user.service;

import com.green.book_shop.user.dto.UserDTO;

public interface UserService {
  //회원가입
  public int insertUser(UserDTO userDTO);
      //boolean

  //로그인
  public UserDTO login(UserDTO userDTO);
}
