package com.codegym.notetags.controller;

import com.codegym.notetags.model.Note;
import com.codegym.notetags.model.NoteType;
import com.codegym.notetags.service.NoteService;
import com.codegym.notetags.service.NoteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class NoteControllerRestful {
    @Autowired
    private NoteService noteService;

    @Autowired
    private NoteTypeService noteTypeService;

    @GetMapping("/notes")
    public ResponseEntity<Page<Note>> getAllNote(Pageable pageable) {
        Page<Note> notes = noteService.findALl(pageable);
        if (notes.getTotalElements() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @GetMapping(value = "/notes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Note>> getNoteById(@PathVariable Long id) {
        Optional<Note> note = noteService.findById(id);
        if (! note.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @PostMapping("/notes")
    public ResponseEntity<String> createNotes(@RequestBody Note note) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        note.setTime(date);
        noteService.save(note);
        return new ResponseEntity<>("Created successfully !", HttpStatus.CREATED);
    }

    @PutMapping("/notes/{id}")
    public ResponseEntity<Optional<Note>> updateNotes(@PathVariable Long id, @RequestBody Note note) {
        Optional<Note> optionalNote = noteService.findById(id);
        if (! optionalNote.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
//        long millis = System.currentTimeMillis();
//        Date date = new Date(millis);
//        optionalNote.get().setTitle(note.getTitle());
//        optionalNote.get().setContent(note.getContent());
//        optionalNote.get().setTime(date);
//        optionalNote.get().setNoteType(note.getNoteType());
//        optionalNote.get().setTags(note.getTags());

        noteService.save(note);
        return new ResponseEntity<>(optionalNote, HttpStatus.OK);
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<Optional<Note>> deleteNotes(@PathVariable Long id, Pageable pageable) {
        Optional<Note> note = noteService.findById(id);
        if (! note.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Page<NoteType> noteTypes = noteTypeService.findAllByNote(note.get(), pageable);
        for (NoteType noteType : noteTypes) {
            noteType.setNotes(null);
            noteTypeService.save(noteType);
        }
        noteService.delete(note.get().getId());
        return new ResponseEntity<>(HttpStatus.OK);

    }


}
