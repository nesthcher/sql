package ru.nesthcher.sql.implementation.query.constructor.delete;

import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;

import ru.nesthcher.sql.interfaces.query.constructor.delete.AbstractQueryDelete;
import ru.nesthcher.utils.container.Pair;

/**
 * Класс `MySQLQueryDelete` представляет собой конструктор SQL запроса для удаления данных из таблицы MySQL.
 * Расширяет `AbstractQueryDelete` и предоставляет реализацию метода `toString` для формирования SQL запроса.
 */
public final class MySQLQueryDelete extends AbstractQueryDelete {
    /**
     * Конструктор класса `MySQLQueryDelete`.
     * @param table Название таблицы, из которой необходимо удалить данные.
     */
    public MySQLQueryDelete(
            @NotNull String table
    ) {
        super(table);
    }

    /**
     * Формирует SQL запрос для удаления данных из таблицы.
     * @return SQL запрос для удаления данных.
     */
    @Override
    public String toString() {
        Pair<String, ArrayList<Object>> convertEntries = where.getConvertEntries();
        preparedObjects = convertEntries.getSecond();
        return "DELETE FROM `" + table + "` WHERE " + convertEntries.getFirst()
                + (limitSize != 0 ? " LIMIT " + limitSize : "") + ";";
    }
}
