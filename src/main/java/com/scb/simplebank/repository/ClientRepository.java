package com.scb.simplebank.repository;

import com.scb.simplebank.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
