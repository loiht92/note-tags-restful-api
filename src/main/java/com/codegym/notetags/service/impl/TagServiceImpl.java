package com.codegym.notetags.service.impl;

import com.codegym.notetags.model.Note;
import com.codegym.notetags.model.Tag;
import com.codegym.notetags.repository.TagRepository;
import com.codegym.notetags.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepository;

    @Override
    public Iterable<Tag> findAllByNotes(Note note) {
        return tagRepository.findAllByNotes(note);
    }

    @Override
    public Page<Tag> findTagByName(String name, Pageable pageable) {
        return tagRepository.findTagByName(name, pageable);
    }

    @Override
    public Page<Tag> findALl(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public Optional<Tag> findById(Long id) {
        return tagRepository.findById(id);
    }

    @Override
    public void save(Tag model) {
        tagRepository.save(model);
    }

    @Override
    public void delete(Long id) {
        tagRepository.deleteById(id);
    }
}
