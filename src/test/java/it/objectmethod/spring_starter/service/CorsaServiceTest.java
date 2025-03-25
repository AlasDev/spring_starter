package it.objectmethod.spring_starter.service;

import it.objectmethod.spring_starter.dto.CorsaDTO;
import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.entity.*;
import it.objectmethod.spring_starter.mapper.CorsaMapstructMapper;
import it.objectmethod.spring_starter.repository.AutistaRepository;
import it.objectmethod.spring_starter.repository.ClienteRepository;
import it.objectmethod.spring_starter.repository.CorsaRepository;
import it.objectmethod.spring_starter.util.StatoCorsa;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@MockitoSettings
@DisplayName("Unit Test CorsaService")
public class CorsaServiceTest {
    @Mock
    private CorsaRepository corsaRepository;

    @Mock
    private AutistaRepository autistaRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Spy
    private CorsaMapstructMapper corsaMapstructMapper = Mappers.getMapper(CorsaMapstructMapper.class);

    @InjectMocks
    private CorsaService corsaService;

    //getAll
    @Test
    void shouldReturnListOfCorsaDTO_whenInvokedGetAll() {
        //ARRANGE
        final List<CorsaDTO> expectedListOfDTO = List.of(
                new CorsaDTO(),
                new CorsaDTO(),
                new CorsaDTO());

        List<Corsa> corsaEntities = corsaMapstructMapper.mapToEntities(expectedListOfDTO);

        //ACT
        when(corsaRepository.findAll())
                .thenReturn(corsaEntities);

        List<CorsaDTO> actualListOfDTO = corsaService.getAll();

        //ASSERT
        assertThat(actualListOfDTO)
                .usingRecursiveComparison()
                .isEqualTo(expectedListOfDTO);
    }

    //getCorsa
    @Test
    void shouldReturnCorsaDTO_whenInvokedGetCorsa() {
        //ARRANGE
        final Long id = 1L;

        final CorsaDTO expectedDTO = CorsaDTO.builder()
                .id(id)
                .statoCorsa(StatoCorsa.STATO_COMPLETATA)
                .distanzaPercorsa(10.00)
                .costoCorsa(100.00)
                .indirizzoInizio("IndirizzoInizio bla bla")
                .indirizzoFine("IndirizzoFine bla bla bla")
                .dataPrenotazione(LocalDateTime.parse("2025-01-01T10:15:25"))
                .dataInizio(LocalDateTime.parse("2025-01-01T10:15:30"))
                .dataFine(LocalDateTime.parse("2025-01-01T10:16:30"))
                .autista(1L)
                .cliente(1L)
                .build();

        Corsa corsa = corsaMapstructMapper.mapToEntity(expectedDTO);

        when(corsaRepository.existsById(id)).thenReturn(true);

        when(corsaRepository.findById(id)).thenReturn(Optional.of(corsa));

        //ACT
        CorsaDTO actualDTO = corsaService.getCorsa(id);

        //ASSERT
        assertThat(actualDTO)
                .usingRecursiveComparison()
                .isEqualTo(expectedDTO);
    }

