package com.green.book_shop.book.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.PrimitiveIterator;

@ToString
@Getter
@Setter
public class BookDTO {
  private int bookCode;
  private String bookName;
  private int bookPrice;
  private String publisher;
  private String bookInfo;
  private LocalDateTime regDate;
  private int bookStock;
  private int cateCode;
  private List<ImgDTO> imgList;
  //1대N관계
}
