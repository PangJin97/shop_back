package com.green.book_shop.book.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
}
