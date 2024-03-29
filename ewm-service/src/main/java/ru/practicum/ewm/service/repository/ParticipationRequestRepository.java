package ru.practicum.ewm.service.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.ewm.service.model.ParticipationRequest;
import ru.practicum.ewm.service.model.ParticipationRequestStatus;
import ru.practicum.ewm.service.repository.dto.ParticipationRequestCount;

@Repository
public interface ParticipationRequestRepository extends JpaRepository<ParticipationRequest, Long> {
    List<ParticipationRequest> findAllByUser_Id(final long userId);

    List<ParticipationRequest> findAllByEvent_User_IdAndEvent_Id(final long userId, final long eventId);

    Optional<ParticipationRequest> findByIdAndUser_Id(final long id, final long userId);

    long countByEvent_IdAndStatus(final long eventId, final ParticipationRequestStatus status);

    @Query("SELECT new ru.practicum.ewm.service.repository.dto.ParticipationRequestCount(pr.event.id, count(pr.id)) " +
        "FROM ParticipationRequest pr " +
        "WHERE pr.event.id in :eventIds and pr.status = :status " +
        "GROUP BY pr.event.id")
    List<ParticipationRequestCount> getCountForEventsByStatus(final List<Long> eventIds,
                                                              final ParticipationRequestStatus status);

    @Query("SELECT new ru.practicum.ewm.service.repository.dto.ParticipationRequestCount(pr.event.id, count(pr.id)) " +
        "FROM ParticipationRequest pr " +
        "WHERE pr.event.id = :eventId and pr.status = :status " +
        "GROUP BY pr.event.id")
    Optional<ParticipationRequestCount> getCountForEventByStatus(final long eventId,
                                                                 final ParticipationRequestStatus status);
}
