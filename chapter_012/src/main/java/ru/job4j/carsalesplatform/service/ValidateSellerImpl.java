package ru.job4j.carsalesplatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.carsalesplatform.dao.SellerDao;
import ru.job4j.carsalesplatform.model.Seller;

import java.util.List;

@Service
public class ValidateSellerImpl implements ValidateSeller {

    @Autowired
    SellerDao sellerDao;

    @Override
    public int addSeller(Seller seller) {
        return this.sellerDao.addSeller(seller);
    }

    @Override
    public void updateSeller(Seller seller) {
        this.sellerDao.updateSeller(seller);
    }

    @Override
    public void deleteSeller(Seller seller) {
        this.sellerDao.deleteSeller(seller);
    }

    @Override
    public List<Seller> findAllSellers() {
        return this.sellerDao.findAllSellers();
    }

    @Override
    public Seller findSellerById(int id) {
        return this.sellerDao.findSellerById(id);
    }

    @Override
    public Seller findSellerByLogin(String login) {
        return this.sellerDao.findSellerByLogin(login);
    }
}
