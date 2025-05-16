package ru.buzynnikov.bidservice.dto;

import lombok.Data;

/**
 * DTO-класс для представления данных о пользователе.
 * Используется для регистрации или аутентификации пользователей.
 */
@Data
public class UserDTO {
    private String login;
    private String password;
    private String confirmPassword;
}
