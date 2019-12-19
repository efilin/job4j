package ru.job4j.carsalesplatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.carsalesplatform.dao.SellingCarDao;
import ru.job4j.carsalesplatform.model.SellingCar;

import java.util.List;

@Service
public class ValidateSellingCarImpl implements ValidateSellingCar {

    @Autowired
    SellingCarDao sellingCarDao;

    @Override
    public int addCar(SellingCar car) {
        return sellingCarDao.addCar(car);
    }

    @Override
    public void updateCar(SellingCar car) {
        sellingCarDao.updateCar(car);
    }

    @Override
    public void deleteCar(SellingCar car) {
        sellingCarDao.deleteCar(car);
    }

    @Override
    public List<SellingCar> findAllCars() {
        return sellingCarDao.findAllCars();
    }

    @Override
    public List<SellingCar> findCarsWithPhoto() {
        return sellingCarDao.findCarsWithPhoto();
    }

    @Override
    public List<SellingCar> findLastDayCars() {
        return sellingCarDao.findLastDayCars();
    }

    @Override
    public List<SellingCar> findCurrentManufacturerCars(String manufacturer) {
        return sellingCarDao.findCurrentManufacturerCars(manufacturer);
    }

    @Override
    public SellingCar findCarById(int id) {
        return sellingCarDao.findCarById(id);
    }

    @Override
    public void changeSaleStatus(int id) {
        sellingCarDao.changeSaleStatus(id);
    }
}
