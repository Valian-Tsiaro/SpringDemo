package com.example.demo.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Person;

/**
 * 
 * @author Tsiaro
 *
 * Make the connexion between the DBB and the entities
 * 
 * 
 */

@Repository
@Transactional
public interface PersonDAO extends JpaRepository<Person, Long> {

}
