package com.bookstore.repository;

import com.bookstore.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findByAuthorContainingIgnoreCase(String author, Pageable pageable);
    
    Page<Book> findByCategoryContainingIgnoreCase(String category, Pageable pageable);
    
    Page<Book> findByRatingGreaterThanEqual(Double rating, Pageable pageable);
    
    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    @Query("SELECT b FROM Book b WHERE " +
           "(:author is null OR LOWER(b.author) LIKE LOWER(CONCAT('%', :author, '%'))) AND " +
           "(:category is null OR LOWER(b.category) LIKE LOWER(CONCAT('%', :category, '%'))) AND " +
           "(:rating is null OR b.rating >= :rating) AND " +
           "(:title is null OR LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%')))")
    Page<Book> findByFilters(
            @Param("author") String author,
            @Param("category") String category,
            @Param("rating") Double rating,
            @Param("title") String title,
            Pageable pageable);
}