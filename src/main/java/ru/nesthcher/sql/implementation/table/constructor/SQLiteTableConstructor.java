package ru.nesthcher.sql.implementation.table.constructor;

import org.jetbrains.annotations.NotNull;
import ru.nesthcher.sql.api.AbstractDatabase;
import ru.nesthcher.sql.api.table.constructor.BaseTableConstructor;
import ru.nesthcher.sql.api.table.constructor.column.AbstractTableColumn;
import ru.nesthcher.sql.api.table.constructor.column.ColumnType;
import ru.nesthcher.sql.implementation.table.constructor.column.SQLiteTableColumn;

import java.util.stream.Collectors;

public final class SQLiteTableConstructor extends BaseTableConstructor {
    public SQLiteTableConstructor(
            @NotNull final AbstractDatabase database,
            @NotNull final String name
    ) {
        super(database, name);
    }

    @Override
    public AbstractTableColumn newColumn(
            @NotNull final String name,
            @NotNull final ColumnType columnType
    ) {
        return new SQLiteTableColumn(name, columnType);
    }

    @Override
    public @NotNull String toString() {
        String columnSql = tableColumns.values().stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));

        return "CREATE TABLE IF NOT EXISTS `" + name + "` (" + columnSql + ");";
    }

    @Override
    public void create() {
        database.execute(this.toString());

        for (final String columnName : columns) {
            database.execute("ALTER TABLE `" + name + "` ADD INDEX (`" + columnName + "`);");
        }
    }
}
