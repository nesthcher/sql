package ru.nesthcher.sql.api.table.constructor.column;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

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

    public BaseTableColumn setNull(
            final boolean nullValue
    ) {
        this.nullValue = nullValue;
        return this;
    }

    public BaseTableColumn setDefaultValue(
            final Object defaultValue
    ) {
        this.defaultValue = defaultValue;
        return this;
    }

    public BaseTableColumn primaryKey(
            final boolean primaryKey
    ) {
        this.primaryKey = primaryKey;
        return this;
    }

    public BaseTableColumn unigue(
            final boolean unigue
    ) {
        this.unigue = unigue;
        return this;
    }

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
