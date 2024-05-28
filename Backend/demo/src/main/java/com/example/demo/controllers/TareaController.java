package com.example.demo.controllers;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.models.entity.Tarea;
import com.example.demo.models.services.ITareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:8100"})
@RestController
@RequestMapping("/api/tarea")
public class TareaController {

    @Autowired
    private ITareaService tareaService;

    @GetMapping
    public List<Tarea> getAllTareas() {
        return tareaService.findAll();
    }

    @GetMapping("/{id}")
    public Tarea getTareaById(@PathVariable Long id) {
        Optional<Tarea> tarea = tareaService.findById(id);
        if (!tarea.isPresent()) {
            throw new ResourceNotFoundException("Tarea no encontrada con id: " + id);
        }
        return tarea.get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tarea createTarea(@RequestBody Tarea tarea) {
        return tareaService.save(tarea);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Tarea updateTarea(@RequestBody Tarea tareaDetails, @PathVariable Long id) {
        Optional<Tarea> tareaOptional = tareaService.findById(id);
        if (!tareaOptional.isPresent()) {
            throw new ResourceNotFoundException("Tarea no encontrada con id: " + id);
        }
        Tarea tareaActual = tareaOptional.get();
        tareaActual.setTitulo(tareaDetails.getTitulo());
        tareaActual.setDescripcion(tareaDetails.getDescripcion());
        tareaActual.setPrioridad(tareaDetails.getPrioridad());
        tareaActual.setFechaVencimiento(tareaDetails.getFechaVencimiento());
        // Agrega aquí cualquier otro campo que necesites actualizar

        return tareaService.save(tareaActual);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTarea(@PathVariable Long id) {
        Optional<Tarea> tareaOptional = tareaService.findById(id);
        if (!tareaOptional.isPresent()) {
            throw new ResourceNotFoundException("Tarea no encontrada con id: " + id);
        }
        tareaService.delete(id);
    }
}
