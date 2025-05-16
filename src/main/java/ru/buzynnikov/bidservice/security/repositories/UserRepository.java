package ru.buzynnikov.bidservice.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.buzynnikov.bidservice.security.models.User;

import java.util.Optional;

/**
 * Интерфейс репозитория для работы с пользователями.
 * Использует стандартные методы JpaRepository для манипуляций с пользователями.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Метод для поиска пользователя по логину.
     *
     * @param login Логин пользователя
     * @return Optional, содержащий пользователя с данным логином или пустой Optional, если пользователь не найден
     */
    Optional<User> findByLogin(String login);
}
