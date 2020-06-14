package com.codegym.notetags.service;

import com.codegym.notetags.model.Note;
import com.codegym.notetags.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TagService extends GenerateService<Tag> {
    Iterable<Tag> findAllByNotes(Note note);

    Page<Tag> findTagByName(String name, Pageable pageable);
}
