package com.codegym.notetags.controller;

import com.codegym.notetags.model.Note;
import com.codegym.notetags.model.Tag;
import com.codegym.notetags.repository.NoteRepository;
import com.codegym.notetags.repository.TagRepository;
import com.codegym.notetags.service.NoteService;
import com.codegym.notetags.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TagControllerRestful {
    @Autowired
    private TagService tagService;

    @Autowired
    private NoteService noteService;

    @GetMapping("/tags")
    public ResponseEntity<Page<Tag>> getAllTags(Pageable pageable) {
        Page<Tag> tags = tagService.findALl(pageable);
        if (tags.getTotalElements() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @GetMapping("/tags/{id}")
    public ResponseEntity<Optional<Tag>> getTagById(@PathVariable Long id) {
        Optional<Tag> tag = tagService.findById(id);
        if (! tag.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @PostMapping("/tags")
    public ResponseEntity<String> createTags(@RequestBody Tag tag) {
        tagService.save(tag);
        return new ResponseEntity<>("create ok !", HttpStatus.OK);
    }

    @PutMapping("/tags/{id}")
    public ResponseEntity<Optional<Tag>> updateTags(@PathVariable Long id, @RequestBody Tag tag){
        Optional<Tag> tagOptional = tagService.findById(id);
        if (!tagOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        tagService.save(tag);
        return new ResponseEntity<>(tagOptional, HttpStatus.OK);
    }

    @DeleteMapping("/tags/{id}")
    public ResponseEntity<Optional<Tag>> deleteTags(@PathVariable Long id, Pageable pageable) {
        Optional<Tag> tag = tagService.findById(id);
        if (! tag.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Page<Note> notes = noteService.findAllByTag(tag.get(), pageable);
        for (Note note : notes) {
            note.setTags(null);
            noteService.save(note);
        }
        tagService.delete(tag.get().getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


















