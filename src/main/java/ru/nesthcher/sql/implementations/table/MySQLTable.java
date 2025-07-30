package ru.nesthcher.sql.implementations.table;

import org.jetbrains.annotations.NotNull;

import ru.nesthcher.sql.interfaces.AbstractDatabase;
import ru.nesthcher.sql.interfaces.table.BaseTable;
import ru.nesthcher.sql.interfaces.table.constructor.AbstractTableConstructor;
import ru.nesthcher.sql.implementations.table.constructor.MySQLTableConstructor;

/**
 * Класс `MySQLTable` реализует интерфейс `BaseTable` для работы с таблицами в MySQL базе данных.
 */
public final class MySQLTable extends BaseTable {
    /**
     * Конструктор класса `MySQLTable`.
     * @param database Абстрактная база данных, с которой будет работать таблица.
     */
    public MySQLTable(
            @NotNull AbstractDatabase database
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
            @NotNull String tableName
    ) {
        return new MySQLTableConstructor(database, tableName);
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
