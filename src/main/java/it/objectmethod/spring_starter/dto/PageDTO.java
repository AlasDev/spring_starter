package it.objectmethod.spring_starter.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO<DTO> {
    /**
     * Il contenuto della pagina
     */
    @NotNull
    private List<DTO> content;

    /**
     * Numero di elementi massimi che la pagina può contenere
     */
    @NotNull
    private Integer size;

    /**
     * Numero di elementi effettivamente presenti nella pagina
     */
    @NotNull
    private Integer numberOfElements;

    /**
     * True quando sei alla pagina iniziale (pagina 0)
     */
    @NotNull
    private Boolean first;

    /**
     * True quando sei all'ultima pagina
     */
    @NotNull
    private Boolean last;

    /**
     * Numero della pagina (parte da 0)
     */
    @NotNull
    private Integer number;

    /**
     * Numero di pagine
     */
    @NotNull
    private Integer totalPages;

}