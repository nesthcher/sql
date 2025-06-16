package ru.nesthcher.sql.api.table.constructor;

import org.jetbrains.annotations.NotNull;

import ru.nesthcher.sql.api.table.constructor.column.AbstractTableColumn;
import ru.nesthcher.sql.api.table.constructor.column.ColumnType;

public interface AbstractTableConstructor {
    AbstractTableColumn newColumn(@NotNull final String name, @NotNull final ColumnType columnType);
    void addColumn(@NotNull final AbstractTableColumn column);
    void removeColumn(@NotNull final String name);
    void addIndex(@NotNull final String column);
    @Override @NotNull String toString();
    void create();
}
