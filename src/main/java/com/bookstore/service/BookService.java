package com.bookstore.service;

import com.bookstore.dto.BookRequestDto;
import com.bookstore.dto.BookResponseDto;
import com.bookstore.exception.ResourceNotFoundException;
import com.bookstore.model.Book;
import com.bookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public BookResponseDto createBook(BookRequestDto requestDto) {
        Book book = mapToEntity(requestDto);
        Book savedBook = bookRepository.save(book);
        return mapToDto(savedBook);
    }

    public Page<BookResponseDto> getAllBooks(
            Integer page, 
            Integer size, 
            String sortBy, 
            String sortDir,
            String author,
            String category,
            Double rating,
            String title) {
        
        Pageable pageable = PageRequest.of(
                page != null ? page : 0,
                size != null ? size : 10,
                Sort.Direction.fromString(sortDir != null ? sortDir : "ASC"),
                sortBy != null ? sortBy : "id"
        );
        
        Page<Book> bookPage;
        
        if (author != null || category != null || rating != null || title != null) {
            bookPage = bookRepository.findByFilters(author, category, rating, title, pageable);
        } else {
            bookPage = bookRepository.findAll(pageable);
        }
        
        return bookPage.map(this::mapToDto);
    }

    public BookResponseDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        return mapToDto(book);
    }

    public BookResponseDto updateBook(Long id, BookRequestDto requestDto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        
        // Update the book properties
        book.setTitle(requestDto.getTitle());
        book.setAuthor(requestDto.getAuthor());
        book.setCategory(requestDto.getCategory());
        book.setPrice(requestDto.getPrice());
        book.setRating(requestDto.getRating());
        book.setPublishedDate(requestDto.getPublishedDate());
        
        Book updatedBook = bookRepository.save(book);
        return mapToDto(updatedBook);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }

    private Book mapToEntity(BookRequestDto dto) {
        return Book.builder()
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .category(dto.getCategory())
                .price(dto.getPrice())
                .rating(dto.getRating())
                .publishedDate(dto.getPublishedDate())
                .build();
    }

    private BookResponseDto mapToDto(Book book) {
        BookResponseDto dto = new BookResponseDto();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setCategory(book.getCategory());
        dto.setPrice(book.getPrice());
        dto.setRating(book.getRating());
        dto.setPublishedDate(book.getPublishedDate());
        return dto;
    }
}