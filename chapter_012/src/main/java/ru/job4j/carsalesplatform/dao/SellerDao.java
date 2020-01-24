package ru.job4j.carsalesplatform.dao;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.carsalesplatform.model.Seller;

import java.util.List;

public interface SellerDao  extends CrudRepository<Seller, Integer> {

    Seller findByLogin(String login);
}