    //update
    @Test
    void shouldReturnCorsaDTO_whenInvokedUpdate() {
        // ARRANGE
        final CorsaDTO inputDTO = CorsaDTO.builder()
                .id(1L)
                .statoCorsa(StatoCorsa.STATO_COMPLETATA)
                .distanzaPercorsa(10.00)
                .costoCorsa(100.00)
                .indirizzoInizio("IndirizzoInizio bla bla")
                .indirizzoFine("IndirizzoFine bla bla bla")
                .dataPrenotazione(LocalDateTime.parse("2025-01-01T10:15:25"))
                .dataInizio(LocalDateTime.parse("2025-01-01T10:15:30"))
                .dataFine(LocalDateTime.parse("2025-01-01T10:16:30"))
                .autista(1L)
                .cliente(2L)
                .build();

        final CorsaDTO afterUpdateExpectedDTO = CorsaDTO.builder()
                .id(1L)
                .statoCorsa(StatoCorsa.STATO_COMPLETATA)
                .distanzaPercorsa(10.00)
                .costoCorsa(100.00)
                .indirizzoInizio("IndirizzoInizio bla bla")
                .indirizzoFine("IndirizzoFine bla bla bla")
                .dataPrenotazione(LocalDateTime.parse("2025-01-01T10:15:25"))
                .dataInizio(LocalDateTime.parse("2025-01-01T10:15:30"))
                .dataFine(LocalDateTime.parse("2025-01-01T10:16:30"))
                .autista(1L)
                .cliente(2L)
                .build();

        Veicolo mockVeicolo = Veicolo.builder().id(1L).build();

        Utente mockUtente1 = Utente.builder().id(1L).build();

        Utente mockUtente2 = Utente.builder().id(2L).build();

        Autista mockAutista = Autista.builder()
                .id(1L)
                .utente(mockUtente1)
                .veicolo(mockVeicolo)
                .build();

        Cliente mockCliente = Cliente.builder()
                .id(2L)
                .utente(mockUtente2)
                .build();

        Corsa mockCorsa = Corsa.builder().id(1L).build();

        // ACT
        when(corsaRepository.findById(1L)).thenReturn(Optional.of(mockCorsa));

        when(autistaRepository.findById(1L)).thenReturn(Optional.of(mockAutista));

        when(clienteRepository.findById(2L)).thenReturn(Optional.of(mockCliente));

        when(corsaService.updateCorsa(inputDTO)).thenReturn(afterUpdateExpectedDTO);

        CorsaDTO actualDTO = corsaService.updateCorsa(inputDTO);

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

        when(corsaRepository.existsById(id)).thenReturn(true);

        //ACT
        corsaService.deleteCorsa(id);

        //ASSERT
        verify(corsaRepository).deleteById(id);

    }

    //save
    @Test
    void shouldReturnCorsaDTO_whenInvokedSave() {
        // ARRANGE
        final CorsaDTO inputDTO = CorsaDTO.builder()
                .statoCorsa(StatoCorsa.STATO_COMPLETATA)
                .distanzaPercorsa(10.00)
                .costoCorsa(100.00)
                .indirizzoInizio("IndirizzoInizio bla bla")
                .indirizzoFine("IndirizzoFine bla bla bla")
                .dataPrenotazione(LocalDateTime.parse("2025-01-01T10:15:25"))
                .dataInizio(LocalDateTime.parse("2025-01-01T10:15:30"))
                .dataFine(LocalDateTime.parse("2025-01-01T10:16:30"))
                .autista(1L)
                .cliente(2L)
                .build();

        final CorsaDTO afterSaveExpectedDTO = CorsaDTO.builder()
                .id(1L)
                .statoCorsa(StatoCorsa.STATO_COMPLETATA)
                .distanzaPercorsa(10.00)
                .costoCorsa(100.00)
                .indirizzoInizio("IndirizzoInizio bla bla")
                .indirizzoFine("IndirizzoFine bla bla bla")
                .dataPrenotazione(LocalDateTime.parse("2025-01-01T10:15:25"))
                .dataInizio(LocalDateTime.parse("2025-01-01T10:15:30"))
                .dataFine(LocalDateTime.parse("2025-01-01T10:16:30"))
                .autista(1L)
                .cliente(2L)
                .build();

        Corsa saveEntity = corsaMapstructMapper.mapToEntity(inputDTO);
        Corsa savedEntity = corsaMapstructMapper.mapToEntity(afterSaveExpectedDTO);

        Autista mockAutista = Autista.builder()
                .id(1L)
                .build();
        Cliente mockCliente = Cliente.builder()
                .id(2L)
                .build();

        Corsa mockCorsa = Corsa.builder()
                .id(1L)
                .autista(mockAutista)
                .cliente(mockCliente)
                .build();

        // ACT
        when(autistaRepository.findById(1L)).thenReturn(Optional.of(mockAutista));

        when(clienteRepository.findById(2L)).thenReturn(Optional.of(mockCliente));

        when(corsaRepository.save(saveEntity)).thenReturn(savedEntity);

        CorsaDTO actualDTO = corsaService.save(inputDTO);

        // ASSERT
        assertThat(actualDTO)
                .usingRecursiveComparison()
                .isEqualTo(afterSaveExpectedDTO);
    }

