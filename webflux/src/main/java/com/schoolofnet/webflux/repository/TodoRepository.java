package com.schoolofnet.webflux.repository;

import com.schoolofnet.webflux.model.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * created by:
 *
 * @author rafael for DevDusCorre on 22/12/2021
 */
@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {
}
