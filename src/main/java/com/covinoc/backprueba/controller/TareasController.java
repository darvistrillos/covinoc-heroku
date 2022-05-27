package com.covinoc.backprueba.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.covinoc.backprueba.model.Tareas;
import com.covinoc.backprueba.repository.TareasRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class TareasController {

    @Autowired
    TareasRepository tareasRepository;


    @GetMapping("/api/v1/todos")
    public ResponseEntity<List<Tareas>> obtenerTareas() {
        try {
            List<Tareas> items = new ArrayList<Tareas>();
            items = tareasRepository.findAllByOrderByCreatedAtDesc();
            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(items, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/api/v1/todos")
    public ResponseEntity<Tareas> crearTarea(@RequestBody Tareas item) throws SQLException {
        try {
            item.setCreatedAt(new Date());
            Tareas tareas = tareasRepository.save(item);
            return new ResponseEntity<>(tareas, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/api/v1/todos/{id}")
    public ResponseEntity<Tareas> actualizarTarea(@PathVariable(value = "id") Integer id,
            @Valid @RequestBody Tareas item) {
        try {
            Optional<Tareas> item_to_update = tareasRepository.findById(id);
            if (item_to_update.isPresent()) {
                Tareas tarea_actual = item_to_update.get();
                tarea_actual.setTitle(item.getTitle());
                tarea_actual.setStatus(item.getStatus());
                return new ResponseEntity<>(tareasRepository.save(tarea_actual), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/api/v1/todos/{id}")
    public ResponseEntity<HttpStatus> eliminarTarea(@PathVariable("id") Integer id) {
        try {
            Optional<Tareas> item_to_delete = tareasRepository.findById(id);
            if (item_to_delete.isPresent()) {
                Tareas tarea_actual = item_to_delete.get();
                tareasRepository.delete(tarea_actual);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

}
