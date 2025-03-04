package it.objectmethod.spring_starter.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import it.objectmethod.spring_starter.dto.UtenteDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Classe responsabile della generazione e validazione dei token JWT.
 * Utilizza un algoritmo di firma HMAC con una chiave segreta per garantire l'integrità e l'autenticità del token.
 */
@Component
public class JwtTokenProvider {
    private final SecretKey SECRET_KEY;

    public JwtTokenProvider(@Value("${jwt.secret}") final String secret) {
        this.SECRET_KEY = Keys.hmacShaKeyFor(secret.getBytes());
    }

    /**
     * Estrae l'indirizzo email dal token JWT.
     * @param token il token JWT da cui estrarre l'email
     * @return l'indirizzo email estratto dal token
     */
    public String extractEmail(final String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Estrae la data di scadenza dal token JWT.
     * @param token il token JWT da cui estrarre la data di scadenza
     * @return la data di scadenza estratta dal token
     */
    public Date extractExpiration(final String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Estrae il valore associato alla chiave "name" dai claims del token JWT.
     * @param token il token JWT da cui estrarre il nome
     * @return il valore del campo "name" estratto dai claims del token
     */
    public Long extractIdFromClaims(final String token) {
        return extractClaim(token, claims -> claims.get("id", Long.class));
    }

    /**
     * Estrae un claim specifico dal token JWT utilizzando una funzione di risoluzione.
     * @param token          il token JWT da cui estrarre il claim
     * @param claimsResolver funzione che definisce come estrarre il claim dai claims
     * @param <R>            il tipo del claim da estrarre
     * @return il valore del claim estratto
     */
    public <R> R extractClaim(final String token, Function<Claims, R> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Estrae tutti i claims dal token JWT.
     * @param token il token JWT da cui estrarre i claims
     * @return i claims estratti dal token
     */
    private Claims extractAllClaims(final String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Verifica se il token JWT è scaduto.
     * @param token il token JWT da verificare
     * @return true se il token è scaduto, false altrimenti
     */
    public Boolean isTokenExpired(final String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Genera un nuovo token JWT basato sull'oggetto AuthorizationRequest.
     * @param request l'oggetto AuthorizationRequest contenente le informazioni per la generazione del token
     * @return il token JWT generato
     */
    public String generateToken(final UtenteDTO request) {
        final Map<String, Long> claims = new HashMap<>();
        claims.put("id", request.getId());
        return createToken(claims, request.getEmail());
    }

    /**
     * Crea un token JWT utilizzando i claims forniti e il soggetto specificato.
     * @param claims  la mappa dei claims da includere nel token
     * @param subject il soggetto del token (ad esempio, l'email dell'utente) e subject è una sorta di identifier
     * @return il token JWT creato
     */
    private String createToken(final Map<String, Long> claims, final String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SECRET_KEY)
                .compact();
    }

    /**
     * Valida un token JWT confrontando l'email estratta con il nome fornito nell'oggetto AuthorizationRequest
     * e verificando se il token è scaduto.
     * @param token il token JWT da validare
     * @return true se il token è valido, false altrimenti
     */
    public Boolean isValid(final String token) {
        final Claims claims = extractAllClaims(token);
        if (extractExpiration(token).equals(claims.getExpiration())) return Boolean.TRUE;
        return Boolean.FALSE;
//        return isTokenExpired(token);
    }
}