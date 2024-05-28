package com.example.demo.models.services;

import com.example.demo.models.entity.Tarea;
import com.example.demo.models.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareaServiceImpl implements ITareaService {

    @Autowired
    private TareaRepository tareaRepository;

    @Override
    public List<Tarea> findAll() {
        return tareaRepository.findAll();
    }

    @Override
    public Optional<Tarea> findById(Long id) {
        return tareaRepository.findById(id);
    }

    @Override
    public Tarea save(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    @Override
    public void delete(Long id) {
        tareaRepository.deleteById(id);
    }
}
