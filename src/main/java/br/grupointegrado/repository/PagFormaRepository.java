package br.grupointegrado.repository;

import br.grupointegrado.model.PagForma;
import br.grupointegrado.model.PagFormaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagFormaRepository extends JpaRepository<PagForma, PagFormaId> {   
}
