package ru.nesthcher.sql.implementations.query.constructor.insert;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;

import ru.nesthcher.sql.interfaces.query.QueryUtil;
import ru.nesthcher.sql.interfaces.query.constructor.insert.AbstractQueryInsert;
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
