package br.grupointegrado.repository;

import br.grupointegrado.model.AuditPrecoCurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditPrecoCursoRepository extends JpaRepository<AuditPrecoCurso, Long> {

}
