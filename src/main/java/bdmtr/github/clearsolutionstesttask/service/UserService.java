package bdmtr.github.clearsolutionstesttask.service;

import bdmtr.github.clearsolutionstesttask.model.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {
    User findById(Long userId);
    List<User> findByBirthDateBetween(LocalDate fromDate, LocalDate toDate);
    User save(User user);
    void delete(Long id);
    User update(Long id, User user);
}
