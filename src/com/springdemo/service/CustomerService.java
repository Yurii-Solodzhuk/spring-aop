package com.springdemo.service;

import com.springdemo.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getCustomers();

    void saveCustomer(Customer customer);

    Customer getCustomers(int id);

    void deleteCustomer(int id);

    List<Customer> searchCustomers(String searchName);
}
