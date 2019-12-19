package ru.job4j.carsalesplatform.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;
import ru.job4j.carsalesplatform.model.SellingCar;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@Repository
public class SellingCarDaoImpl implements SellingCarDao {

    private final SessionFactory factory;

    public SellingCarDaoImpl() {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    @Override
    public int addCar(SellingCar car) {
        return (int) this.tx(session -> session.save(car));
    }

    @Override
    public void updateCar(SellingCar car) {
        this.txVoid(session -> session.update(car));
    }

    @Override
    public void deleteCar(SellingCar car) {
        this.txVoid(session -> session.delete(car));
    }

    @Override
    public List<SellingCar> findAllCars() {
        return this.tx(session -> session.createQuery("from SellingCar sc ORDER BY sc.id").list());
    }

    @Override
    public List<SellingCar> findCarsWithPhoto() {
        return this.tx(session -> session.createQuery("from SellingCar sc where sc.photo!=''").list());
    }

    @Override
    public List<SellingCar> findLastDayCars() {
        LocalDate date = LocalDateTime.now().toLocalDate();
        Timestamp currentDateTime = Timestamp.valueOf(date.atStartOfDay());
        String query = String.format("from SellingCar sc where sc.created > '%s'", currentDateTime);
        return this.tx(session -> session.createQuery(query).list());
    }

    @Override
    public List<SellingCar> findCurrentManufacturerCars(String manufacturer) {
        String query = String.format("from SellingCar  sc where sc.manufacturer = '%s'", manufacturer);
        return this.tx(session -> session.createQuery(query).list());
    }

    @Override
    public SellingCar findCarById(int id) {
        return this.tx(session -> session.get(SellingCar.class, id));
    }

    @Override
    public void changeSaleStatus(int id) {
        this.txVoid(session -> session.get(SellingCar.class, id)
                .setOnSale(!session.get(SellingCar.class, id).isOnSale()));
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = factory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    private void txVoid(final Consumer<Session> command) {
        final Session session = factory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            command.accept(session);
            tx.commit();
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

}
