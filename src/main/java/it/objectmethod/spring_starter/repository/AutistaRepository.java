package it.objectmethod.spring_starter.repository;

import it.objectmethod.spring_starter.entity.Autista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AutistaRepository extends JpaRepository<Autista, Long>, JpaSpecificationExecutor<Autista> {

    Optional<Autista> getAutistaById(Long id);

    //JPA Keywords
    Optional<List<Autista>> getAutistaByNome(String nome);
    
    Optional<List<Autista>> getAutistaByCognome(String cognome);

    Optional<List<Autista>> getAutistaByDataNascita(Date dataNascita);

    Optional<List<Autista>> getAutistaByCodFiscale(String codFiscale);

    Optional<List<Autista>> getAutistaByVeicoloId(Long veicoloId);


    //ricerca con più campi:
    //ricerca autisti con un certo nome e un certo cognome
    @Query(value = "SELECT * FROM autista a WHERE a.nome = $1 AND a.cognome = $2", nativeQuery = true)
    Optional<List<Autista>> getAutistaByNomeCognome(String nome, String cognome);
    //List<Autista> getAutistaByNomeAndCognome(String nome, String cognome);

    //ricerca autisti che hanno veicoli di un certo colore in base alla foreign key
    @Query(value = "SELECT a.* FROM autista a LEFT JOIN veicolo v ON a.veicolo_ID= v.veicolo_ID where v.colore = $1", nativeQuery = true)
    Optional<List<Autista>> getAutistaWithColoreVeicolo(String coloreVeicolo);
}