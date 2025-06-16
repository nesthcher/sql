package ru.nesthcher.sql.implementation.query;

import org.jetbrains.annotations.NotNull;

import ru.nesthcher.sql.api.AbstractDatabase;
import ru.nesthcher.sql.api.query.BaseQuery;
import ru.nesthcher.sql.api.query.constructor.delete.AbstractQueryDelete;
import ru.nesthcher.sql.api.query.constructor.insert.AbstractQueryInsert;
import ru.nesthcher.sql.api.query.constructor.select.AbstractQuerySelect;
import ru.nesthcher.sql.api.query.constructor.update.AbstractQueryUpdate;
import ru.nesthcher.sql.implementation.query.constructor.delete.SQLiteQueryDelete;
import ru.nesthcher.sql.implementation.query.constructor.insert.SQLiteQueryInsert;
import ru.nesthcher.sql.implementation.query.constructor.select.SQLiteQuerySelect;
import ru.nesthcher.sql.implementation.query.constructor.update.SQLiteQueryUpdate;

/**
 * Класс `SQLiteQuery` предоставляет реализацию интерфейса `BaseQuery` для выполнения SQL запросов в базе данных SQLite.
 */
public final class SQLiteQuery extends BaseQuery {

    /**
     * Конструктор класса `SQLiteQuery`.
     * @param database Абстрактная база данных, с которой будут выполняться запросы.
     */
    public SQLiteQuery(
            @NotNull final AbstractDatabase database
    ) {
        super(database);
    }

    /**
     * Создает конструктор запроса на удаление данных из таблицы.
     * @param table Название таблицы, из которой будут удалены данные.
     * @return Конструктор запроса на удаление `SQLiteQueryDelete`.
     */
    @Override
    public AbstractQueryDelete delete(
            @NotNull final String table
    ) {
        return new SQLiteQueryDelete(table);
    }

    /**
     * Создает конструктор запроса на вставку данных в таблицу.
     * @param table Название таблицы, в которую будут вставлены данные.
     * @return Конструктор запроса на вставку `SQLiteQueryInsert`.
     */
    @Override
    public AbstractQueryInsert insert(
            @NotNull final String table
    ) {
        return new SQLiteQueryInsert(table);
    }

    /**
     * Создает конструктор запроса на выборку данных из таблицы.
     * @param table Название таблицы, из которой будут выбраны данные.
     * @return Конструктор запроса на выборку `SQLiteQuerySelect`.
     */
    @Override
    public AbstractQuerySelect select(
            @NotNull final String table
    ) {
        return new SQLiteQuerySelect(table);
    }

    /**
     * Создает конструктор запроса на обновление данных в таблице.
     * @param table Название таблицы, в которой будут обновлены данные.
     * @return Конструктор запроса на обновление `SQLiteQueryUpdate`.
     */
    @Override
    public AbstractQueryUpdate update(
            @NotNull final String table
    ) {
        return new SQLiteQueryUpdate(table);
    }
}
