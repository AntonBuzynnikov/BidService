package ru.buzynnikov.bidservice.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Конфигурация безопасности приложения.
 * Настраивает правила доступа к различным ресурсам и механизм шифрования паролей.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Bean для шифрования паролей с использованием алгоритма bcrypt.
     *
     * @return Encoder для шифрования паролей
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * Основная конфигурация фильтров безопасности.
     * Устанавливает правила доступа к ресурсам приложения.
     *
     * @param http Объект конфигурации HTTP-безопасности
     * @return Собранная цепочка фильтрации безопасности
     * @throws Exception Возможные исключения при конфигурировании безопасности
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                                    .requestMatchers("/login/**","/login").permitAll()
                                    .requestMatchers("/bid/**","/bid").hasAnyRole("ADMIN","USER")
                                    .requestMatchers("/consumption/**","/product/**").hasRole("ADMIN"))
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
