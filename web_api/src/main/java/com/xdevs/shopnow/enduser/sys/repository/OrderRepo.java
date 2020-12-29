package com.xdevs.shopnow.enduser.sys.repository;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo {
    @Procedure("{ORDER_PROCEDURE_NAME}")
    void orderTicket();
}
