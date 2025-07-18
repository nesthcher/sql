package ru.nesthcher.sql.api;

/**
 * Интерфейс `ResponseHandler` определяет метод для обработки результата SQL запроса.
 * @param <H> Тип результата SQL запроса.
 * @param <R> Тип возвращаемого значения.
 */
public interface ResponseHandler<H, R> {
    /**
     * Обрабатывает результат SQL запроса.
     * @param handle Результат SQL запроса.
     * @return Результат обработки запроса.
     * @throws Exception Если произошла ошибка при обработке результата запроса.
     */
    R handleResponse(H handle) throws Exception;
}