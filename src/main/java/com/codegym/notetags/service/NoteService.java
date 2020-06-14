package com.codegym.notetags.service;

import com.codegym.notetags.model.Note;
import com.codegym.notetags.model.NoteType;
import com.codegym.notetags.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoteService extends GenerateService<Note> {
    //@ManyToMany
    Page<Note> findAllByNoteType(NoteType noteType, Pageable pageable);

    //@ManyToMany
    Page<Note> findAllByTag(Tag tag, Pageable pageable);

    //Search By Title
    Page<Note> findNoteByTitle(String title, Pageable pageable);
}
