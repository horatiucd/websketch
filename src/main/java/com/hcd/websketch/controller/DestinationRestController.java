package com.hcd.websketch.controller;

import com.hcd.websketch.domain.Destination;
import com.hcd.websketch.exception.EntityNotFoundException;
import com.hcd.websketch.repository.DestinationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/destinations")
public class DestinationRestController {

    private final DestinationRepository brickRepository;

    public DestinationRestController(DestinationRepository brickRepository) {
        this.brickRepository = brickRepository;
    }

    @GetMapping
    public Iterable<Destination> findAll() {
        return brickRepository.findAll();
    }

    @GetMapping("/{id}")
    public Destination findOne(@PathVariable Long id) {
        return brickRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Destination create(@RequestBody Destination book) {
        return brickRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        brickRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        brickRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Destination update(@PathVariable long id, @RequestBody Destination brick) {
        brickRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        brick.setId(id);

        return brickRepository.save(brick);
    }
}
