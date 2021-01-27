package games.lab4.services;

import games.lab4.models.Koszyk;
import games.lab4.models.OrderShop;
import games.lab4.models.User;

public interface OrderService {

void saveOrder(OrderShop order, User user);
}
