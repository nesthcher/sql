package ru.nesthcher.sql.implementation.query.constructor.delete;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import ru.nesthcher.sql.api.query.constructor.delete.AbstractQueryDelete;
import ru.nesthcher.utils.container.Pair;

/**
 * Класс `SQLiteQueryDelete` представляет собой конструктор SQL запроса для удаления данных из таблицы SQLite.
 * Расширяет `AbstractQueryDelete` и предоставляет реализацию метода `toString` для формирования SQL запроса.
 */
public final class SQLiteQueryDelete extends AbstractQueryDelete {
    /**
     * Конструктор класса `SQLiteQueryDelete`.
     * @param table Название таблицы, из которой необходимо удалить данные.
     */
    public SQLiteQueryDelete(
            @NotNull final String table
    ) {
        super(table);
    }

    /**
     * Формирует SQL запрос для удаления данных из таблицы.
     * @return SQL запрос для удаления данных.
     */
    @Override
    public String toString() {
        final Pair<String, List<Object>> convertEntries = where.getConvertEntries();
        preparedObjects = convertEntries.getSecond();
        return "DELETE FROM `" + table + "` WHERE " + convertEntries.getFirst()
                + (limitSize != 0 ? " LIMIT " + limitSize : "") + ";";
    }
}
