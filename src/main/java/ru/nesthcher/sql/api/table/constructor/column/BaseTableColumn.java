package ru.nesthcher.sql.api.table.constructor.column;

import org.jetbrains.annotations.NotNull;

import lombok.Getter;

@Getter
public abstract class BaseTableColumn implements AbstractTableColumn {
    protected final String name;
    protected final ColumnType columnType;

    protected boolean nullValue;
    protected boolean primaryKey;
    protected boolean unigue;
    protected boolean autoIncrement;

    protected Object defaultValue;

    public BaseTableColumn(
            @NotNull final String name,
            @NotNull final ColumnType columnType
    ) {
        this.name = name;
        this.columnType = columnType;
    }

    @Override
    public BaseTableColumn setNull(
            final boolean nullValue
    ) {
        this.nullValue = nullValue;
        return this;
    }

    @Override
    public BaseTableColumn setDefaultValue(
            final Object defaultValue
    ) {
        this.defaultValue = defaultValue;
        return this;
    }

    @Override
    public BaseTableColumn primaryKey(
            final boolean primaryKey
    ) {
        this.primaryKey = primaryKey;
        return this;
    }

    @Override
    public BaseTableColumn unigue(
            final boolean unigue
    ) {
        this.unigue = unigue;
        return this;
    }

    @Override
    public BaseTableColumn autoIncrement(
            final boolean autoIncrement
    ) {
        this.autoIncrement = autoIncrement;
        return this;
    }

    protected @NotNull String getDefaultValueString() {
        return defaultValue == null ? "" : "'" + this.defaultValue.toString() + "'";
    }

    @Override
    public abstract @NotNull String toString();
}
