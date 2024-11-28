package com.example.sign_up;


import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {
   public Person findByUsername(String username);
}
