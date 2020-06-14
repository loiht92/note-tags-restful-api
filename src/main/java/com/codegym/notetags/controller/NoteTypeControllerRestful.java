package com.codegym.notetags.controller;

import com.codegym.notetags.model.Note;
import com.codegym.notetags.model.NoteType;
import com.codegym.notetags.repository.NoteTypeRepository;
import com.codegym.notetags.service.NoteService;
import com.codegym.notetags.service.NoteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class NoteTypeControllerRestful {
    @Autowired
    private NoteTypeService noteTypeService;

    @Autowired
    private NoteService noteService;

    @GetMapping("/note_type")
    public ResponseEntity<Page<NoteType>> getAllNoteType(Pageable pageable) {
        Page<NoteType> noteTypes = noteTypeService.findALl(pageable);
        if (noteTypes.getTotalElements() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(noteTypes, HttpStatus.OK);
    }

    @GetMapping("/note_type/{id}")
    public ResponseEntity<Optional<NoteType>> getNoteTypeById(@PathVariable Long id) {
        Optional<NoteType> noteType = noteTypeService.findById(id);
        if (! noteType.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(noteType, HttpStatus.OK);
    }

    @PostMapping("/note_type")
    public ResponseEntity<String> createNoteType(@RequestBody NoteType noteType) {
        noteTypeService.save(noteType);
        return new ResponseEntity<>("create ok !", HttpStatus.OK);
    }

    @PutMapping("/note_type/{id}")
    public ResponseEntity<Optional<NoteType>> updateNoteType(@PathVariable Long id, @RequestBody NoteType noteType) {
        Optional<NoteType> noteTypeOptional = noteTypeService.findById(id);
        if (! noteTypeOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        noteTypeService.save(noteType);
        return new ResponseEntity<>(noteTypeOptional, HttpStatus.OK);
    }

    @DeleteMapping("/note_type/{id}")
    public ResponseEntity<Optional<NoteType>> deleteNoteType(@PathVariable Long id, Pageable pageable) {
        Optional<NoteType> noteType = noteTypeService.findById(id);
        if (! noteType.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Page<Note> notes = noteService.findAllByNoteType(noteType.get(), pageable);
        for (Note note : notes) {
            note.setNoteType(null);
            noteService.save(note);
        }
        noteTypeService.delete(noteType.get().getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

















