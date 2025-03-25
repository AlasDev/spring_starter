package it.objectmethod.spring_starter.service;

import it.objectmethod.spring_starter.dto.ClienteDTO;
import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.entity.Cliente;
import it.objectmethod.spring_starter.entity.Utente;
import it.objectmethod.spring_starter.mapper.ClienteMapstructMapper;
import it.objectmethod.spring_starter.repository.ClienteRepository;
import it.objectmethod.spring_starter.repository.UtenteRepository;
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
@DisplayName("Unit Test ClienteService")
public class ClienteServiceTest {
    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private UtenteRepository utenteRepository;

    @Spy
    private ClienteMapstructMapper clienteMapstructMapper = Mappers.getMapper(ClienteMapstructMapper.class);

    @InjectMocks
    private ClienteService clienteService;

    //getAll
    @Test
    void shouldReturnListOfClienteDTO_whenInvokedGetAll() {
        //ARRANGE
        final List<ClienteDTO> expectedListOfDTO = List.of(
                new ClienteDTO(),
                new ClienteDTO(),
                new ClienteDTO());

        List<Cliente> clienteEntities = clienteMapstructMapper.mapToEntities(expectedListOfDTO);

        //ACT
        when(clienteRepository.findAll())
                .thenReturn(clienteEntities);

        List<ClienteDTO> actualListOfDTO = clienteService.getAll();

        //ASSERT
        assertThat(actualListOfDTO)
                .usingRecursiveComparison()
                .isEqualTo(expectedListOfDTO);
    }

