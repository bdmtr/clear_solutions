package bdmtr.github.clearsolutionstesttask.model.repository;

import bdmtr.github.clearsolutionstesttask.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByBirthdateBetween(LocalDate from, LocalDate to);
}
