package fr.xebia.extras.selma.service;

import fr.xebia.extras.selma.Selma;
import fr.xebia.extras.selma.beans.Order;
import fr.xebia.extras.selma.beans.Order2OrderDto;
import fr.xebia.extras.selma.beans.OrderDto;

/**
 * Created with IntelliJ IDEA.
 * User: slemesle
 * Date: 27/11/2013
 * Time: 00:12
 * To change this template use File | Settings | File Templates.
 */
public class OrderService {

    private final Order2OrderDto orderMapper;

    public OrderService() {
        this.orderMapper = Selma.getMapper(Order2OrderDto.class);
    }

    public OrderDto process(Order order){
        return orderMapper.to(order);
    }

}
