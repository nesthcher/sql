package ru.nesthcher.sql.implementations.table.constructor.column;

import org.jetbrains.annotations.NotNull;

import ru.nesthcher.sql.interfaces.table.constructor.column.BaseTableColumn;
import ru.nesthcher.sql.interfaces.table.constructor.column.ColumnType;

/**
 * Класс `SQLiteTableColumn` представляет колонку таблицы для SQLite базы данных.
 * Расширяет `BaseTableColumn` и предоставляет специфичную для SQLite реализацию метода `toString`.
 */
public final class SQLiteTableColumn extends BaseTableColumn {
    /**
     * Конструктор класса `SQLiteTableColumn`.
     * @param name Название колонки.
     * @param columnType Тип колонки.
     */
    public SQLiteTableColumn(
            @NotNull String name,
            @NotNull ColumnType columnType
    ) {
        super(name, columnType);
    }

    /**
     * Преобразует колонку таблицы в SQL строку для создания колонки в SQLite.
     * @return SQL строка, представляющая колонку таблицы.
     */
    @Override
    public @NotNull String toString() {
        return "`" + this.name + "` " + this.columnType.getSql() + (this.nullValue || this.autoIncrement ? "" : " NOT NULL") + (!this.primaryKey ? "" : " PRIMARY KEY")
                + (!this.unique ? "" : " UNIQUE") + (this.defaultValue == null ? "" : " DEFAULT "
                + this.getDefaultValueString()) + (!this.autoIncrement ? "" : " AUTOINCREMENT");
    }
}
