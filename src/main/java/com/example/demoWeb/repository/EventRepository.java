package com.example.demoWeb.repository;

import com.example.demoWeb.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}
