package ru.nesthcher.sql.implementation.query.constructor.select;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import ru.nesthcher.sql.api.query.constructor.select.AbstractQuerySelect;
import ru.nesthcher.sql.util.Pair;

/**
 * Класс `MySQLQuerySelect` представляет собой конструктор SQL запроса для выборки данных из таблицы MySQL.
 * Расширяет `AbstractQuerySelect` и предоставляет реализацию метода `toString` для формирования SQL запроса.
 */
public final class MySQLQuerySelect extends AbstractQuerySelect {
    /**
     * Конструктор класса `MySQLQuerySelect`.
     * @param table Название таблицы, из которой необходимо выбрать данные.
     */
    public MySQLQuerySelect(
            @NotNull final String table
    ) {
        super(table);
    }

    /**
     * Формирует SQL запрос для выборки данных из таблицы.
     * @return SQL запрос для выборки данных.
     */
    @Override
    public String toString() {
        final Pair<String, List<Object>> convertEntries = where.getConvertEntries();
        preparedObjects = convertEntries.getSecond();
        return "SELECT " + (result == null ? "*" : "`" + result + "`") + " FROM `" + table
                + "` WHERE " + convertEntries.getFirst() + (limitSize != 0 ? " LIMIT " + limitSize : "") + ";";
    }
}
