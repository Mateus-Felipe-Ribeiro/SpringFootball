package PMM.SpringFootball.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import PMM.SpringFootball.model.Jogador;

public interface JogadorRepository extends JpaRepository<Jogador, Long>{
    
    List<Jogador> findByNomeContaining(String nome);

    List<Jogador> findByDatanasc(Date datanasc);

}
