package com.amitchell05.cheesemvc.models.data;

import com.amitchell05.cheesemvc.models.Cheese;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CheeseDao extends CrudRepository<Cheese, Integer> {

}
