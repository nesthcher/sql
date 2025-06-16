package ru.nesthcher.sql.implementation.table;

import org.jetbrains.annotations.NotNull;

import ru.nesthcher.sql.api.AbstractDatabase;
import ru.nesthcher.sql.api.table.BaseTable;
import ru.nesthcher.sql.api.table.constructor.AbstractTableConstructor;
import ru.nesthcher.sql.implementation.table.constructor.MySQLTableConstructor;

/**
 * Класс `MySQLTable` реализует интерфейс `BaseTable` для работы с таблицами в MySQL базе данных.
 */
public final class MySQLTable extends BaseTable {
    /**
     * Конструктор класса `MySQLTable`.
     * @param database Абстрактная база данных, с которой будет работать таблица.
     */
    public MySQLTable(
            @NotNull final AbstractDatabase database
    ) {
        super(database);
    }

    /**
     * Создает конструктор таблицы `MySQLTableConstructor`.
     * @param tableName Название таблицы.
     * @return Конструктор таблицы `MySQLTableConstructor`.
     */
    @Override
    public AbstractTableConstructor constructor(
            @NotNull final String tableName
    ) {
        return new MySQLTableConstructor(database, tableName);
    }

    /**
     * Удаляет таблицу из базы данных, если она существует.
     * @param tableName Название таблицы, которую нужно удалить.
     */
    @Override
    public void delete(
            @NotNull final String tableName
    ) {
        database.execute("DROP TABLE IF EXISTS " + tableName + ";");
    }
}
