package ru.nesthcher.sql.implementation.query.constructor.select;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import ru.nesthcher.sql.api.query.constructor.select.AbstractQuerySelect;
import ru.nesthcher.sql.util.Pair;

/**
 * Класс `SQLiteQuerySelect` представляет собой конструктор SQL запроса для выборки данных из таблицы SQLite.
 * Расширяет `AbstractQuerySelect` и предоставляет реализацию метода `toString` для формирования SQL запроса.
 */
public final class SQLiteQuerySelect extends AbstractQuerySelect {
    /**
     * Конструктор класса `SQLiteQuerySelect`.
     * @param table Название таблицы, из которой необходимо выбрать данные.
     */
    public SQLiteQuerySelect(
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
