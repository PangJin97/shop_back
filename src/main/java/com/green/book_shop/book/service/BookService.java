package com.green.book_shop.book.service;

import com.green.book_shop.book.dto.BookCategoryDTO;
import com.green.book_shop.book.dto.BookDTO;

import java.util.List;

public interface BookService {

  /*카테고리 목록 조회*/
  public List<BookCategoryDTO> getCateGory();

  /*도서 등록*/
  public void insertItem(BookDTO bookDTO);

  /*카테고리 등록*/
  public int insertCate(String cateName);

  //카테고리 등록 기능 안에 카테고리 중복 검사가 있으니 굳이 따로 기능을 만들어 주지 않아도 된다
  //등록할때 같이 실행하면 되니까

  //mapper 는 단순히 쿼리를 받는 메서드  작성
  //실제 기능 구현하는 service 와는 다르다

  /*카테고리 수정*/
  public void updateCate(BookCategoryDTO bookCategoryDTO);

  /*카테고리 삭제*/
  public void deleteCate(int cateCode);

  //도서이미지 등록
  public void insertImgs(BookDTO bookDTO);

  //도서를 등록할때마다 bookCode지정
  public int getNextBookCode();

  public List<BookDTO> getBooks();

  public BookCategoryDTO getCateGoryByCateCode(int cateCode);
}
