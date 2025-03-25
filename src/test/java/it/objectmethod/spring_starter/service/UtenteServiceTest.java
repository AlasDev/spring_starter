package it.objectmethod.spring_starter.service;

import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.dto.UtenteDTO;
import it.objectmethod.spring_starter.entity.Ruolo;
import it.objectmethod.spring_starter.entity.Utente;
import it.objectmethod.spring_starter.entity.UtenteRuolo;
import it.objectmethod.spring_starter.mapper.UtenteMapstructMapper;
import it.objectmethod.spring_starter.repository.UtenteRepository;
import it.objectmethod.spring_starter.util.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@MockitoSettings
@DisplayName("Unit Test UtenteService")
public class UtenteServiceTest {
    @Mock
    private UtenteRepository utenteRepository;

    @Spy
    private UtenteMapstructMapper utenteMapstructMapper = Mappers.getMapper(UtenteMapstructMapper.class);

    @InjectMocks
    private UtenteService utenteService;

    //getAll
    @Test
    void shouldReturnListOfUtenteDTO_whenInvokedGetAll() {
        //ARRANGE
        final List<UtenteDTO> expectedListOfDTO = List.of(
                UtenteDTO.builder()
                        .id(1L)
                        .ruoli(List.of(Role.USER))
                        .build(),
                UtenteDTO.builder()
                        .id(2L)
                        .ruoli(List.of(Role.USER))
                        .build(),
                UtenteDTO.builder()
                        .id(3L)
                        .ruoli(List.of(Role.USER))
                        .build()
        );

        List<Utente> utenteEntities = utenteMapstructMapper.mapToEntities(expectedListOfDTO);

        //ACT
        when(utenteRepository.findAll())
                .thenReturn(utenteEntities);

        List<UtenteDTO> actualListOfDTO = utenteService.getAll();

        //ASSERT
        assertThat(actualListOfDTO)
                .usingRecursiveComparison()
                .isEqualTo(expectedListOfDTO);
    }

    //getCorsa
    @Test
    void shouldReturnUtenteDTO_whenInvokedGetCorsa() {
        //ARRANGE
        final Long id = 1L;

        final UtenteDTO expectedDTO = UtenteDTO.builder()
                .id(1L)
                .ruoli(List.of(Role.USER))
                .build();

        Utente utente = utenteMapstructMapper.mapToEntity(expectedDTO);

        when(utenteRepository.existsById(id)).thenReturn(true);

        when(utenteRepository.findById(id)).thenReturn(Optional.of(utente));

        //ACT
        UtenteDTO actualDTO = utenteService.getUtente(id);

        //ASSERT
        assertThat(actualDTO)
                .usingRecursiveComparison()
                .isEqualTo(expectedDTO);
    }

    //update
    @Test
    void shouldReturnUtenteDTO_whenInvokedUpdate() {
        // ARRANGE
        final UtenteDTO inputDTO = UtenteDTO.builder()
                .id(1L)
                .email("mymail123@gmail.com")
                .password("password123")
                .ruoli(List.of(Role.USER))
                .build();

        final UtenteDTO afterUpdateExpectedDTO = UtenteDTO.builder()
                .id(1L)
                .email("mymail123@gmail.com")
                .password("password123")
                .ruoli(List.of(Role.USER))
                .build();

        Ruolo mockRuolo = Ruolo.builder()
                .id(2L)
                .nome("USER")
                .build();
        UtenteRuolo mockUtenteRuolo = new UtenteRuolo(utenteMapstructMapper.mapToEntity(inputDTO), mockRuolo);
        Utente mockUtente = Utente.builder()
                .id(1L)
                .utenteRuoli(List.of(mockUtenteRuolo))
                .build();

        // ACT
        when(utenteRepository.findById(1L)).thenReturn(Optional.of(mockUtente));

        when(utenteService.updateUtente(inputDTO)).thenReturn(afterUpdateExpectedDTO);

        UtenteDTO actualDTO = utenteService.updateUtente(inputDTO);

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

        when(utenteRepository.existsById(id)).thenReturn(true);

        //ACT
        utenteService.deleteUtente(id);

        //ASSERT
        verify(utenteRepository).deleteById(id);

    }

    //save
    @Test
    void shouldReturnUtenteDTO_whenInvokedSave() {
        // ARRANGE
        final UtenteDTO inputDTO = UtenteDTO.builder()
                .ruoli(List.of(Role.USER))
                .build();

        final UtenteDTO afterSaveExpectedDTO = UtenteDTO.builder()
                .id(1L)
                .ruoli(List.of(Role.USER))
                .build();

        Utente saveEntity = utenteMapstructMapper.mapToEntity(inputDTO);
        Utente savedEntity = utenteMapstructMapper.mapToEntity(afterSaveExpectedDTO);

        // ACT
        when(utenteRepository.save(saveEntity)).thenReturn(savedEntity);

        UtenteDTO actualDTO = utenteService.save(inputDTO);

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

        Ruolo mockRuolo1 = Ruolo.builder()
                .id(1L)
                .nome("USER")
                .build();

        Utente mockUtente1 = Mockito.mock(Utente.class);

        Utente mockUtente2 = Mockito.mock(Utente.class);

        Utente mockUtente3 = Mockito.mock(Utente.class);

        UtenteRuolo mockUtenteRuolo1 = new UtenteRuolo(mockUtente1, mockRuolo1);

        UtenteRuolo mockUtenteRuolo2 = new UtenteRuolo(mockUtente2, mockRuolo1);

        UtenteRuolo mockUtenteRuolo3 = new UtenteRuolo(mockUtente3, mockRuolo1);

        List<Utente> utenteList = List.of(
                Utente.builder()
                        .id(1L)
                        .utenteRuoli(List.of(mockUtenteRuolo1))
                        .build(),

                Utente.builder()
                        .id(2L)
                        .utenteRuoli(List.of(mockUtenteRuolo2))
                        .build(),

                Utente.builder()
                        .id(3L)
                        .utenteRuoli(List.of(mockUtenteRuolo3))
                        .build()
        );

        Page<Utente> inputUtentePage = new PageImpl<>(utenteList);

        PageDTO<UtenteDTO> expectedPageDto = new PageDTO<>(
                utenteMapstructMapper.mapToDtos(utenteList),
                2,
                2,
                true,
                false,
                0,
                2
        );

        //ACT
        when(utenteRepository.findAll(pageable)).thenReturn(inputUtentePage);

        when(utenteMapstructMapper.mapToPageDTO(inputUtentePage)).thenReturn(expectedPageDto);

        PageDTO<UtenteDTO> actualPageDto = utenteService.getPage(pageable);

        //ASSERT
        assertThat(actualPageDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedPageDto);
    }
}
