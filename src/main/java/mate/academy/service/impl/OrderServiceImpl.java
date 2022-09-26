package mate.academy.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import mate.academy.dao.OrderDao;
import mate.academy.lib.Inject;
import mate.academy.lib.Service;
import mate.academy.model.Order;
import mate.academy.model.ShoppingCart;
import mate.academy.model.User;

@Service
public class OrderServiceImpl implements mate.academy.service.OrderService {
    @Inject
    private OrderDao orderDao;

    @Override
    public Order completeOrder(ShoppingCart shoppingCart) {
        Order addedOrder = new Order();
        addedOrder.setUser(shoppingCart.getUser());
        addedOrder.setTickets(new ArrayList<>(shoppingCart.getTickets()));
        addedOrder.setOrderDate(LocalDateTime.now());
        return orderDao.add(addedOrder);
    }

    @Override
    public List<Order> getOrdersHistory(User user) {
        return orderDao.getByUser(user);
    }
}