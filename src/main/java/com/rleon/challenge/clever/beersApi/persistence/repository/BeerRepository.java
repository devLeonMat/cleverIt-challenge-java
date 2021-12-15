package com.rleon.challenge.clever.beersApi.persistence.repository;

import com.rleon.challenge.clever.beersApi.persistence.entity.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerRepository  extends JpaRepository<Beer, Long> {
}
