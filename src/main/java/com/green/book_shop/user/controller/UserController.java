package com.green.book_shop.user.controller;

import com.green.book_shop.user.dto.UserDTO;
import com.green.book_shop.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  //회원가입
  @PostMapping("")
  public ResponseEntity<Integer> insertUser(@RequestBody UserDTO userDTO){
     //boolean
    int result = userService.insertUser(userDTO);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  //로그인
  //@PathVariable, @RequestParam 으로 전달되는 데이터는
  //url 이 노출되어 => 아이디, 비밀번호가 노출됨
  @GetMapping("/login")
  public ResponseEntity<?> login(UserDTO userDTO){
    //조회된 데이터가 있다 => 로그인 된다 -> loginUser 가 null 이 아니다.
    //조회된 데이터가 없다 => 로그인 불가능 -> loginUser 가 null 이다.
    UserDTO loginUser = userService.login(userDTO);
    return ResponseEntity
            .status(loginUser == null ? HttpStatus.NOT_FOUND : HttpStatus.OK)
            .body(loginUser == null ? "로그인 실패" : loginUser);
  }
}
