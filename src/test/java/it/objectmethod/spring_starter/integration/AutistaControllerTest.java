package it.objectmethod.spring_starter.integration;

import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import it.objectmethod.spring_starter.BaseIntegrationTest;
import it.objectmethod.spring_starter.dto.AutistaDTO;
import it.objectmethod.spring_starter.dto.UtenteDTO;
import org.junit.jupiter.api.Test;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
public class AutistaControllerTest extends BaseIntegrationTest {

    @Test
    @Order(1)
    public void shouldReturnListOfAutistaDTO_whenInvokedEndpoint_GetAll() {
        List<AutistaDTO> expected = List.of(
                AutistaDTO.builder()
                        .id(1L)
                        .nome("nome03")
                        .cognome("cognome03")
                        .dataNascita(LocalDate.parse("1999-12-03"))
                        .codFiscale("GUIDOO11K88K333X")
                        .veicolo(1L)
                        .utente(3L)
                        .corse(List.of(
                                1L
                        ))
                        .build(),

                AutistaDTO.builder()
                        .id(2L)
                        .nome("nome04")
                        .cognome("cognome04")
                        .dataNascita(LocalDate.parse("1999-12-04"))
                        .codFiscale("SIVOLA13O40K444X")
                        .veicolo(2L)
                        .utente(4L)
                        .corse(List.of(
                                2L
                        ))
                        .build()
        );

        List<AutistaDTO> actual = given()
                .port(super.port)
                .contentType(ContentType.JSON)
                .when()
                .header("Authorization", jwtToken)
                .get("/api/autista/get/all")
                .prettyPeek()
                .then()
                .statusCode(200)
                .extract()
                .as(new TypeRef<>() {
                });

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    @Order(2)
    public void shouldReturnAutistaDTO_whenInvokedEndpoint_GetById() {
        AutistaDTO expected = AutistaDTO.builder()
                .id(1L)
                .nome("nome03")
                .cognome("cognome03")
                .dataNascita(LocalDate.parse("1999-12-03"))
                .codFiscale("GUIDOO11K88K333X")
                .veicolo(1L)
                .utente(3L)
                .corse(List.of(
                        1L
                ))
                .build();

        AutistaDTO actual = given()
                .port(super.port)
                .contentType(ContentType.JSON)
                .when()
                .header("Authorization", jwtToken)
                .get("/api/autista/get/{id}", 1)
                .prettyPeek()
                .then()
                .statusCode(200)
                .extract()
                .as(new TypeRef<>() {
                });

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    @Order(3)
    public void shouldReturnAutistaDTO_whenInvokedEndpoint_Update() {
        AutistaDTO expected = AutistaDTO.builder()
                .id(1L)
                .nome("nome03new")
                .cognome("cognome03new")
                .dataNascita(LocalDate.parse("1999-12-03"))
                .codFiscale("GUIDOO11K88K333X")
                .veicolo(1L)
                .utente(3L)
                .corse(List.of(
                        1L
                ))
                .build();

        AutistaDTO actual = given()
                .port(super.port)
                .contentType(ContentType.JSON)
                .when()
                .header("Authorization", jwtToken)
                .body(
                        AutistaDTO.builder()
                                .id(1L)
                                .nome("nome03new")
                                .cognome("cognome03new")
                                .dataNascita(LocalDate.parse("1999-12-03"))
                                .codFiscale("GUIDOO11K88K333X")
                                .veicolo(1L)
                                .utente(3L)
                                .corse(List.of(
                                        1L
                                ))
                                .build()
                )
                .put("/api/autista/update")
                .prettyPeek()
                .then()
                .statusCode(200)
                .extract()
                .as(new TypeRef<>() {
                });

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    @Order(4)
    public void shouldReturnVoid_whenInvokedEndpoint_DeleteById() {
        given()
                .port(super.port)
                .header("Authorization", jwtToken)
                .when()
                .delete("/api/autista/delete/{id}", 1)
                .then()
                .statusCode(200);

        given()
                .port(super.port)
                .header("Authorization", jwtToken)
                .when()
                .get("/api/autista/get/{id}", 1)
                .then()
                .statusCode(404);
    }

    @Test
    @Order(5)
    public void shouldReturnAutistaDTO_whenInvokedEndpoint_Post() {
        given()
                .port(super.port)
                .contentType(ContentType.JSON)
                .when()
                .header("Authorization", jwtToken)
                .body(
                        UtenteDTO.builder()
                                .email("emailJustAdded@email.com")
                                .password("password")
                                .build()
                )
                .post("/api/auth/register")
                .prettyPeek()
                .then()
                .statusCode(200);


        AutistaDTO expected = AutistaDTO.builder()
                .id(3L)
                .nome("nomeJustAdded")
                .cognome("cognomeJustAdded")
                .dataNascita(LocalDate.parse("1999-10-20"))
                .codFiscale("GUIDOO11K88K777X")
                .veicolo(3L)
                .utente(5L)
                .corse(List.of(
                        //nessuna corsa
                ))
                .build();

        AutistaDTO actual = given()
                .port(super.port)
                .contentType(ContentType.JSON)
                .when()
                .header("Authorization", jwtToken)
                .body(
                        AutistaDTO.builder()
                                .nome("nomeJustAdded")
                                .cognome("cognomeJustAdded")
                                .dataNascita(LocalDate.parse("1999-10-20"))
                                .codFiscale("GUIDOO11K88K777X")
                                .veicolo(3L)
                                .utente(5L)
                                .corse(List.of(
                                        //nessuna corsa
                                ))
                                .build()
                )
                .post("/api/autista/post")
                .prettyPeek()
                .then()
                .statusCode(200)
                .extract()
                .as(new TypeRef<>() {
                });

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

//    @Test
//    @Order(6)
//    public void shouldReturnAutistaDTO_whenInvokedEndpoint_Page() {}
//
//    @Test
//    @Order(7)
//    public void shouldReturnAutistaDTO_whenInvokedEndpoint_Filter() {}
}
