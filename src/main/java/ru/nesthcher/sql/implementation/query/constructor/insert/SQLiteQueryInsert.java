package ru.nesthcher.sql.implementation.query.constructor.insert;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;

import ru.nesthcher.sql.api.query.QueryUtil;
import ru.nesthcher.sql.api.query.constructor.insert.AbstractQueryInsert;
import ru.nesthcher.utils.container.Pair;

/**
 * Класс `SQLiteQueryInsert` представляет собой конструктор SQL запроса для вставки данных в таблицу SQLite.
 * Расширяет `AbstractQueryInsert` и предоставляет реализацию метода `toString` для формирования SQL запроса.
 */
public final class SQLiteQueryInsert extends AbstractQueryInsert {
    /**
     * Конструктор класса `SQLiteQueryInsert`.
     * @param table Название таблицы, в которую необходимо вставить данные.
     */
    public SQLiteQueryInsert(
            @NotNull String table
    ) {
        super(table);
    }

    /**
     * Формирует SQL запрос для вставки данных в таблицу.
     * @return SQL запрос для вставки данных.
     */
    @Override
    public String toString() {
        String columns = entries.keySet().stream().map(s -> "`" + s + "`")
                .collect(Collectors.joining(", "));
        String values = entries.keySet().stream().map(s -> "?")
                .collect(Collectors.joining(", "));

        Pair<String, ArrayList<Object>> convertEntries = QueryUtil.convertEntries(entries, false);
        preparedObjects = convertEntries.getSecond();

        return "INSERT INTO `" + table + "` (" + columns + ") VALUES (" + values + ");";
    }
}
