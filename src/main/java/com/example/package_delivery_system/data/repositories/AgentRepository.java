package com.example.package_delivery_system.data.repositories;

import com.example.package_delivery_system.data.entities.Admin;
import com.example.package_delivery_system.data.entities.Agent;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {

    List<Agent> findAll();

    Agent findAgentById(Long id);

}
