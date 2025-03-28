package com.green.book_shop.book.controller;

import com.green.book_shop.book.dto.BookCategoryDTO;
import com.green.book_shop.book.service.BookService;
import com.sun.source.tree.TryTree;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class BookCategoryController {
  private final BookService bookService;

  //카테고리 목록 조회 api
  @GetMapping("")
  public ResponseEntity<?> getCateList(){

    try{
      List<BookCategoryDTO> list = bookService.getCateGory();
      //조회가 안되면 리스트는 데이터가 없을때 NULL 이 아니라 0개이다 => list.size() == 0
      //DTO는 데이터가 없을 때 NULL 이다.

     return ResponseEntity.status(HttpStatus.OK).body(list);

    }catch (Exception e){
      e.printStackTrace(); //오류가 발생한 위치 및 이유를 알려줌
      //console.log(e) 와 같다.

//      return ResponseEntity
//              .status(HttpStatus.INTERNAL_SERVER_ERROR)
//              .body("카테고리 목록조회중 서버오류 발생");

      return ResponseEntity
              .status(HttpStatus.INTERNAL_SERVER_ERROR).build();
              //상태 코드만 가져가고 끝내겠다.
              //전달할 데이터가 없으면 .build()메서드 호출로 마무리
    }
  }

  /*카테고리 등록 api*/
  @PostMapping("")
  public ResponseEntity<?> insertCate(@RequestBody BookCategoryDTO bookCategoryDTO){
    //매개변수로 넘어오는 객체의 키와 매개변수에 작성한 dto 클래스의 변수가 일치

    //동록 성공 => 1을 리턴 / insert 가 실행되면 int 1을 리턴
    //등록 실패 => 0을 리턴 / insert 가 실행 되지 않았으니까

       int result = bookService.insertCate(bookCategoryDTO.getCateName());

       return ResponseEntity
               .status(result == 1 ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR)
               .body(result == 1 ? result : "알수없는 이유로 등록이 되지 않음");
  }

  /*카테고리 수정*/
  @PutMapping("/{cateCode}")
  public void updateCate(@RequestBody BookCategoryDTO bookCategoryDTO, @PathVariable("cateCode") int cateCode){
    bookCategoryDTO.setCateCode(cateCode);
    bookService.updateCate(bookCategoryDTO);
  }

  /*카테고리 삭제*/
  @DeleteMapping("/{cateCode}")
  public void deleteCate(@PathVariable("cateCode") int cateCode){
    bookService.deleteCate(cateCode);
  }




  @GetMapping("/test1")
  public ResponseEntity<String> test1(){
    return ResponseEntity.status(HttpStatus.OK).body("java");
  }

  @GetMapping("/test2")
  public ResponseEntity<Integer> test2(){
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(10);
  }

  @GetMapping("/test3")
  public ResponseEntity<List<BookCategoryDTO>> test3(){
   List<BookCategoryDTO> list = bookService.getCateGory();

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(list);
  }

  //헤더는 잘 쓸일이 없을거다
  @GetMapping("/test4")
  public ResponseEntity<String> test4(){
    HttpHeaders headers = new HttpHeaders();
    headers.add("myName", "hong");
    headers.add("myAge", "20");
    //헤더에는 문자밖에 못넣음

    return ResponseEntity
            .status(HttpStatus.OK)
            .headers(headers)
            .body("java");
  }

  @GetMapping("/test5/{cateCode}")
  public ResponseEntity<?> test5(@PathVariable("cateCode") int cateCode){
   //? : 와일드 카드 = 어떤 자료형이든 다 받겠습니다. (IF는 리턴이 스트링 ELSE 는 DTO 자료형)

  try {
    //예외가 날 수 있는 코드 작성
    BookCategoryDTO resultDTO = bookService.getCateGoryByCateCode(cateCode);

    return ResponseEntity
            .status(resultDTO == null ? HttpStatus.NOT_FOUND : HttpStatus.OK)
            .body(resultDTO == null ? "정보가 없습니다" : resultDTO);

  }catch (Exception e){
    //예외가 발생했을 때 실행할 코드
    //axios 에서 error 구문의 e
    //Exception(자료형) e(자바에서의 모든 오류 정보가 담긴 것) => 자바에선 자료형이 필요하고 자바스크립트는 자료형이없어

    return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("서버에서 오류 발생");
  }


//    //조회가 되지 않았을 때
//    if(resultDTO == null){
//      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("정보가 없습니다");
//    }
//    //조회가 잘 됐을 때
//    else {
//      return ResponseEntity.status(HttpStatus.OK).body(resultDTO);
//    }
  }



}
