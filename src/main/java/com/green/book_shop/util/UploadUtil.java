package com.green.book_shop.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;//첨부파일 기능 모음 클래스

@Component
//객체를 자동으로 생성해주는 어노테이션
public class UploadUtil {

  //application/properties 파일에 정의한
  //file.upload.dir 값을 가져와서 uploadPath변수에 저장

  //업로드 될 경로(업로드할 파일 위치)
  @Value("${file.upload.dir}")
  private String uploadPath;

  //단일 파일 업로드
  public String fileUpload(MultipartFile multipartFile) {
      //파일을 첨부했을 때만 첨부 기능 실행
      if(multipartFile != null){
        //multipartFile : 원본파일
        //multipartFile.getOriginalFilename()
        //화면에서 선택한 원본 파일 명
        String originFileName = multipartFile.getOriginalFilename();

        //첨부될 파일명
        String attachFileName = getAttachedFileName(originFileName);
        //첨부하는 원본 파일 -> 첨부실행 -> 첨부될 파일
        //통상적으로 중복이 안되게 억지로 바꿔서 넣음(원본 파일과 첨부될 파일이 중복 안되게)


        //업로드 경로, 파일명을 연결
        File f = new File(uploadPath + attachFileName);

        try {
          multipartFile.transferTo(f);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        return attachFileName;
      }
      return null;
      //첨부한 파일이 없으면 null을 리턴
  }


  //다중 파일 업로드
  //첨부할 파일이 여러개 들어오면 MultipartFile[] => 배열 형태로 데이터를 받음
  public void multiFileUpload(MultipartFile[] files) {
    for(MultipartFile eachFile : files){
      fileUpload(eachFile);
      //받아온 각각의 파일을 단일파일 업로드 함수 호출을 활용해 사용
    }
  }


  //원본 파일 명에서 첨부할 파일명을 생성하는 메서드
  public String getAttachedFileName(String originFileName){

    //첨부할 파일명(랜덤한 문자열 생성)
    String uuid = UUID.randomUUID().toString();

    //화면에서 선택한 파일의 확장자 추출
    String[] result = originFileName.split("\\.");
    // 확장자는 항상 마지막 요소
    //split에 들어간 문자를 기준으로 나눠주고 배열로 리턴

    String extension = result[result.length - 1];

    //완성된 첨부할 파일명
    String attachFileName = uuid + "." + extension;
    return attachFileName;
  }

}


