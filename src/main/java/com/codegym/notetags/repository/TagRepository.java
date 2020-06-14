package com.codegym.notetags.repository;

import com.codegym.notetags.model.Note;
import com.codegym.notetags.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TagRepository extends PagingAndSortingRepository<Tag, Long> {
    Iterable<Tag> findAllByNotes(Note note);

    Page<Tag> findTagByName(String name, Pageable pageable);
}
