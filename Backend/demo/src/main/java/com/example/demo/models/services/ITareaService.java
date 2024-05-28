package com.example.demo.models.services;

import com.example.demo.models.entity.Tarea;

import java.util.List;
import java.util.Optional;

public interface ITareaService {
    List<Tarea> findAll();
    Optional<Tarea> findById(Long id);
    Tarea save(Tarea tarea);
    void delete(Long id);
}
