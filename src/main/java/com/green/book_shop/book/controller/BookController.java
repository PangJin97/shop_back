package com.green.book_shop.book.controller;


import com.green.book_shop.book.dto.BookDTO;
import com.green.book_shop.book.service.BookService;
import com.green.book_shop.util.UploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
  public void insertBook(BookDTO bookDTO, @RequestParam(name = "mainImg", required = true) MultipartFile mainImg,
                         @RequestParam(name = "subImg", required = true) MultipartFile subImg){

  //일반 데이터는 requestBody 어노테이션을 빼고 DTO로 받는다
  //requestParam 을 2개로 만들어야함 메인 이미지, 상세 이미지를 따로따로 만들어서 2개로 받는다

  //첨부파일(도서 이미지 업로드)
    uploadUtil.fileUpload(mainImg);
    uploadUtil.fileUpload(subImg);

    //BOOK테이블에 데이터에 INSERT
    bookService.insertItem(bookDTO);
  }

  //도서 목록 조회
  @GetMapping("")
  public List<BookDTO> getBooks(){
    return bookService.getBooks();
  }

}
