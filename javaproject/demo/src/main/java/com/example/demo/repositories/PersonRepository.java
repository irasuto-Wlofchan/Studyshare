package com.example.demo.repositories;

import java.util.List;
//import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.example.demo.Person;

@Repository
public interface PersonRepository  extends JpaRepository<Person, Long> {
  
    public Optional<Person> findById(Long name);
    public List<Person> findByNameLike(String name);
    public List<Person> findByIdIsNotNullorderByIdDesc();
    public List<Person> findByAgeGreaterThen(Integer age);
    public List<Person> findByAgeBetween(Integer age1,Integer age2);


}
