package PMM.SpringFootball.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import PMM.SpringFootball.model.Jogador;
import PMM.SpringFootball.repository.JogadorRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")

public class JogadorController {
    
    @Autowired
    JogadorRepository repo;

    /*
    *Get /api/jogadores : listar todos os jogadores 
    */
    @GetMapping("/jogadores")
    public ResponseEntity<List<Jogador>> getAllJogadores(@RequestParam(required = false) String nome)
    {
        try {
            List<Jogador> ljg = new ArrayList<Jogador>();

            if(nome == null){
                repo.findAll().forEach(ljg::add);
            }else{
                repo.findByNomeContaining(nome).forEach(ljg::add);
            }
            if(ljg.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(ljg, HttpStatus.OK);
            
        } catch (Exception e) {
            //TODO: handle exceptio
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    /*
    *Post /api/jogadores : criar jogador
    */

    @PostMapping("/jogadores")
    public ResponseEntity<Jogador> createJogador(@RequestBody Jogador jg)
    {
        try {
            Jogador _j = repo.save(new Jogador(jg.getNome(), jg.getEmail(), jg.getDatasnasc()));
            
            return new ResponseEntity<>(_j, HttpStatus.CREATED);
        } catch (Exception e) {
            //TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get /api/jogadores/:id : listar jogadores dado um id
     */
    @GetMapping("/jogadores/{id}")
    public ResponseEntity<Jogador> getJogadorById(@PathVariable("id") long id){
        Optional<Jogador> data = repo.findById(id);

        if(data.isPresent())
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Put /api/jogadores/:id  : atualizar jogador dado um id
     */

    @PutMapping("/jogadores/{id}")
    public ResponseEntity<Jogador> updateJogador(@PathVariable("id") long id, @RequestBody Jogador jg)
    {
        Optional<Jogador> data = repo.findById(id);

        if(data.isPresent()){
            Jogador jog = data.get();
            jog.setNome(jg.getNome());
            jog.setEmail(jg.getEmail());
            jog.setDatanascimento(jg.getDatasnasc());

            return new ResponseEntity<>(repo.save(jog), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Del /api/jogadores/:id : remover jogador dado um id
     */
    @DeleteMapping("/jogadores/{id}")
    public ResponseEntity<HttpStatus> deleteJogador(@PathVariable("id") long id)
    {
        try {
            repo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            //TODO: handle exception
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Del /api/jogadores : remover todos os jogadores
     */
    @DeleteMapping("/jogadores")
    public ResponseEntity<HttpStatus> deleteAllJogador()
    {
        try {
            repo.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            //TODO: handle exception
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
