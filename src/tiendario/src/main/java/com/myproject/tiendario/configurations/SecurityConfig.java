package com.myproject.tiendario.configurations;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final JwtFilter jwtFilter;

    public SecurityConfig(CustomUserDetailsService userDetailsService,
            JwtFilter jwtFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        // Endpoints públicos
                        .requestMatchers("/register", "/login", "/validate").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()

                        // Endpoints administrativos (Solo ADMINISTRADOR)
                        .requestMatchers("/api/usuarios/**").hasRole("ADMINISTRADOR")
                        .requestMatchers("/api/roles/**").hasRole("ADMINISTRADOR")

                        // Endpoints de gestión completa (ADMINISTRADOR y ENCARGADO_INVENTARIO)
                        .requestMatchers(HttpMethod.POST, "/api/productos/**")
                        .hasAnyRole("ADMINISTRADOR", "ENCARGADO_INVENTARIO")
                        .requestMatchers(HttpMethod.PUT, "/api/productos/**")
                        .hasAnyRole("ADMINISTRADOR", "ENCARGADO_INVENTARIO")
                        .requestMatchers(HttpMethod.DELETE, "/api/productos/**")
                        .hasAnyRole("ADMINISTRADOR", "ENCARGADO_INVENTARIO")
                        .requestMatchers(HttpMethod.POST, "/api/categorias/**")
                        .hasAnyRole("ADMINISTRADOR", "ENCARGADO_INVENTARIO")
                        .requestMatchers(HttpMethod.PUT, "/api/categorias/**")
                        .hasAnyRole("ADMINISTRADOR", "ENCARGADO_INVENTARIO")
                        .requestMatchers(HttpMethod.DELETE, "/api/categorias/**")
                        .hasAnyRole("ADMINISTRADOR", "ENCARGADO_INVENTARIO")
                        .requestMatchers("/api/proveedores/**").hasAnyRole("ADMINISTRADOR", "ENCARGADO_INVENTARIO")
                        .requestMatchers("/api/movimientos/**").hasAnyRole("ADMINISTRADOR", "ENCARGADO_INVENTARIO")

                        // Endpoints de CONSULTA (VENDEDOR)
                        .requestMatchers(HttpMethod.GET, "/api/productos/**")
                        .hasAnyRole("ADMINISTRADOR", "ENCARGADO_INVENTARIO", "VENDEDOR")
                        .requestMatchers(HttpMethod.GET, "/api/categorias/**")
                        .hasAnyRole("ADMINISTRADOR", "ENCARGADO_INVENTARIO", "VENDEDOR")

                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // Permitir solo el frontend, en desarrollo puedes dejar "*"
        config.setAllowedOrigins(List.of("http://localhost:3000"));

        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));

        // IMPORTANTE: Si usas cookies o Authorization Header, necesitas esto:
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}