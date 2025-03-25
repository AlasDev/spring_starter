package it.objectmethod.spring_starter.service;

import it.objectmethod.spring_starter.dto.PageDTO;
import it.objectmethod.spring_starter.dto.VeicoloDTO;
import it.objectmethod.spring_starter.entity.Autista;
import it.objectmethod.spring_starter.entity.Veicolo;
import it.objectmethod.spring_starter.mapper.VeicoloMapstructMapper;
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

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@MockitoSettings
@DisplayName("Unit Test VeicoloService")
public class VeicoloServiceTest {
    @Mock
    private VeicoloRepository veicoloRepository;

    @Spy
    private VeicoloMapstructMapper veicoloMapstructMapper = Mappers.getMapper(VeicoloMapstructMapper.class);

    @InjectMocks
    private VeicoloService veicoloService;

    //getAll
    @Test
    void shouldReturnListOfVeicoloDTO_whenInvokedGetAll() {
        //ARRANGE
        final List<VeicoloDTO> expectedListOfDTO = List.of(
                new VeicoloDTO(),
                new VeicoloDTO(),
                new VeicoloDTO());

        List<Veicolo> clienteEntities = veicoloMapstructMapper.mapToEntities(expectedListOfDTO);

        //ACT
        when(veicoloRepository.findAll())
                .thenReturn(clienteEntities);

        List<VeicoloDTO> actualListOfDTO = veicoloService.getAll();

        //ASSERT
        assertThat(actualListOfDTO)
                .usingRecursiveComparison()
                .isEqualTo(expectedListOfDTO);
    }

    //getVeicolo
    @Test
    void shouldReturnVeicoloDTO_whenInvokedGetVeicolo() {
        //ARRANGE
        final Long id = 1L;

        final VeicoloDTO expectedDTO = VeicoloDTO.builder()
                .id(id)
                .numTarga("ab123cd")
                .modello("fiat panda")
                .colore("bianca")
//                .autisti(1L)
                .build();

        Veicolo veicolo = veicoloMapstructMapper.mapToEntity(expectedDTO);

        when(veicoloRepository.existsById(id)).thenReturn(true);

        when(veicoloRepository.findById(id)).thenReturn(Optional.of(veicolo));

        //ACT
        VeicoloDTO actualDTO = veicoloService.getVeicolo(id);

        //ASSERT
        assertThat(actualDTO)
                .usingRecursiveComparison()
                .isEqualTo(expectedDTO);
    }

    //update
    @Test
    void shouldReturnVeicoloDTO_whenInvokedUpdate() {
        // ARRANGE
        final VeicoloDTO inputDTO = VeicoloDTO.builder()
                .id(1L)
                .numTarga("ab123cd")
                .modello("Toyota AE86")
                .colore("bianca")
                .autisti(null)
                .build();

        final VeicoloDTO afterUpdateExpectedDTO = VeicoloDTO.builder()
                .id(1L)
                .numTarga("ab123cd")
                .modello("Toyota AE86")
                .colore("bianca")
                .autisti(null)
                .build();

        Veicolo mockVeicolo = new Veicolo();
        mockVeicolo.setId(1L);

        // ACT
        when(veicoloRepository.findById(1L)).thenReturn(Optional.of(mockVeicolo));

        when(veicoloService.updateVeicolo(inputDTO)).thenReturn(afterUpdateExpectedDTO);

        VeicoloDTO actualDTO = veicoloService.updateVeicolo(inputDTO);

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

        when(veicoloRepository.existsById(id)).thenReturn(true);

        //ACT
        veicoloService.deleteVeicolo(id);

        //ASSERT
        verify(veicoloRepository).deleteById(id);
    }

    //save
    @Test
    void shouldReturnClienteDTO_whenInvokedSave() {

        // ARRANGE
        final VeicoloDTO inputDTO = VeicoloDTO.builder()
                .numTarga("ab123cd")
                .modello("Toyota AE86")
                .colore("bianca")
                .autisti(null)
                .build();

        final VeicoloDTO afterSaveExpectedDTO = VeicoloDTO.builder()
                .id(1L)
                .numTarga("ab123cd")
                .modello("Toyota AE86")
                .colore("bianca")
                .autisti(null)
                .build();

        Veicolo saveEntity = veicoloMapstructMapper.mapToEntity(inputDTO);

        Veicolo savedEntity = veicoloMapstructMapper.mapToEntity(afterSaveExpectedDTO);

        // ACT
        when(veicoloRepository.save(saveEntity)).thenReturn(savedEntity);

        VeicoloDTO actualDTO = veicoloService.save(inputDTO);

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

        Autista mockAutista1 = Autista.builder().id(1L).build();

        Autista mockAutista2 = Autista.builder().id(2L).build();

        Autista mockAutista3 = Autista.builder().id(3L).build();

        List<Veicolo> veicoloList = List.of(
                Veicolo.builder()
                        .id(1L)
                        .numTarga("aa111aa")
                        .modello("Fiat Panda")
                        .colore("bianca")
                        .autisti(List.of(mockAutista1))
                        .build(),

                Veicolo.builder()
                        .id(2L)
                        .numTarga("bb222bb")
                        .modello("Toyota AE86")
                        .colore("bianca")
                        .autisti(List.of(mockAutista2, mockAutista3))
                        .build(),

                Veicolo.builder()
                        .id(3L)
                        .numTarga("cc333cc")
                        .modello("Supra")
                        .colore("nera")
                        .autisti(List.of(mockAutista3))
                        .build()
        );

        Page<Veicolo> inputVeicoloPage = new PageImpl<>(veicoloList);

        PageDTO<VeicoloDTO> expectedPageDto = new PageDTO<>(
                veicoloMapstructMapper.mapToDtos(veicoloList),
                2,
                2,
                true,
                false,
                0,
                2
        );

        //ACT
        when(veicoloRepository.findAll(pageable)).thenReturn(inputVeicoloPage);

        when(veicoloMapstructMapper.mapToPageDTO(inputVeicoloPage)).thenReturn(expectedPageDto);

        PageDTO<VeicoloDTO> actualPageDto = veicoloService.getPage(pageable);

        //ASSERT
        assertThat(actualPageDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedPageDto);
    }
}
