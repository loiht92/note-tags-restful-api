package com.codegym.notetags.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface GenerateService<T> {
    Page<T> findALl(Pageable pageable);

    Optional<T> findById(Long id);

    void save(T model);

    void delete(Long id);

}