    //page
    @Test
    void shouldReturnPageOfCorsaDTO_whenInvokedGetPage() {

        //ARRANGE
        Pageable pageable = PageRequest.of(0, 2);

        Autista mockAutista1 = Autista.builder().id(1L).build();
        Autista mockAutista2 = Autista.builder().id(2L).build();
        Autista mockAutista3 = Autista.builder().id(3L).build();

        Cliente mockCliente1 = Cliente.builder().id(1L).build();
        Cliente mockCliente2 = Cliente.builder().id(2L).build();
        Cliente mockCliente3 = Cliente.builder().id(3L).build();

        List<Corsa> corsaList = List.of(
                Corsa.builder()
                        .id(1L)
                        .statoCorsa(StatoCorsa.STATO_COMPLETATA)
                        .distanzaPercorsa(7.00)
                        .costoCorsa(70.00)
                        .indirizzoInizio("IndirizzoInizio bla bla 1")
                        .indirizzoFine("IndirizzoFine bla bla bla 1")
                        .dataPrenotazione(LocalDateTime.parse("2025-01-01T10:15:25"))
                        .dataInizio(LocalDateTime.parse("2025-01-01T10:15:30"))
                        .dataFine(LocalDateTime.parse("2025-01-01T10:16:30"))
                        .autista(mockAutista1)
                        .cliente(mockCliente1)
                        .build(),

                Corsa.builder()
                        .id(2L)
                        .statoCorsa(StatoCorsa.STATO_COMPLETATA)
                        .distanzaPercorsa(15.00)
                        .costoCorsa(150.00)
                        .indirizzoInizio("IndirizzoInizio bla bla 2")
                        .indirizzoFine("IndirizzoFine bla bla bla 2")
                        .dataPrenotazione(LocalDateTime.parse("2025-01-01T10:15:25"))
                        .dataInizio(LocalDateTime.parse("2025-01-01T10:15:30"))
                        .dataFine(LocalDateTime.parse("2025-01-01T10:16:30"))
                        .autista(mockAutista2)
                        .cliente(mockCliente2)
                        .build(),

                Corsa.builder()
                        .id(3L)
                        .statoCorsa(StatoCorsa.STATO_COMPLETATA)
                        .distanzaPercorsa(10.00)
                        .costoCorsa(100.00)
                        .indirizzoInizio("IndirizzoInizio bla bla 3")
                        .indirizzoFine("IndirizzoFine bla bla bla 3")
                        .dataPrenotazione(LocalDateTime.parse("2025-01-01T10:15:25"))
                        .dataInizio(LocalDateTime.parse("2025-01-01T10:15:30"))
                        .dataFine(LocalDateTime.parse("2025-01-01T10:16:30"))
                        .autista(mockAutista3)
                        .cliente(mockCliente3)
                        .build()
        );

        Page<Corsa> inputCorsaPage = new PageImpl<>(corsaList);

        PageDTO<CorsaDTO> expectedPageDto = new PageDTO<>(
                corsaMapstructMapper.mapToDtos(corsaList),
                2,
                2,
                true,
                false,
                0,
                2
        );

        //ACT
        when(corsaRepository.findAll(pageable)).thenReturn(inputCorsaPage);

        when(corsaMapstructMapper.mapToPageDTO(inputCorsaPage)).thenReturn(expectedPageDto);

        PageDTO<CorsaDTO> actualPageDto = corsaService.getPage(pageable);

        //ASSERT
        assertThat(actualPageDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedPageDto);
    }
}
