package Reservation.service;

import Reservation.model.Projector;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

public interface ProjectorService {

    boolean insert(int projectorId);

    Optional<Projector> get(Integer projectorId);

    Collection<Projector> getAll();

    Integer count();

    Optional<Projector> has(Integer projectorId);

    Integer remove(int projectorId);

//    Integer remove(Projector projector);

    @Transactional
    public int batchInsert(List<Projector> projectorList);
}
