package ru.nesthcher.sql.implementation.query;

import org.jetbrains.annotations.NotNull;

import ru.nesthcher.sql.api.AbstractDatabase;
import ru.nesthcher.sql.api.query.BaseQuery;
import ru.nesthcher.sql.api.query.constructor.delete.AbstractQueryDelete;
import ru.nesthcher.sql.api.query.constructor.insert.AbstractQueryInsert;
import ru.nesthcher.sql.api.query.constructor.select.AbstractQuerySelect;
import ru.nesthcher.sql.api.query.constructor.update.AbstractQueryUpdate;
import ru.nesthcher.sql.implementation.query.constructor.delete.MySQLQueryDelete;
import ru.nesthcher.sql.implementation.query.constructor.insert.MySQLQueryInsert;
import ru.nesthcher.sql.implementation.query.constructor.select.MySQLQuerySelect;
import ru.nesthcher.sql.implementation.query.constructor.update.MySQLQueryUpdate;

/**
 * Класс `MySQLQuery` предоставляет реализацию интерфейса `BaseQuery` для выполнения SQL запросов в базе данных MySQL.
 */
public final class MySQLQuery extends BaseQuery {

    /**
     * Конструктор класса `MySQLQuery`.
     * @param database Абстрактная база данных, с которой будут выполняться запросы.
     */
    public MySQLQuery(
            @NotNull AbstractDatabase database
    ) {
        super(database);
    }

    /**
     * Создает конструктор запроса на удаление данных из таблицы.
     * @param table Название таблицы, из которой будут удалены данные.
     * @return Конструктор запроса на удаление `MySQLQueryDelete`.
     */
    @Override
    public AbstractQueryDelete delete(
            @NotNull String table
    ) {
        return new MySQLQueryDelete(table);
    }

    /**
     * Создает конструктор запроса на вставку данных в таблицу.
     * @param table Название таблицы, в которую будут вставлены данные.
     * @return Конструктор запроса на вставку `MySQLQueryInsert`.
     */
    @Override
    public AbstractQueryInsert insert(
            @NotNull String table
    ) {
        return new MySQLQueryInsert(table);
    }

    /**
     * Создает конструктор запроса на выборку данных из таблицы.
     * @param table Название таблицы, из которой будут выбраны данные.
     * @return Конструктор запроса на выборку `MySQLQuerySelect`.
     */
    @Override
    public AbstractQuerySelect select(
            @NotNull String table
    ) {
        return new MySQLQuerySelect(table);
    }

    /**
     * Создает конструктор запроса на обновление данных в таблице.
     * @param table Название таблицы, в которой будут обновлены данные.
     * @return Конструктор запроса на обновление `MySQLQueryUpdate`.
     */
    @Override
    public AbstractQueryUpdate update(
            @NotNull String table
    ) {
        return new MySQLQueryUpdate(table);
    }
}
