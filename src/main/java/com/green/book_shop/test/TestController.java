package com.green.book_shop.test;


import com.green.book_shop.book.dto.BookDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/test")
public class TestController {



  @GetMapping("/1")
  public  int test1(){
    return 5;
  }

  //첨부파일을 자바에서 받기 위해서 FormData 객체를 사용
  //전달되는 데이터의 형태도 multipart//form-data 형식으로 변환해서 전달
  //이렇게 전달되는 데이터를 받기 위한 코드를 변경했기 때문에
  //평소랑 다르게 데이터를 전달받아야함
  //DTO객체로 전달된 데이터를 받되 @RequestBody 어노테이션은 사용하지 않는다.
  //그렇다고 해서 DTO 객체로 첨부파일 정보도 받는 것은 아니다.\

  //첨부 파일 데이터를 받을때는 multiPartFile 객체를 사용한다.
  //첨부파일 연습1
  @PostMapping("/upload1")
  public void upload1(BookDTO bookDTO,
                      @RequestParam("firstFile") MultipartFile multipartFile){
    System.out.println(bookDTO);

    System.out.println(multipartFile.getOriginalFilename());
    //첨부된 원본 파일 명

    //업로드 될 경로
    String uploadPath = "D:\\01-STUDY\\devel\\ShopProject\\backEnd\\book_shop\\src\\main\\resources\\upload";

    //엽로드 경로와 피일명을 연결
    String attachedFileName = multipartFile.getOriginalFilename();


    File f = new File(uploadPath+attachedFileName);

    try {
      multipartFile.transferTo(f);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
