package ru.practicum.ewm.service.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.practicum.ewm.service.model.Event;
import ru.practicum.ewm.service.model.EventState;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event> {
    Optional<Event> findByIdAndUser_Id(final long id, final long userId);

    Optional<Event> findByIdAndState(final long id, final EventState state);
}
