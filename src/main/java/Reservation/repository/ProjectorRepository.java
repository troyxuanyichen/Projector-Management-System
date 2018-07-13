package Reservation.repository;

import Reservation.model.Projector;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectorRepository<Projector, Integer> extends JpaRepository<Projector, Integer> {

  @Override
  Optional<Projector> findById(Integer id);

  @Override
  <S extends Projector> Optional<S> findOne(Example<S> example);
}
