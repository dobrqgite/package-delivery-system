package com.example.package_delivery_system.data.repositories;

import com.example.package_delivery_system.data.entities.Admin;
import com.example.package_delivery_system.data.entities.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
}
