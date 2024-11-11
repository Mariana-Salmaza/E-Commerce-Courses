package br.grupointegrado.repository;

import br.grupointegrado.model.PedidoCurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoCursoRepository extends JpaRepository<PedidoCurso, Integer> {
}
