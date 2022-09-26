package mate.academy.dao.impl;

import java.util.Optional;
import mate.academy.dao.OrderDao;
import mate.academy.exception.DataProcessingException;
import mate.academy.lib.Dao;
import mate.academy.model.Order;
import mate.academy.model.User;
import mate.academy.util.HibernateUtil;
import org.hibernate.Session;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public Optional<Order> getByUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Order o "
                    + "LEFT JOIN FETCH O.tickets t "
                    + "LEFT JOIN FETCH o.user "
                    + "LEFT JOIN FETCH t.movieSession ms "
                    + "LEFT JOIN FETCH ms.cinemaHall "
                    + "LEFT JOIN FETCH ms.movie "
                    + "WHERE o.user = :user", Order.class)
                    .setParameter("user", user)
                    .uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get the order by user: " + user, e);
        }
    }
}