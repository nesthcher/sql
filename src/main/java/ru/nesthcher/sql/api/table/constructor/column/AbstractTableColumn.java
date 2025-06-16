package ru.nesthcher.sql.api.table.constructor.column;

import org.jetbrains.annotations.NotNull;

public interface AbstractTableColumn {
    String getName();
    ColumnType getColumnType();
    boolean isNullValue();
    AbstractTableColumn setNull(final boolean nullValue);
    Object getDefaultValue();
    AbstractTableColumn setDefaultValue(final Object defaultValue);
    boolean isPrimaryKey();
    AbstractTableColumn primaryKey(final boolean primaryKey);
    boolean isUnigue();
    AbstractTableColumn unigue(final boolean unigue);
    boolean isAutoIncrement();
    AbstractTableColumn autoIncrement(final boolean autoIncrement);
    @Override @NotNull String toString();
}
