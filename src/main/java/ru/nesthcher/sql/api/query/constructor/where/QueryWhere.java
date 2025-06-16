package ru.nesthcher.sql.api.query.constructor.where;

import java.util.LinkedHashMap;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import lombok.Getter;
import ru.nesthcher.sql.api.query.QuerySymbol;
import ru.nesthcher.sql.api.query.QueryUtil;
import ru.nesthcher.sql.util.Pair;

/**
 * Класс `QueryWhere` представляет собой конструктор условия WHERE в SQL запросе.
 */
@Getter
public class QueryWhere implements AbstractQueryWhere {
    /**
     * Карта записей, где ключ - название колонки, значение - пара (значение, символ сравнения).
     */
    private final LinkedHashMap<String, Pair<Object, Object>> entries = new LinkedHashMap<>();

    /**
     * Добавляет условие в конструктор WHERE.
     * @param column Название колонки.
     * @param symbol Символ сравнения.
     * @param result Значение для сравнения.
     * @throws IllegalArgumentException Если название колонки пустое.
     */
    @Override
    public void put(
            @NotNull final String column,
            @NotNull final QuerySymbol symbol,
            final Object result
    ) {
        if (column.isEmpty()) throw new IllegalArgumentException("Название колонки не может быть пустым");
        entries.put(column, new Pair<>(result, symbol));
    }

    /**
     * Преобразует конструктор WHERE в SQL строку и список значений для prepared statement.
     * @return Пара (SQL строка, список значений для prepared statement).
     */
    @Override
    public Pair<String, List<Object>> getConvertEntries() {
        return QueryUtil.convertEntries(entries, true);
    }
}
