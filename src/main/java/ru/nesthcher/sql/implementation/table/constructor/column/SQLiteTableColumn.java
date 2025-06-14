package ru.nesthcher.sql.implementation.table.constructor.column;

import org.jetbrains.annotations.NotNull;
import ru.nesthcher.sql.api.table.constructor.column.BaseTableColumn;
import ru.nesthcher.sql.api.table.constructor.column.ColumnType;

public final class SQLiteTableColumn extends BaseTableColumn {
    public SQLiteTableColumn(
            @NotNull final String name,
            @NotNull final ColumnType columnType
    ) {
        super(name, columnType);
    }

    @Override
    public @NotNull String toString() {
        return "`" + this.name + "` " + this.columnType.getSql() + (this.nullValue || this.autoIncrement ? "" : " NOT NULL") + (!this.primaryKey ? "" : " PRIMARY KEY")
                + (!this.unigue ? "" : " UNIQUE") + (this.defaultValue == null ? "" : " DEFAULT "
                + this.getDefaultValueString()) + (!this.autoIncrement ? "" : " AUTOINCREMENT");
    }
}
