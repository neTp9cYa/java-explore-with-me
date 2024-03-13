package ru.practicum.ewm.service.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Repository;
import ru.practicum.ewm.service.model.ParticipationRequest;
import ru.practicum.ewm.service.model.ParticipationRequestStatus;
import ru.practicum.ewm.service.repository.dto.ParticipationRequestCount;

@Repository
public interface ParticipationRequestRepository extends JpaRepository<ParticipationRequest, Long> {
    List<ParticipationRequest> findAllByUser_Id(final long userId);

    List<ParticipationRequest> findAllByUser_IdAndEvent_Id(final long userId, final long eventId);

    Optional<ParticipationRequest> findByIdAndUser_Id(final long id, final long userId);

    long countByEvent_IdAndStatus(final long eventId, final ParticipationRequestStatus status);

    @Query("SELECT new ru.practicum.ewm.service.repository.dto.ParticipationRequestCount(pr.event.id, count(pr.id)) " +
        "FROM ParticipationRequest pr " +
        "WHERE pr.id in :eventIds and pr.status = :status " +
        "GROUP BY pr.event.id")
    Stream<ParticipationRequestCount> getCountForEventsByStatus(final List<Long> eventIds,
                                                                final ParticipationRequestStatus status);
}
