package ru.nesthcher.sql.implementation.query.constructor.update;

import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;

import ru.nesthcher.sql.api.query.QueryUtil;
import ru.nesthcher.sql.api.query.constructor.update.AbstractQueryUpdate;
import ru.nesthcher.utils.container.Pair;

/**
 * Класс `MySQLQueryUpdate` представляет собой конструктор SQL запроса для обновления данных в таблице MySQL.
 * Расширяет `AbstractQueryUpdate` и предоставляет реализацию метода `toString` для формирования SQL запроса.
 */
public final class MySQLQueryUpdate extends AbstractQueryUpdate {
    /**
     * Конструктор класса `MySQLQueryUpdate`.
     * @param table Название таблицы, которую необходимо обновить.
     */
    public MySQLQueryUpdate(
            @NotNull final String table
    ) {
        super(table);
    }

    /**
     * Формирует SQL запрос для обновления данных в таблице.
     * @return SQL запрос для обновления данных.
     */
    @Override
    public String toString() {
        final Pair<String, ArrayList<Object>> whereConvertEntries = where.getConvertEntries();
        final Pair<String, ArrayList<Object>> setConvertEntries = QueryUtil.convertEntries(entries, false);
        preparedObjects = setConvertEntries.getSecond();
        preparedObjects.addAll(whereConvertEntries.getSecond());

        return "UPDATE `" + table + "` SET " + setConvertEntries.getFirst() + " WHERE "
                + whereConvertEntries.getFirst() + (limitSize != 0 ? " LIMIT " + limitSize : "") + ";";
    }

}
