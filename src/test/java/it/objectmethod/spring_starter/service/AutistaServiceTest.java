package it.objectmethod.spring_starter.service;

import it.objectmethod.spring_starter.dto.AutistaDTO;
import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.entity.Autista;
import it.objectmethod.spring_starter.entity.Utente;
import it.objectmethod.spring_starter.entity.Veicolo;
import it.objectmethod.spring_starter.mapper.AutistaMapstructMapper;
import it.objectmethod.spring_starter.repository.AutistaRepository;
import it.objectmethod.spring_starter.repository.UtenteRepository;
import it.objectmethod.spring_starter.repository.VeicoloRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@MockitoSettings
@DisplayName("Unit Test AutistaService")
public class AutistaServiceTest {
    @Mock
    private AutistaRepository autistaRepository;

    @Mock
    private UtenteRepository utenteRepository;

    @Mock
    private VeicoloRepository veicoloRepository;

    @Spy
    private AutistaMapstructMapper autistaMapstructMapper = Mappers.getMapper(AutistaMapstructMapper.class);

    @InjectMocks
    private AutistaService autistaService;

    //getAll
    @Test
    void shouldReturnListOfAutistaDTO_whenInvokedGetAll() {
        //ARRANGE
        final List<AutistaDTO> expectedListOfDTO = List.of(
                new AutistaDTO(),
                new AutistaDTO(),
                new AutistaDTO());

        List<Autista> autistaEntities = autistaMapstructMapper.mapToEntities(expectedListOfDTO);

        //ACT
        when(autistaRepository.findAll())
                .thenReturn(autistaEntities);

        List<AutistaDTO> actualListOfDTO = autistaService.getAll();

        //ASSERT
        assertThat(actualListOfDTO)
                .usingRecursiveComparison()
                .isEqualTo(expectedListOfDTO);
    }

    //getAutista
    @Test
    void shouldReturnAutistaDTO_whenInvokedGetAutista() {
        //ARRANGE
        final Long id = 1L;

        final AutistaDTO expectedDTO = AutistaDTO.builder()
                .id(id)
                .nome("Nome")
                .cognome("Cognome")
                .dataNascita(LocalDate.parse("1998-03-22"))
                .codFiscale("AAAAAA11B22CCCCD")
                .veicolo(1L)
                .utente(1L)
//                .corse()
                .build();

        Autista autista = autistaMapstructMapper.mapToEntity(expectedDTO);

        when(autistaRepository.existsById(id)).thenReturn(true);

        when(autistaRepository.findById(id)).thenReturn(Optional.of(autista));

        //ACT
        AutistaDTO actualDTO = autistaService.getAutista(id);

        //ASSERT
        assertThat(actualDTO)
                .usingRecursiveComparison()
                .isEqualTo(expectedDTO);
    }

    //update
    @Test
    void shouldReturnAutistaDTO_whenInvokedUpdate() {
        // ARRANGE
        final AutistaDTO inputDTO = AutistaDTO.builder()
                .id(1L)
                .nome("NuovoNome")
                .cognome("NuovoCognome")
                .dataNascita(LocalDate.parse("1999-03-22"))
                .codFiscale("NEWAAA11B22CCCCD")
                .veicolo(1L)
                .utente(2L)
                .build();

        final AutistaDTO afterUpdateExpectedDTO = AutistaDTO.builder()
                .id(1L)
                .nome("NuovoNome")
                .cognome("NuovoCognome")
                .dataNascita(LocalDate.parse("1999-03-22"))
                .codFiscale("NEWAAA11B22CCCCD")
                .veicolo(1L)
                .utente(2L)
                .build();

        Veicolo mockVeicolo = new Veicolo();
        mockVeicolo.setId(1L);

        Utente mockUtente = new Utente();
        mockUtente.setId(2L);

        Autista mockAutista = new Autista();
        mockAutista.setId(1L);
        mockAutista.setUtente(mockUtente);

        // ACT
        when(veicoloRepository.findById(1L)).thenReturn(Optional.of(mockVeicolo));

        when(autistaRepository.findById(1L)).thenReturn(Optional.of(mockAutista));

        when(utenteRepository.findById(2L)).thenReturn(Optional.of(mockUtente));

        when(autistaService.updateAutista(inputDTO)).thenReturn(afterUpdateExpectedDTO);

        AutistaDTO actualDTO = autistaService.updateAutista(inputDTO);

        // ASSERT
        assertThat(actualDTO)
                .usingRecursiveComparison()
                .isEqualTo(afterUpdateExpectedDTO);
    }

