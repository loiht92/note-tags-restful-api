package com.codegym.notetags.repository;

import com.codegym.notetags.model.Note;
import com.codegym.notetags.model.NoteType;
import com.codegym.notetags.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NoteRepository extends PagingAndSortingRepository<Note, Long> {
    Page<Note> findAllByNoteType(NoteType noteType, Pageable pageable);

    Page<Note> findAllByTags(Tag tag, Pageable pageable);

    Page<Note> findNoteByTitle(String title, Pageable pageable);
}
