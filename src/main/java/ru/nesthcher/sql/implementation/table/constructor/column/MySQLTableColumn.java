package ru.nesthcher.sql.implementation.table.constructor.column;

import org.jetbrains.annotations.NotNull;
import ru.nesthcher.sql.api.table.constructor.column.BaseTableColumn;
import ru.nesthcher.sql.api.table.constructor.column.ColumnType;

public final class MySQLTableColumn extends BaseTableColumn {
    public MySQLTableColumn(
            @NotNull final String name,
            @NotNull final ColumnType columnType
    ) {
        super(name, columnType);
    }

    @Override
    public @NotNull String toString() {
        return "`" + this.name + "` " + this.columnType.getSql() + (this.nullValue ? "" : " NOT NULL")
                + (!this.unigue ? "" : " UNIQUE") + (this.defaultValue == null ? "" : " DEFAULT "
                + this.getDefaultValueString()) + (!this.autoIncrement ? "" : " AUTO_INCREMENT");
    }
}
