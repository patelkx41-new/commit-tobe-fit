package com.example.demo;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.cart.Cart;
import com.commercetools.api.models.cart.CartDraft;
import com.commercetools.api.models.cart.CartDraftBuilder;
import com.commercetools.api.models.cart.LineItemDraft;
import com.commercetools.api.models.cart.LineItemDraftBuilder;
import com.commercetools.api.models.order.Order;
import com.commercetools.api.models.order.OrderFromCartDraft;
import com.commercetools.api.models.order.OrderFromCartDraftBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Rest {

    @Autowired
    private ProjectApiRoot api;

    @PostMapping("/create")
    public Order makeOrder(@RequestParam String sku) {
        List<LineItemDraft> items = new ArrayList<>();
        for (int i = 0; i < 51; i++) {
            LineItemDraft item = LineItemDraftBuilder.of()
                    .sku(sku)
                    .quantity(1L)
                    .build();
            items.add(item);
        }

        CartDraft cartDraft = CartDraftBuilder.of()
                .currency("USD")
                .lineItems(items)
                .build();

        Cart cart = api.carts().post(cartDraft).executeBlocking().getBody();

        OrderFromCartDraft orderDraft = OrderFromCartDraftBuilder.of()
                .cart(cart)
                .build();

        Order order = api.orders().post(orderDraft).executeBlocking().getBody();

        return order;
    }
}

