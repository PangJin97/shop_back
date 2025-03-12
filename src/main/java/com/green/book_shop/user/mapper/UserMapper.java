package com.green.book_shop.user.mapper;

import com.green.book_shop.user.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
  //회원등록
  public int insertUser(UserDTO userDTO);
        //boolean

  //아이디 중복확인
  public String isUsableUserId(String userId);

  //로그인
  public UserDTO login(UserDTO userDTO);
}
