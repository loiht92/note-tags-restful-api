package com.codegym.notetags.service;

import com.codegym.notetags.model.Note;
import com.codegym.notetags.model.NoteType;
import com.codegym.notetags.model.Stack;
import com.codegym.notetags.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoteTypeService extends GenerateService<NoteType> {
    Page<NoteType> findAllByStack(Stack stack, Pageable pageable);

    Page<NoteType> findAllByNote(Note note, Pageable pageable);

    Page<NoteType> findNoteTypeByName(String name, Pageable pageable);
}
