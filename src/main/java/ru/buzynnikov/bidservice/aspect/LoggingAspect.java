package ru.buzynnikov.bidservice.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.util.Arrays;


/**
 * Аспект для ведения журнала (логирования) методов, отмеченных аннотацией @ToLog.
 * Позволяет записывать имя метода и переданные аргументы в файл log.txt.
 */
@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {

    /**
     * Метод-обертка вокруг аннотированных методов (@ToLog),
     * регистрирующий их запуск и передаваемые аргументы в журнал.
     *
     * @param joinPoint Информация о точке соединения (методе)
     * @return Результат выполнения оригинального метода
     * @throws Throwable Если оригинальный метод выбросил исключение
     */
    @Around(value = "@annotation(ToLog)")
    public Object fileLog(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();

        Object[] args = joinPoint.getArgs();

        writeToFile(methodName, args);

        return joinPoint.proceed();
    }

    /**
     * Вспомогательный приватный метод для записи информации в файл журнала.
     *
     * @param methodName Имя метода
     * @param args       Массив аргументов метода
     */
    private void writeToFile(String methodName, Object[] args){
        try(FileWriter writer = new FileWriter("log.txt", true)){
            writer.write(String.valueOf(LocalDateTime.now())
            + " " + methodName
            + ": " + Arrays.asList(args) + "\n");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
