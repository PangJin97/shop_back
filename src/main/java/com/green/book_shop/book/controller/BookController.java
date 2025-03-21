package com.green.book_shop.book.controller;


import com.green.book_shop.book.dto.BookDTO;
import com.green.book_shop.book.service.BookService;
import com.green.book_shop.util.UploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
  private final BookService bookService;
  private final UploadUtil uploadUtil;

  //첨부파일(도서 이미지)업로드


  //도서 등록
  @PostMapping("")
  //Book 테이블에 데이터 INSERT
  public void insertBook(@RequestBody BookDTO bookDTO){
    bookService.insertItem(bookDTO);
    uploadUtil.fileUpload();
  }

  //도서 목록 조회
  @GetMapping("")
  public List<BookDTO> getBooks(){
    return bookService.getBooks();
  }

}
