package ru.nesthcher.sql.implementation.query.constructor.insert;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;

import ru.nesthcher.sql.api.query.QueryUtil;
import ru.nesthcher.sql.api.query.constructor.insert.AbstractQueryInsert;
import ru.nesthcher.utils.container.Pair;

/**
 * Класс `MySQLQueryInsert` представляет собой конструктор SQL запроса для вставки данных в таблицу MySQL.
 * Расширяет `AbstractQueryInsert` и предоставляет реализацию метода `toString` для формирования SQL запроса.
 */
public final class MySQLQueryInsert extends AbstractQueryInsert {
    /**
     * Конструктор класса `MySQLQueryInsert`.
     * @param table Название таблицы, в которую необходимо вставить данные.
     */
    public MySQLQueryInsert(
            @NotNull final String table
    ) {
        super(table);
    }

    /**
     * Формирует SQL запрос для вставки данных в таблицу.
     * @return SQL запрос для вставки данных.
     */
    @Override
    public String toString() {
        final String columns = entries.keySet().stream().map(s -> "`" + s + "`")
                .collect(Collectors.joining(", "));
        final String values = entries.keySet().stream().map(s -> "?")
                .collect(Collectors.joining(", "));

        final Pair<String, ArrayList<Object>> convertEntries = QueryUtil.convertEntries(entries, false);
        preparedObjects = convertEntries.getSecond();

        return "INSERT INTO `" + table + "` (" + columns + ") VALUES (" + values + ");";
    }
}
