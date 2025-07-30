package ru.nesthcher.sql.implementations.table.constructor.column;

import org.jetbrains.annotations.NotNull;

import ru.nesthcher.sql.interfaces.table.constructor.column.BaseTableColumn;
import ru.nesthcher.sql.interfaces.table.constructor.column.ColumnType;

/**
 * Класс `MySQLTableColumn` представляет колонку таблицы для MySQL базы данных.
 * Расширяет `BaseTableColumn` и предоставляет специфичную для MySQL реализацию метода `toString`.
 */
public final class MySQLTableColumn extends BaseTableColumn {
    /**
     * Конструктор класса `MySQLTableColumn`.
     * @param name Название колонки.
     * @param columnType Тип колонки.
     */
    public MySQLTableColumn(
            @NotNull String name,
            @NotNull ColumnType columnType
    ) {
        super(name, columnType);
    }

    /**
     * Преобразует колонку таблицы в SQL строку для создания колонки в MySQL.
     * @return SQL строка, представляющая колонку таблицы.
     */
    @Override
    public @NotNull String toString() {
        return "`" + this.name + "` " + this.columnType.getSql() + (this.nullValue ? "" : " NOT NULL")
                + (!this.unique ? "" : " UNIQUE") + (this.defaultValue == null ? "" : " DEFAULT "
                + this.getDefaultValueString()) + (!this.autoIncrement ? "" : " AUTO_INCREMENT");
    }
}
