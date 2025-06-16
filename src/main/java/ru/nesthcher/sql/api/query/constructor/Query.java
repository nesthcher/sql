package ru.nesthcher.sql.api.query.constructor;

import java.util.List;

/**
 * Интерфейс `Query` определяет методы для работы с SQL запросами.
 */
public interface Query {
    /**
     * Преобразует конструктор запроса в SQL строку.
     * @return SQL строка, представляющая конструктор запроса.
     */
    @Override String toString();

    /**
     * Возвращает список значений для prepared statement.
     * @return Список значений для prepared statement.
     */
    List<Object> getPreparedObjects();
}
