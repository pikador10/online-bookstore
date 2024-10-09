package com.hw.bookstore.security.auth.jwt;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.hw.bookstore.exception.TokenAuthenticationException;
import com.hw.bookstore.security.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Slf4j
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final HandlerExceptionResolver resolver;

    private final TokenProvider tokenProvider;

    private final CustomUserDetailsService customUserDetailsService;

    public TokenAuthenticationFilter(@Qualifier("handlerExceptionResolver")
                                     HandlerExceptionResolver resolver,
                                     TokenProvider tokenProvider,
                                     CustomUserDetailsService customUserDetailsService) {
        this.resolver = resolver;
        this.tokenProvider = tokenProvider;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            var jwt = getJwtFromRequest(request);
            tokenProvider.validateToken(jwt);
            var username = tokenProvider.getUsernameFromToken(jwt);
            var userDetails = customUserDetailsService.loadUserByUsername(username);

            var authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource()
                        .buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception ex) {
            log.error("Token authentication error", ex);
            resolver.resolveException(request, response, null,
                    new TokenAuthenticationException("Token authentication error", ex)
            );
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
