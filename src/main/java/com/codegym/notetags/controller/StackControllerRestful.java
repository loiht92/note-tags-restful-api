package com.codegym.notetags.controller;

import com.codegym.notetags.model.NoteType;
import com.codegym.notetags.model.Stack;
import com.codegym.notetags.repository.StackRepository;
import com.codegym.notetags.service.NoteTypeService;
import com.codegym.notetags.service.StackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StackControllerRestful {
    @Autowired
    private StackService stackService;

    @Autowired
    private NoteTypeService noteTypeService;

    @GetMapping("/stacks")
    public ResponseEntity<Page<Stack>> getAllStack(Pageable pageable) {
        Page<Stack> stacks = stackService.findALl(pageable);
        if (stacks.getTotalElements() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(stacks, HttpStatus.OK);
    }

    @GetMapping("/stacks/{id}")
    public ResponseEntity<Optional<Stack>> getStackById(@PathVariable Long id) {
        Optional<Stack> stack = stackService.findById(id);
        if (! stack.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(stack, HttpStatus.OK);
    }

    @PostMapping("/stacks")
    public ResponseEntity<String> createStacks(@RequestBody Stack stack) {
        stackService.save(stack);
        return new ResponseEntity<>("create ok !", HttpStatus.OK);
    }

    @PutMapping("/stacks/{id}")
    public ResponseEntity<Optional<Stack>> updateStacks(@PathVariable Long id, @RequestBody Stack stack) {
        Optional<Stack> stackOptional = stackService.findById(id);
        if (! stackOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        stackService.save(stack);
        return new ResponseEntity<>(stackOptional, HttpStatus.OK);
    }

    @DeleteMapping("/stacks/{id}")
    public ResponseEntity<Optional<Stack>> deleteStacks(@PathVariable Long id, Pageable pageable) {
        Optional<Stack> stack = stackService.findById(id);
        if (! stack.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Page<NoteType> noteTypes = noteTypeService.findAllByStack(stack.get(), pageable);
        for (NoteType noteType : noteTypes) {
            noteType.setStack(null);
            noteTypeService.save(noteType);
        }
        stackService.delete(stack.get().getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
