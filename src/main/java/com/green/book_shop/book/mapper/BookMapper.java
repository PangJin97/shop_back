package com.green.book_shop.book.mapper;

import com.green.book_shop.book.dto.BookCategoryDTO;
import com.green.book_shop.book.dto.BookDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {

  /*카테고리 목록 조회*/
  public List<BookCategoryDTO>getCateGory();

  /*도서 등록*/
  public void insertItem(BookDTO bookDTO);

  /*카테고리 등록*/
  public int insertCate(String cateName);

  //카테고리명 중복 확인
  public String isUsableCateName(String cateName);

  //카테고리 수정
  public void updateCate(BookCategoryDTO bookCategoryDTO);

  //카테고리 삭제
  public void deleteCate(int cateCode);


  public List<BookDTO> getBooks();
}
