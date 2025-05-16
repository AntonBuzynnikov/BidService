package ru.buzynnikov.bidservice.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация, используемая для пометки методов, выполнение которых должно быть зафиксировано в журнале (логировании).
 * Применяется для отслеживания операций, важных для мониторинга и анализа производительности системы.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ToLog {
}
