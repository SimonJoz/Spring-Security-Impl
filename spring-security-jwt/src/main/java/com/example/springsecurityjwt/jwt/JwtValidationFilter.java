package com.example.springsecurityjwt.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.lang.Strings;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtValidationFilter extends OncePerRequestFilter {

    public static final String BEARER_PREFIX = "Bearer ";
    public static final String AUTHORIZATION = "Authorization";

    private final String mySecretKey = "verySecureSecretKeyLongEnougthContainsSome2312312312DigithsAnd";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if (Strings.hasText(authorizationHeader) && authorizationHeader.startsWith(BEARER_PREFIX)) {
            String token = authorizationHeader.replace(BEARER_PREFIX, "");
            setAuthentication(token);
        }

        filterChain.doFilter(request, response);
    }


    @SuppressWarnings("unchecked")
    private void setAuthentication(String token) {
        try {
            Key key = Keys.hmacShaKeyFor(mySecretKey.getBytes());
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            String username = claimsJws.getBody().getSubject();
            var authorities = (List<Map<String, String>>) claimsJws.getBody().get("authorities");

            Set<SimpleGrantedAuthority> authoritiesList = authorities.stream()
                    .map(a -> new SimpleGrantedAuthority(a.get("authority"))).collect(Collectors.toSet());

            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(username, null, authoritiesList);

            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (JwtException e) {
            throw new IllegalStateException(String.format("Token '%s' cannot be trusted", token));
        }
    }
}
