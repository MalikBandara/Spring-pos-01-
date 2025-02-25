package org.example.springboot01.repo;

import org.example.springboot01.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemRepo  extends JpaRepository<Item, Integer> {

    @Modifying
    @Query(value = "update Item set qty = qty - :qty where id = :item_code")
    void updateQty(Integer item_code, int qty);

}
