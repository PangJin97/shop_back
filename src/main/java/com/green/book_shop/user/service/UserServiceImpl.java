package com.green.book_shop.user.service;

import com.green.book_shop.user.dto.UserDTO;
import com.green.book_shop.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserMapper userMapper;

  @Override
  public int insertUser(UserDTO userDTO){
        //boolean => mapper에서만 리턴 타입을 꼭 지켜주면 되고
                     //나머지는 상관없음
    int result = 0;
   //boolean isJoin = false;

    String selectUserId = userMapper.isUsableUserId(userDTO.getUserId());

    //아이디가 중복이다 -> 조회된 데이터가 null 이 아니다
    //아이디가 중복이 아니다 -> 조회되는 데이터가 null 이다

    //만약 null 이면 insert 에 성공하고 영향을 받은 DB의 행이 하나 생겨서 result 가 1로 변하고 리턴
    if(selectUserId == null){
      result = userMapper.insertUser(userDTO);
      //isJoin = true;
    }
    return result;
    //return isJoin;
  }

  //로그인
  @Override
  public UserDTO login(UserDTO userDTO) {
    return userMapper.login(userDTO);
  }
}
