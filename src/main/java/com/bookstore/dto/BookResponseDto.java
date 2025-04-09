package com.bookstore.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BookResponseDto {
    private Long id;
    private String title;
    private String author;
    private String category;
    private BigDecimal price;
    private Double rating;
    private LocalDate publishedDate;
}