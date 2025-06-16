package ru.nesthcher.sql.api.query.constructor.where;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import ru.nesthcher.sql.api.query.QuerySymbol;
import ru.nesthcher.sql.util.Pair;

/**
 * Интерфейс `AbstractQueryWhere` определяет методы для конструирования условия WHERE в SQL запросе.
 */
public interface AbstractQueryWhere {
    /**
     * Добавляет условие в конструктор WHERE.
     * @param column Название колонки.
     * @param symbol Символ сравнения.
     * @param result Значение для сравнения.
     */
    void put(@NotNull final String column, @NotNull final QuerySymbol symbol, final Object result);

    /**
     * Преобразует конструктор WHERE в SQL строку и список значений для prepared statement.
     * @return Пара (SQL строка, список значений для prepared statement).
     */
    Pair<String, List<Object>> getConvertEntries();
}
