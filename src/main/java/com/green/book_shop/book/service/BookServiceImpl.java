package com.green.book_shop.book.service;

import com.green.book_shop.book.dto.BookCategoryDTO;
import com.green.book_shop.book.dto.BookDTO;
import com.green.book_shop.book.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookMapper bookMapper;

  /*카테고리 목록 조회*/
  @Override
  public List<BookCategoryDTO> getCateGory() {
    return bookMapper.getCateGory();
  }

  /*도서 등록*/
  @Override
  public void insertItem(BookDTO bookDTO) {
    bookMapper.insertItem(bookDTO);
  }

  //카테고리 등록기능
  @Override
  public int insertCate(String cateName){
    int result = 0;
    //먼저 카테고리 명이 중복인지 확인한다.
    //cateName 이 null 이면 사용가능한 카테고리
    //cateName 이 null 이 아니면 사용 불가능한 카테고리
    String selectedCateName = bookMapper.isUsableCateName(cateName);

    // => 중복이면 result는 0

    //만약 중복이 아니면 카테고리 등록한다.
    if(selectedCateName == null){
      //카테고리 등록 실행
      //result 가 1로 변함(insert 가 실행됐으니까 영항받은 행이 하나이니 1 => insert 의 리턴타입의 활용(int))
      result=bookMapper.insertCate(cateName);
    }
    return result;
  }

  /*카테고리 수정*/
  @Override
  public void updateCate(BookCategoryDTO bookCategoryDTO) {
    bookMapper.updateCate(bookCategoryDTO);
  }

  /*카테고리 삭제*/
  @Override
  public void deleteCate(int cateCode) {
    bookMapper.deleteCate(cateCode);
  }

  @Override
  public List<BookDTO> getBooks() {
    return bookMapper.getBooks();
  }
}
