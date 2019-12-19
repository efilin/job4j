package ru.job4j.carsalesplatform.dao;

import ru.job4j.carsalesplatform.model.Seller;

import java.util.List;

public interface SellerDao {
    int addSeller(Seller seller);

    void updateSeller(Seller seller);

    void deleteSeller(Seller seller);

    List<Seller> findAllSellers();

    Seller findSellerById(int id);

    Seller findSellerByLogin(String login);
}
