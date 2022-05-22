package hu.webuni.airport.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class JwtService {

    private static final String AUTH = "auth";
    private Algorithm alg = Algorithm.HMAC256("mysecret");
    private String issuer = "AirportApp";

    public String createJwtToken(UserDetails principal) {

        return JWT.create().withSubject(principal.getUsername())
                .withArrayClaim("auth", principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new))
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(2)))
                .withIssuer(issuer)
                .sign(alg);

    }

    public UserDetails parseJwt(String jwtToken) {

        DecodedJWT decodedJWT = JWT.require(alg).withIssuer(issuer).build().verify(jwtToken);
        return new User(decodedJWT.getSubject(), "dummy",
                decodedJWT.getClaim(AUTH).asList(String.class).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
        );
    }
}
