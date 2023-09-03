package com.example.demo;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
//import javax.management.Query;
import jakarta.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;



@Repository
public class PersonDAOPersonimpl implements PersonDAO<Person> {
    private static final long serialVersionUID = 1L;
    
    @PersistenceContext
    private EntityManager entityManager;

    public PersonDAOPersonimpl(){
        super();
    }


    @Override
    public List<Person> getAll(){
        Query query = entityManager.createQuery("from Person");
        @SuppressWarnings("unchecked")
        List<Person> list = query.getResultList(); 
        entityManager.close();
        return list;

    }
    
}
