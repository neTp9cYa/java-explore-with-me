package ru.practicum.ewm.service.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.ewm.service.model.ParticipationRequest;

@Repository
public interface ParticipationRequestRepository extends JpaRepository<ParticipationRequest, Long> {
    List<ParticipationRequest> findAllByUser_Id(final long userId);
    List<ParticipationRequest> findAllByUser_IdAndEvent_Id(final long userId, final long eventId);
    Optional<ParticipationRequest> findByIdAndUser_Id(final long id, final long userId);
}
