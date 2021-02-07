package com.ozeeesoftware.realestate.repository;

import com.ozeeesoftware.realestate.model.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdRepository extends JpaRepository<Ad,Long> {
}
