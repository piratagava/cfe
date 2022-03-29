package com.cfe.hernan.sistema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cfe.hernan.sistema.model.PeriodoFechas;

@Repository
public interface PeriodoFechasRepository extends JpaRepository<PeriodoFechas, Integer>{

}
