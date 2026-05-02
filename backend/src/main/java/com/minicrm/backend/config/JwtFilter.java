package com.minicrm.backend.config;

import com.minicrm.backend.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtFilter extends OncePerRequestFilter {
    
    private final JwtService jwtService;

    public JwtFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                String authHeader = request.getHeader("Authorization");

                // User kann nur Login Seite sehen, wenn kein Token vorhanden
                if (authHeader == null || !authHeader.startsWith("Bearer")) {
                    filterChain.doFilter(request, response);
                    return;
                }

                // Das Wort Bearer abschneiden
                String jwt = authHeader.substring(7);

                // Usernamen auslesen
                String username = jwtService.extractUsername(jwt);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());

                    // Token setzen
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }

                // Anfrage freigeben
                filterChain.doFilter(request, response);
            }
}
