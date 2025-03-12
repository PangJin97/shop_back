package com.green.book_shop.book.controller;


import com.green.book_shop.book.dto.BookDTO;
import com.green.book_shop.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
  private final BookService bookService;

  //도서 등록
  @PostMapping("")
  public void insertBook(@RequestBody BookDTO bookDTO){
    bookService.insertItem(bookDTO);
  }

  //도서 목록 조회
  @GetMapping("")
  public List<BookDTO> getBooks(){
    return bookService.getBooks();
  }

}
