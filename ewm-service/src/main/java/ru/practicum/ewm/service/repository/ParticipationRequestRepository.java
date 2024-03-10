package ru.practicum.ewm.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.ewm.service.model.ParticipationRequest;

@Repository
public interface ParticipationRequestRepository extends JpaRepository<ParticipationRequest, Long> {
}
