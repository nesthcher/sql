package ru.nesthcher.sql.api.query;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jetbrains.annotations.NotNull;

import lombok.experimental.UtilityClass;
import ru.nesthcher.utils.container.Pair;

/**
 * Класс `QueryUtil` предоставляет утилитарные методы для работы с SQL запросами.
 */
@UtilityClass
public class QueryUtil {
    /**
     * Преобразует карту записей в SQL строку и список значений для prepared statement.
     * @param entrys Карта записей, где ключ - название колонки, значение - пара (значение, символ сравнения).
     * @param isWhere Флаг, указывающий, является ли это условие WHERE.
     * @return Пара (SQL строка, список значений для prepared statement).
     * @throws IllegalArgumentException Если карта записей пуста.
     */
    public @NotNull Pair<String, ArrayList<Object>> convertEntries(
            @NotNull LinkedHashMap<String, Pair<Object, Object>> entrys,
            boolean isWhere
    ) {
        if (entrys.isEmpty()) throw new IllegalArgumentException("Entries не может быть пустым.");
        StringBuilder sb = new StringBuilder();
        ArrayList<Object> values = new ArrayList<>();
        int size = 0;
        String column;
        for (Map.Entry<String, Pair<Object, Object>> entry : entrys.entrySet()) {
            column = entry.getKey();
            values.add(entry.getValue().getFirst());
            sb.append("`")
                    .append(column)
                    .append("` ")
                    .append(isWhere ? ((QuerySymbol) entry.getValue().getSecond()).getSymbol() : "=")
                    .append(" ?");
            size++;
            if (size < entrys.size())
                sb.append(isWhere ? " AND " : " , ");
        }

        return new Pair<>(sb.toString(), values);
    }
}
