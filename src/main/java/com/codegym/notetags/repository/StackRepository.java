package com.codegym.notetags.repository;

import com.codegym.notetags.model.Stack;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StackRepository extends PagingAndSortingRepository<Stack, Long> {
}
