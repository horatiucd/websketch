package com.hcd.websketch.controller;

import com.hcd.websketch.domain.Destination;
import com.hcd.websketch.exception.EntityNotFoundException;
import com.hcd.websketch.repository.DestinationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/destinations")
public class DestinationController {

    private final DestinationRepository repository;

    public DestinationController(DestinationRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("destinations", repository.findAll());
        return "listDestinations";
    }

    @GetMapping(value = "/create")
    public String create(Model model) {
        model.addAttribute("destination", new Destination());
        return "createDestination";
    }

    @GetMapping(value = "/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        Destination destination = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        model.addAttribute("destination", destination);
        return "updateDestination";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id) {
        Destination destination = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        repository.delete(destination);

        return "redirect:/destinations";
    }

    @PostMapping(value = "/save")
    public String save(@ModelAttribute Destination destination, Model model) {
        repository.save(destination);

        model.addAttribute("detinations", repository.findAll());

        return "redirect:/destinations";
    }
}
