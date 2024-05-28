package com.example.demo.models.repository;

import com.example.demo.models.entity.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareaRepository extends JpaRepository<Tarea, Long> {
    // Puedes agregar métodos personalizados de consulta aquí si es necesario
}
