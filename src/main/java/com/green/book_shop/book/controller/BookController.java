package com.green.book_shop.book.controller;


import com.green.book_shop.book.dto.BookDTO;
import com.green.book_shop.book.dto.ImgDTO;
import com.green.book_shop.book.service.BookService;
import com.green.book_shop.util.UploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
  private final BookService bookService;
  private final UploadUtil uploadUtil;

  //도서 등록
  @PostMapping("")
  //Book 테이블에 데이터 INSERT
  public void insertBook(BookDTO bookDTO, @RequestParam(name = "mainImg", required = true) MultipartFile mainImg,
                         @RequestParam(name = "subImg", required = true) MultipartFile subImg){

  //첨부파일(도서 이미지)업로드
  //일반 데이터는 requestBody 어노테이션을 빼고 DTO로 받는다
  //requestParam 을 2개로 만들어야함 메인 이미지, 상세 이미지를 따로따로 만들어서 2개로 받는다

    //첨부파일(도서 이미지 업로드)

    //bookMapper의 insertImgs쿼리의 빈값을 채울 수 있는 값 찾고 담기

    //1.원본 파일명
    mainImg.getOriginalFilename();
    subImg.getOriginalFilename();


    //2.첨부한 파일명
    //빈값을 채우기 위해 데이터 뽑아오려면 첨부된 파일명이 함수안에 있으니 리턴 시켜서 뽑아와야한다.
    String mainAttachedFileName = uploadUtil.fileUpload(mainImg); // 첨부된 파일명은 fileUpload() 메서드에서 만들어짐
    String subAttachedFileName = uploadUtil.fileUpload(subImg);


    //3. bookCode
    //bookMapper의 getNextBookCode쿼리
    //이미지 insert 를 위한 북코드 직접 지정
    //img 테이블과 book 테이블의 북코드가 동일해야하니까 img테이블에 bookcode를 빈값을 채우려면
    //book테이블에 자동으로 생성되는 bookCode를 직접 지정해줘야한다.

    //도서를 등록할때 마다 들어갈 BOOK_CODE 지정한 것을 담는다
    int nextBookCode = bookService.getNextBookCode();


    //4.최종(빈값을 채우기 위해 찾은 빈값들을 직접 넣어주기)

    //4-1. BOOK 테이블에 데이터를 INSERT
    bookDTO.setBookCode(nextBookCode);
    //직접 지정한 bookcode삽입

    bookService.insertItem(bookDTO);


    //4-2.bookDTO에 이미지 데이터를 저장
    List<ImgDTO> imgList = new ArrayList<>();
    //빈값을 채우기 위해서는 imgDTO가 여러개 있어야한다 =>List자료형

    ImgDTO mainImgDTO = new ImgDTO();
    //쿼리 빈값을 채우려면 리엑트에서 값을 다 받아오지 못했으니
    //직접 객체를 생성해 setter를 사용하여 값 주입
    //우선은 메인 이미지

    mainImgDTO.setOriginFileName(mainImg.getOriginalFilename());
    //원본파일
    mainImgDTO.setAttachedFileName(mainAttachedFileName);
    //첨부된 파일
    mainImgDTO.setIsMain("Y");
    //메인 이미지냐 아니냐
    mainImgDTO.setBookCode(nextBookCode);
    //bookCode

    ImgDTO subImgDTO = new ImgDTO();
    //다음으로 서브 이미지 객체생성해서 값 주입

    subImgDTO.setOriginFileName(subImg.getOriginalFilename());
    subImgDTO.setAttachedFileName(subAttachedFileName);
    subImgDTO.setIsMain("N");
    subImgDTO.setBookCode(nextBookCode);

    //만든 메인 이미지 객체와 서브 이미지 객체를 리스트 자료형에 담는다
    imgList.add(mainImgDTO);
    imgList.add(subImgDTO);
    bookDTO.setImgList(imgList);
    //1대n관계라 bookDTO안에 있는 imgList를 위에서 실제로 데이터를 넣은 imgList로 값을 setter한다.

    //4-3. 찐 최종 => BOOK_IMG 테이블에 도서 이미지 정보 INSERT
    bookService.insertImgs(bookDTO);
    //이제 bookDTO에 있는 List<ImgDTO> imgList;는  값이 들어가 있다
    //그래서 매개변수로 bookDTO 넣으면 이미지 정보도 들어간다.
  }
  //bookMapper의 insertImgs쿼리의 빈값을 bookDTO로 채우기로 했고(bookDTO안에 imgList가 있으니)
  //bookDTO안의 List<ImgDTO> imgList의 값을 채워가는 과정이다

  //도서 목록 조회
  @GetMapping("")
  public ResponseEntity<?> getBooks(){

    List<BookDTO> bookList = bookService.getBooks();

    return ResponseEntity.status(HttpStatus.OK).body(bookList);
  }
}
