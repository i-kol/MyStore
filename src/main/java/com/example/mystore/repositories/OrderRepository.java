package com.example.mystore.repositories;

import com.example.mystore.models.Order;
import com.example.mystore.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    // Получение списка заказов по конкретному пользователю Person
    List<Order> findByPerson(Person person);

    List<Order> findByNumberEndingWith(String endingWith);
}
