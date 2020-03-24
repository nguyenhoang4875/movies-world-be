package com.movies.dao.daoiplm;

import com.movies.dao.EmployeeDao;
import com.movies.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    // define field for entity manager
    private EntityManager entityManager;

    /// set up constructor injection
    @Autowired
    public EmployeeDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public List<Employee> findAll() {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // create a query
        Query<Employee> query = currentSession.createQuery("from Employee ", Employee.class);

        // execute query and get result list
        List<Employee> employees = query.getResultList();

        // return the results
        return employees;
    }
}
