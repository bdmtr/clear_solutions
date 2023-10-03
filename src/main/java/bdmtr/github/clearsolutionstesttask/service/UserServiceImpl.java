package bdmtr.github.clearsolutionstesttask.service;

import bdmtr.github.clearsolutionstesttask.exception.IncorrectDateException;
import bdmtr.github.clearsolutionstesttask.exception.UserNotFoundException;
import bdmtr.github.clearsolutionstesttask.model.entity.User;
import bdmtr.github.clearsolutionstesttask.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findByBirthDateBetween(LocalDate fromDate, LocalDate toDate) {
        if (toDate.isBefore(fromDate)) {
            throw new IncorrectDateException("End date: " + toDate + " is before start date " + fromDate);
        }

        return userRepository.findByBirthdateBetween(fromDate, toDate);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("Cant find user: " + userId));
    }

    @Override
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("Cant find user to delete: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public User update(Long id, User user) {
        User modificatedUser = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("Cant find user to update: " + id));

        Field[] fields = User.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object fieldValue = field.get(user);
                if (fieldValue != null) {
                    field.set(modificatedUser, fieldValue);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return modificatedUser;
    }
}
