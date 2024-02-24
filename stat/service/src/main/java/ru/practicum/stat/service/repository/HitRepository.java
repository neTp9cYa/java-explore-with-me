package ru.practicum.stat.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.stat.service.model.Hit;

@Repository
public interface HitRepository extends JpaRepository<Hit, Integer> {
}
