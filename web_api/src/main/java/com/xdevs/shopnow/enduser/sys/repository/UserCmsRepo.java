package com.xdevs.shopnow.enduser.sys.repository;

import com.xdevs.shopnow.enduser.sys.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCmsRepo extends JpaRepository<Customer, Integer> {
    Customer findByUserName(String userName);
}
