package com.codegym.notetags.service.impl;

import com.codegym.notetags.model.Stack;
import com.codegym.notetags.repository.StackRepository;
import com.codegym.notetags.service.StackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StackServiceImpl implements StackService {
    @Autowired
    private StackRepository stackRepository;

    @Override
    public Page<Stack> findALl(Pageable pageable) {
        return stackRepository.findAll(pageable);
    }

    @Override
    public Optional<Stack> findById(Long id) {
        return stackRepository.findById(id);
    }

    @Override
    public void save(Stack model) {
        stackRepository.save(model);
    }

    @Override
    public void delete(Long id) {
        stackRepository.deleteById(id);
    }
}
