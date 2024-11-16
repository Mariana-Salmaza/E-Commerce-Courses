package br.grupointegrado.repository;

import br.grupointegrado.model.PedidoCurso;
import br.grupointegrado.model.PedidoCursoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoCursoRepository extends JpaRepository<PedidoCurso, PedidoCursoPK> {
}
