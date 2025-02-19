package org.example.springboot01.repo;

import org.example.springboot01.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemRepo  extends JpaRepository<Item, Integer> {

}
