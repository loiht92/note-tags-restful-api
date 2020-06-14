package com.codegym.notetags.repository;

import com.codegym.notetags.model.Note;
import com.codegym.notetags.model.NoteType;
import com.codegym.notetags.model.Stack;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NoteTypeRepository extends PagingAndSortingRepository<NoteType, Long> {
    Page<NoteType> findNoteTypeByStack(Stack stack, Pageable pageable);

    Page<NoteType> findAllByNotes(Note note, Pageable pageable);

    Page<NoteType> findNoteTypeByName(String name, Pageable pageable);
}
