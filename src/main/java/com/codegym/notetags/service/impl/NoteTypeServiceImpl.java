package com.codegym.notetags.service.impl;

import com.codegym.notetags.model.Note;
import com.codegym.notetags.model.NoteType;
import com.codegym.notetags.model.Stack;
import com.codegym.notetags.repository.NoteTypeRepository;
import com.codegym.notetags.service.NoteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NoteTypeServiceImpl implements NoteTypeService {
    @Autowired
    private NoteTypeRepository noteTypeRepository;

    @Override
    public Page<NoteType> findAllByStack(Stack stack, Pageable pageable) {
        return noteTypeRepository.findNoteTypeByStack(stack, pageable);
    }

    @Override
    public Page<NoteType> findAllByNote(Note note, Pageable pageable) {
        return noteTypeRepository.findAllByNotes(note, pageable);
    }

    @Override
    public Page<NoteType> findNoteTypeByName(String name, Pageable pageable) {
        return noteTypeRepository.findNoteTypeByName(name, pageable);
    }

    @Override
    public Page<NoteType> findALl(Pageable pageable) {
        return noteTypeRepository.findAll(pageable);
    }

    @Override
    public Optional<NoteType> findById(Long id) {
        return noteTypeRepository.findById(id);
    }

    @Override
    public void save(NoteType model) {
        noteTypeRepository.save(model);
    }

    @Override
    public void delete(Long id) {
        noteTypeRepository.deleteById(id);
    }
}
