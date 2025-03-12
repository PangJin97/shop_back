package com.green.book_shop.book.controller;

import com.green.book_shop.book.dto.BookCategoryDTO;
import com.green.book_shop.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class BookCategoryController {
  private final BookService bookService;

  //카테고리 목록 조회 api
  @GetMapping("")
  public List<BookCategoryDTO> getCateList(){
    return bookService.getCateGory();
  }

  /*카테고리 등록 api*/
  @PostMapping("")
  public int insertCate(@RequestBody BookCategoryDTO bookCategoryDTO){
    //매개변수로 넘어오는 객체의 키와 매개변수에 작성한 dto 클래스의 변수가 일치

    //동록 성공 => 1을 리턴 / insert 가 실행되면 int 1을 리턴
    //등록 실패 => 0을 리턴 / insert 가 실행 되지 않았으니까
     return bookService.insertCate(bookCategoryDTO.getCateName());
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
}
