package ru.job4j.carsalesplatform.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;
import ru.job4j.carsalesplatform.model.Seller;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@Repository
public class SellerDaoImpl implements SellerDao {

    private final SessionFactory factory;

    public SellerDaoImpl() {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    @Override
    public int addSeller(Seller seller) {
        return (int) this.tx(session -> session.save(seller));
    }

    @Override
    public void updateSeller(Seller seller) {
        this.txVoid(session -> session.update(seller));
    }

    @Override
    public void deleteSeller(Seller seller) {
        this.txVoid(session -> session.delete(seller));
    }

    @Override
    public List<Seller> findAllSellers() {
        return this.tx(session -> session.createQuery("from Seller ").list());
    }

    @Override
    public Seller findSellerById(int id) {
        return this.tx(session -> session.get(Seller.class, id));
    }

    @Override
    public Seller findSellerByLogin(String login) {
        return tx(session -> session.byNaturalId(Seller.class)
                .using("login", login)
                .load());
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
