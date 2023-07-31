package dev.loicmoreaux.filrouge.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.loicmoreaux.filrouge.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
