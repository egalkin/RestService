package com.galkin.rest.repository;

import com.galkin.rest.model.Application;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.TreeSet;

@Repository
public interface ApplicationRepository extends CrudRepository<Application, Long> {
    TreeSet<Application> findByContactId(Long contactId);
}
