package com.springdemo.dao;

import com.springdemo.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CustomerDAOImpl implements CustomerDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers() {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<Customer> query = currentSession.createQuery("from Customer order by lastName", Customer.class);

        List<Customer> customers = query.getResultList();

        return customers;
    }

    @Override
    public void saveCustomer(Customer customer) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(customer);
    }

    @Override
    public Customer getCustomer(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Customer customer = currentSession.get(Customer.class, id);
        return customer;
    }

    @Override
    public void deleteCustomer(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("delete from Customer where id=:customerId");
        query.setParameter("customerId", id);
        query.executeUpdate();
    }

    @Override
    public List<Customer> searchCustomers(String searchName) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = null;

        if (searchName != null && searchName.trim().length() > 0){
            query = currentSession.createQuery("from Customer where lower(firstName) like :name or lower(lastName)" +
                    "like :name", Customer.class);
            query.setParameter("name","%" + searchName.toLowerCase() + "%");
        } else {
          query = currentSession.createQuery("from Customer ", Customer.class);
        }

        List<Customer> customers = query.getResultList();

        return customers;
    }
}
