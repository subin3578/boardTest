package com.example.flutter.repository;

import com.example.flutter.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

@Repository
public interface BoardRepository extends JpaRepository<Article, Long> {

    Page<Article> findAll(Pageable pageable);
}