    //getCliente
    @Test
    void shouldReturnClienteDTO_whenInvokedGetCliente() {
        //ARRANGE
        final Long id = 1L;

        final ClienteDTO expectedDTO = ClienteDTO.builder()
                .id(id)
                .nome("Nome")
                .cognome("Cognome")
                .dataNascita(LocalDate.parse("1998-03-22"))
                .codFiscale("AAAAAA11B22CCCCD")
                .dataIscrizione(LocalDate.parse("2024-01-15"))
                .utente(1L)
                .build();

        Cliente cliente = clienteMapstructMapper.mapToEntity(expectedDTO);

        when(clienteRepository.existsById(id)).thenReturn(true);

        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));

        //ACT
        ClienteDTO actualDTO = clienteService.getCliente(id);

        //ASSERT
        assertThat(actualDTO)
                .usingRecursiveComparison()
                .isEqualTo(expectedDTO);
    }

    //update
    @Test
    void shouldReturnClienteDTO_whenInvokedUpdate() {
        // ARRANGE
        final ClienteDTO inputDTO = ClienteDTO.builder()
                .id(1L)
                .nome("NuovoNome")
                .cognome("NuovoCognome")
                .dataNascita(LocalDate.parse("1999-03-22"))
                .codFiscale("NEWAAA11B22CCCCD")
                .dataIscrizione(LocalDate.parse("2025-01-15"))
                .utente(2L)
                .build();

        final ClienteDTO afterUpdateExpectedDTO = ClienteDTO.builder()
                .id(1L)
                .nome("NuovoNome")
                .cognome("NuovoCognome")
                .dataNascita(LocalDate.parse("1999-03-22"))
                .codFiscale("NEWAAA11B22CCCCD")
                .dataIscrizione(LocalDate.parse("2025-01-15"))
                .utente(2L)
                .build();

        Utente mockUtente = new Utente();
        mockUtente.setId(2L);

        Cliente mockCliente = new Cliente();
        mockCliente.setId(1L);
        mockCliente.setUtente(mockUtente);

        // ACT
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(mockCliente));

        when(utenteRepository.findById(2L)).thenReturn(Optional.of(mockUtente));

        when(clienteService.updateCliente(inputDTO)).thenReturn(afterUpdateExpectedDTO);

        ClienteDTO actualDTO = clienteService.updateCliente(inputDTO);

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

        when(clienteRepository.existsById(id)).thenReturn(true);

        //ACT
        clienteService.deleteCliente(id);

        //ASSERT
        verify(clienteRepository).deleteById(id);

    }

    //save
    @Test
    void shouldReturnClienteDTO_whenInvokedSave() {
        // ARRANGE
        final ClienteDTO inputDTO = ClienteDTO.builder()
                .nome("Nome")
                .cognome("Cognome")
                .dataNascita(LocalDate.parse("1998-03-22"))
                .codFiscale("AAAAAA11B22CCCCD")
                .dataIscrizione(LocalDate.parse("2024-01-15"))
                .utente(1L)
                .build();

        final ClienteDTO afterSaveExpectedDTO = ClienteDTO.builder()
                .id(1L)
                .nome("Nome")
                .cognome("Cognome")
                .dataNascita(LocalDate.parse("1998-03-22"))
                .codFiscale("AAAAAA11B22CCCCD")
                .dataIscrizione(LocalDate.parse("2024-01-15"))
                .utente(1L)
                .build();

        Cliente saveEntity = clienteMapstructMapper.mapToEntity(inputDTO);
        Cliente savedEntity = clienteMapstructMapper.mapToEntity(afterSaveExpectedDTO);

        Utente mockUtente = new Utente();
        mockUtente.setId(1L);

        // ACT
        when(utenteRepository.findById(1L)).thenReturn(Optional.of(mockUtente));

        when(clienteRepository.save(saveEntity)).thenReturn(savedEntity);

        ClienteDTO actualDTO = clienteService.save(inputDTO);

        // ASSERT
        assertThat(actualDTO)
                .usingRecursiveComparison()
                .isEqualTo(afterSaveExpectedDTO);
    }

    //page
    @Test
    void shouldReturnPageOfClienteDTO_whenInvokedGetPage() {

        //ARRANGE
        Pageable pageable = PageRequest.of(0, 2);

        Utente mockUtente1 = Utente.builder().id(1L).build();

        Utente mockUtente2 = Utente.builder().id(2L).build();

        Utente mockUtente3 = Utente.builder().id(3L).build();

        List<Cliente> clienteList = List.of(
                Cliente.builder()
                        .id(1L)
                        .nome("Nome1")
                        .cognome("Cognome1")
                        .dataNascita(LocalDate.parse("1991-01-01"))
                        .codFiscale("AAAAAA11A11AAAAA")
                        .dataIscrizione(LocalDate.parse("1991-01-02"))
                        .utente(mockUtente1)
                        .build(),

                Cliente.builder()
                        .id(2L)
                        .nome("Nome2")
                        .cognome("Cognome2")
                        .dataNascita(LocalDate.parse("1992-02-01"))
                        .codFiscale("BBBBBB22B22BBBBB")
                        .dataIscrizione(LocalDate.parse("1992-02-02"))
                        .utente(mockUtente2)
                        .build(),

                Cliente.builder()
                        .id(3L)
                        .nome("Nome3")
                        .cognome("Cognome3")
                        .dataNascita(LocalDate.parse("1993-03-01"))
                        .codFiscale("BBBBBB22B22BBBBB")
                        .dataIscrizione(LocalDate.parse("1993-03-02"))
                        .utente(mockUtente3)
                        .build()
        );

        Page<Cliente> inputClientePage = new PageImpl<>(clienteList);

        PageDTO<ClienteDTO> expectedPageDto = new PageDTO<>(
                clienteMapstructMapper.mapToDtos(clienteList),
                2,
                2,
                true,
                false,
                0,
                2
        );

        //ACT
        when(clienteRepository.findAll(pageable)).thenReturn(inputClientePage);

        when(clienteMapstructMapper.mapToPageDTO(inputClientePage)).thenReturn(expectedPageDto);

        PageDTO<ClienteDTO> actualPageDto = clienteService.getPage(pageable);

        //ASSERT
        assertThat(actualPageDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedPageDto);
    }
}