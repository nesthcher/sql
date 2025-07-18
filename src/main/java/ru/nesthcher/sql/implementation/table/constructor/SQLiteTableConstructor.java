package ru.nesthcher.sql.implementation.table.constructor;

import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;

import ru.nesthcher.sql.api.AbstractDatabase;
import ru.nesthcher.sql.api.table.constructor.BaseTableConstructor;
import ru.nesthcher.sql.api.table.constructor.column.AbstractTableColumn;
import ru.nesthcher.sql.api.table.constructor.column.ColumnType;
import ru.nesthcher.sql.implementation.table.constructor.column.SQLiteTableColumn;

/**
 * Класс `SQLiteTableConstructor` реализует конструктор таблиц для SQLite базы данных.
 * Позволяет создавать таблицы с заданными колонками и индексами.
 */
public final class SQLiteTableConstructor extends BaseTableConstructor {
    /**
     * Конструктор класса `SQLiteTableConstructor`.
     * @param database Абстрактная база данных, с которой будет работать конструктор.
     * @param name Название создаваемой таблицы.
     */
    public SQLiteTableConstructor(
            @NotNull AbstractDatabase database,
            @NotNull String name
    ) {
        super(database, name);
    }

    /**
     * Создает новую колонку таблицы.
     * @param name Название колонки.
     * @param columnType Тип колонки.
     * @return Объект `SQLiteTableColumn`, представляющий колонку таблицы.
     */
    @Override
    public AbstractTableColumn newColumn(
            @NotNull String name,
            @NotNull ColumnType columnType
    ) {
        return new SQLiteTableColumn(name, columnType);
    }

    /**
     * Преобразует конструктор таблицы в SQL запрос для создания таблицы.
     * @return SQL запрос для создания таблицы.
     */
    @Override
    public @NotNull String toString() {
        String columnSql = tableColumns.values().stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));

        return "CREATE TABLE IF NOT EXISTS `" + name + "` (" + columnSql + ");";
    }

    /**
     * Создает таблицу в базе данных.
     * Выполняет SQL запрос для создания таблицы и добавления индексов.
     */
    @Override
    public void create() {
        database.execute(this.toString());

        for (String columnName : columns) {
            database.execute("ALTER TABLE `" + name + "` ADD INDEX (`" + columnName + "`);");
        }
    }
}
