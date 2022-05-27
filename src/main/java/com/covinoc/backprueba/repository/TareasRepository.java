package com.covinoc.backprueba.repository;

import java.util.List;

import com.covinoc.backprueba.model.Tareas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareasRepository extends JpaRepository<Tareas, Integer> {
    /**
     * Se define el metodo buscar todos ordenados por fecha de creacion
     */
    public List<Tareas> findAllByOrderByCreatedAtDesc();
}
