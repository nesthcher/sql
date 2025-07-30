package ru.nesthcher.sql.implementations.table;

import org.jetbrains.annotations.NotNull;

import ru.nesthcher.sql.interfaces.AbstractDatabase;
import ru.nesthcher.sql.interfaces.table.BaseTable;
import ru.nesthcher.sql.interfaces.table.constructor.AbstractTableConstructor;
import ru.nesthcher.sql.implementations.table.constructor.SQLiteTableConstructor;

/**
 * Класс `SQLiteTable` реализует интерфейс `BaseTable` для работы с таблицами в SQLite базе данных.
 */
public final class SQLiteTable extends BaseTable {
    /**
     * Конструктор класса `SQLiteTable`.
     * @param database Абстрактная база данных, с которой будет работать таблица.
     */
    public SQLiteTable(
            @NotNull AbstractDatabase database
    ) {
        super(database);
    }

    /**
     * Создает конструктор таблицы `SQLiteTableConstructor`.
     * @param tableName Название таблицы.
     * @return Конструктор таблицы `SQLiteTableConstructor`.
     */
    @Override
    public AbstractTableConstructor constructor(
            @NotNull String tableName
    ) {
        return new SQLiteTableConstructor(database, tableName);
    }

    /**
     * Удаляет таблицу из базы данных, если она существует.
     * @param tableName Название таблицы, которую нужно удалить.
     */
    @Override
    public void delete(
            @NotNull String tableName
    ) {
        database.execute("DROP TABLE IF EXISTS " + tableName + ";");
    }
}
