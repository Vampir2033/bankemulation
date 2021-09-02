package ru.dorogin.bankemulation.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dorogin.bankemulation.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findOneByUsername(String username);
}
