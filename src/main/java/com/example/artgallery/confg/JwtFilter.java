package com.example.artgallery.confg;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Value("${spring.secret}")
    private String secret;

    @Value("${spring.emissor}")
    private String emissor;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String caminho = request.getRequestURI();
        String metodo = request.getMethod();

        // 1. Libera imediatamente requisições OPTIONS (CORS preflight)
        if ("OPTIONS".equalsIgnoreCase(metodo)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2. Libera rotas públicas (incluindo /auth/login e /auth/cadastrar se necessário)
        if (caminho.startsWith("/auth") ||
                caminho.equals("/swagger-ui") ||
                caminho.startsWith("/swagger-ui/") ||
                caminho.equals("/webjars") ||
                caminho.equals("/swagger-resources") ||
                caminho.equals("/v3/api-docs")
        ) {
            filterChain.doFilter(request, response);
            return;
        }

        String headerToken = request.getHeader("Authorization");
        if (headerToken != null && headerToken.startsWith("Bearer ")) {

            String token = headerToken.replace("Bearer ", "");

            try {
                Algorithm algorithm = Algorithm.HMAC256(secret);
                JWTVerifier verifier = JWT.require(algorithm)
                        .withIssuer(emissor)
                        .build();

                var resultado = verifier.verify(token);
                System.out.println(resultado.getSubject());

                // IMPORTANTE: Deixa o fluxo continuar para a controller se o token for válido
                filterChain.doFilter(request, response);

            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token invalido ou expirado");
                return;
            }

        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token ausente ou invalido");
            return;
        }
    }
}