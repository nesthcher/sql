package ru.nesthcher.sql.implementation.query.constructor.update;

import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;

import ru.nesthcher.sql.api.query.QueryUtil;
import ru.nesthcher.sql.api.query.constructor.update.AbstractQueryUpdate;
import ru.nesthcher.utils.container.Pair;

/**
 * Класс `SQLiteQueryUpdate` представляет собой конструктор SQL запроса для обновления данных в таблице SQLite.
 * Расширяет `AbstractQueryUpdate` и предоставляет реализацию метода `toString` для формирования SQL запроса.
 */
public final class SQLiteQueryUpdate extends AbstractQueryUpdate {
    /**
     * Конструктор класса `SQLiteQueryUpdate`.
     * @param table Название таблицы, которую необходимо обновить.
     */
    public SQLiteQueryUpdate(
            @NotNull String table
    ) {
        super(table);
    }

    /**
     * Формирует SQL запрос для обновления данных в таблице.
     * @return SQL запрос для обновления данных.
     */
    @Override
    public String toString() {
        Pair<String, ArrayList<Object>> whereConvertEntries = where.getConvertEntries();
        Pair<String, ArrayList<Object>> setConvertEntries = QueryUtil.convertEntries(entries, false);
        preparedObjects = setConvertEntries.getSecond();
        preparedObjects.addAll(whereConvertEntries.getSecond());

        return "UPDATE `" + table + "` SET " + setConvertEntries.getFirst() + " WHERE "
                + whereConvertEntries.getFirst() + (limitSize != 0 ? " LIMIT " + limitSize : "") + ";";
    }

}
