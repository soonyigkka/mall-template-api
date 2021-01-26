package com.mall.template.test.disruptor;


import com.lmax.disruptor.EventFactory;

public class OrderFactory implements EventFactory<Order> {
    @Override
    public Order newInstance() {
        return new Order();
    }
}