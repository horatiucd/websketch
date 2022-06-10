package com.hcd.websketch.repository;

import com.hcd.websketch.domain.Destination;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DestinationRepository extends CrudRepository<Destination, Long> {

    Optional<Destination> findById(Long id);
}
