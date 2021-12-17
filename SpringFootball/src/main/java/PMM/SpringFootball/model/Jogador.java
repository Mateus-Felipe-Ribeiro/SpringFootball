package PMM.SpringFootball.model;

import java.sql.Date;
import javax.persistence.*;

@Entity
@Table(name = "jogador")
public class Jogador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cod_jogador;

    @Column
    private String nome;

    @Column
    private String email;
    
    @Column
    private Date datanasc;

    public Jogador(String nome, String email, Date datanasc) {
        this.nome = nome;
        this.email = email;
        this.datanasc = datanasc;
    }

    public long getCod_jogador(){
        return cod_jogador;
    }

    public String getNome(){
        return nome;
    }

    public String getEmail(){
        return email;
    }

    public Date getDatasnasc(){
        return datanasc;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setDatanascimento(Date datanasc){
        this.datanasc = datanasc;
    }
        
    @Override
    public String toString(){
        return "Jogador [cod_jogador="+cod_jogador+", nome="+nome+", email="+email+", datanasc="+datanasc+"]";
    }
}
