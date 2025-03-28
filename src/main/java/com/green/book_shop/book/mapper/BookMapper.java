package com.green.book_shop.book.mapper;

import com.green.book_shop.book.dto.BookCategoryDTO;
import com.green.book_shop.book.dto.BookDTO;
import com.green.book_shop.book.dto.ImgDTO;
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

  //도서 이미지 등록
  //public void insertImgs(List<ImgDTO> imgList);
  //근데 bookDTO안에 어차피 ImgList가 있다
  //그래서 빈값을 bookDTO로 채우겠다.
  public void insertImgs(BookDTO bookDTO);


  //도서를 등록할떄마다 BOOK_CODE를 지정(조회)
  public int getNextBookCode();

  public List<BookDTO> getBooks();

  public BookCategoryDTO getCateGoryByCateCode(int cateCode);
}
