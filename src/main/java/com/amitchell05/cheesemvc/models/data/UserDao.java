package com.amitchell05.cheesemvc.models.data;

import com.amitchell05.cheesemvc.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserDao extends CrudRepository<User, Integer> {
    public List<User> findByUsername(String username);
}