    //delete
    @Test
    void shouldReturnVoid_whenInvokedDelete() {
        //ARRANGE
        final Long id = 1L;

        when(autistaRepository.existsById(id)).thenReturn(true);

        //ACT
        autistaService.deleteAutista(id);

        //ASSERT
        verify(autistaRepository).deleteById(id);

    }

    //save
    @Test
    void shouldReturnAutistaDTO_whenInvokedSave() {
        // ARRANGE
        final AutistaDTO inputDTO = AutistaDTO.builder()
                .nome("Nome")
                .cognome("Cognome")
                .dataNascita(LocalDate.parse("1998-03-22"))
                .codFiscale("AAAAAA11B22CCCCD")
                .veicolo(1L)
                .utente(1L)
                .build();

        final AutistaDTO afterSaveExpectedDTO = AutistaDTO.builder()
                .id(1L)
                .nome("Nome")
                .cognome("Cognome")
                .dataNascita(LocalDate.parse("1998-03-22"))
                .codFiscale("AAAAAA11B22CCCCD")
                .veicolo(1L)
                .utente(1L)
                .build();

        Autista saveEntity = autistaMapstructMapper.mapToEntity(inputDTO);
        Autista savedEntity = autistaMapstructMapper.mapToEntity(afterSaveExpectedDTO);

        Veicolo mockVeicolo = new Veicolo();
        mockVeicolo.setId(1L);
        Utente mockUtente = new Utente();
        mockUtente.setId(1L);

        // ACT
        when(veicoloRepository.findById(1L)).thenReturn(Optional.of(mockVeicolo));

        when(utenteRepository.findById(1L)).thenReturn(Optional.of(mockUtente));

        when(autistaRepository.save(saveEntity)).thenReturn(savedEntity);

        AutistaDTO actualDTO = autistaService.save(inputDTO);

        // ASSERT
        assertThat(actualDTO)
                .usingRecursiveComparison()
                .isEqualTo(afterSaveExpectedDTO);
    }

    //page
    @Test
    void shouldReturnPageOfAutistaDTO_whenInvokedGetPage() {

        //ARRANGE
        Pageable pageable = PageRequest.of(0, 2);

        Utente mockUtente1 = Utente.builder().id(1L).build();
        Utente mockUtente2 = Utente.builder().id(2L).build();
        Utente mockUtente3 = Utente.builder().id(3L).build();

        Veicolo mockVeicolo1 = Veicolo.builder().id(1L).build();
        Veicolo mockVeicolo2 = Veicolo.builder().id(2L).build();
        Veicolo mockVeicolo3 = Veicolo.builder().id(3L).build();

        List<Autista> autistaList = List.of(
                Autista.builder()
                        .id(1L)
                        .nome("Nome1")
                        .cognome("Cognome1")
                        .dataNascita(LocalDate.parse("1991-01-01"))
                        .codFiscale("AAAAAA11A11AAAAA")
                        .veicolo(mockVeicolo1)
                        .utente(mockUtente1)
                        .build(),

                Autista.builder()
                        .id(2L)
                        .nome("Nome2")
                        .cognome("Cognome2")
                        .dataNascita(LocalDate.parse("1992-02-01"))
                        .codFiscale("BBBBBB22B22BBBBB")
                        .veicolo(mockVeicolo2)
                        .utente(mockUtente2)
                        .build(),

                Autista.builder()
                        .id(3L)
                        .nome("Nome3")
                        .cognome("Cognome3")
                        .dataNascita(LocalDate.parse("1993-03-01"))
                        .codFiscale("BBBBBB22B22BBBBB")
                        .veicolo(mockVeicolo3)
                        .utente(mockUtente3)
                        .build()
        );

        Page<Autista> inputAutistaPage = new PageImpl<>(autistaList);

        PageDTO<AutistaDTO> expectedPageDto = new PageDTO<>(
                autistaMapstructMapper.mapToDtos(autistaList),
                2,
                2,
                true,
                false,
                0,
                2
        );

        //ACT
        when(autistaRepository.findAll(pageable)).thenReturn(inputAutistaPage);

        when(autistaMapstructMapper.mapToPageDTO(inputAutistaPage)).thenReturn(expectedPageDto);

        PageDTO<AutistaDTO> actualPageDto = autistaService.getPage(pageable);

        //ASSERT
        assertThat(actualPageDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedPageDto);
    }
}