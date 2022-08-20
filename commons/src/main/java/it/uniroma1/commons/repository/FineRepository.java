package it.uniroma1.commons.repository;

import it.uniroma1.commons.entity.Fine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FineRepository extends JpaRepository<Fine, Long> {
}
