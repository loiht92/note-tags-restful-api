package com.codegym.notetags.service.impl;

import com.codegym.notetags.model.Note;
import com.codegym.notetags.model.NoteType;
import com.codegym.notetags.model.Tag;
import com.codegym.notetags.repository.NoteRepository;
import com.codegym.notetags.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteRepository noteRepository;

    @Override
    public Page<Note> findAllByNoteType(NoteType noteType, Pageable pageable) {
        return noteRepository.findAllByNoteType(noteType, pageable);
    }

    @Override
    public Page<Note> findAllByTag(Tag tag, Pageable pageable) {
        return noteRepository.findAllByTags(tag, pageable);
    }

    @Override
    public Page<Note> findNoteByTitle(String title, Pageable pageable) {
        return noteRepository.findNoteByTitle(title, pageable);
    }

    @Override
    public Page<Note> findALl(Pageable pageable) {
        return noteRepository.findAll(pageable);
    }

    @Override
    public Optional<Note> findById(Long id) {
        return noteRepository.findById(id);
    }

    @Override
    public void save(Note model) {
        noteRepository.save(model);
    }

    @Override
    public void delete(Long id) {
        noteRepository.deleteById(id);
    }
}
