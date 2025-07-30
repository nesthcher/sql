package ru.nesthcher.sql.implementation.table.constructor;

import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;

import ru.nesthcher.sql.interfaces.AbstractDatabase;
import ru.nesthcher.sql.interfaces.table.constructor.BaseTableConstructor;
import ru.nesthcher.sql.interfaces.table.constructor.column.AbstractTableColumn;
import ru.nesthcher.sql.interfaces.table.constructor.column.ColumnType;
import ru.nesthcher.sql.implementation.table.constructor.column.MySQLTableColumn;

/**
 * Класс `MySQLTableConstructor` реализует конструктор таблиц для MySQL базы данных.
 * Позволяет создавать таблицы с заданными колонками, первичными ключами и индексами.
 */
public final class MySQLTableConstructor extends BaseTableConstructor {
    /**
     * Конструктор класса `MySQLTableConstructor`.
     * @param database Абстрактная база данных, с которой будет работать конструктор.
     * @param name Название создаваемой таблицы.
     */
    public MySQLTableConstructor(
            @NotNull AbstractDatabase database,
            @NotNull String name
    ) {
        super(database, name);
    }

    /**
     * Создает новую колонку таблицы.
     * @param name Название колонки.
     * @param columnType Тип колонки.
     * @return Объект `MySQLTableColumn`, представляющий колонку таблицы.
     */
    @Override
    public AbstractTableColumn newColumn(
            @NotNull String name,
            @NotNull ColumnType columnType
    ) {
        return new MySQLTableColumn(name, columnType);
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
        String primary = tableColumns.values().stream()
                .filter(AbstractTableColumn::isPrimaryKey)
                .map(AbstractTableColumn::getName)
                .collect(Collectors.joining(", "));

        if (!primary.isEmpty()) {
            columnSql = columnSql + ", PRIMARY KEY (" + primary + ")";
        }

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
