package ru.nesthcher.sql.implementation.table;

import org.jetbrains.annotations.NotNull;
import ru.nesthcher.sql.api.AbstractDatabase;
import ru.nesthcher.sql.api.table.BaseTable;
import ru.nesthcher.sql.api.table.constructor.AbstractTableConstructor;
import ru.nesthcher.sql.implementation.table.constructor.SQLiteTableConstructor;

public final class SQLiteTable extends BaseTable {
    public SQLiteTable(
            @NotNull final AbstractDatabase database
    ) {
        super(database);
    }

    @Override
    public AbstractTableConstructor constructor(
            @NotNull final String tableName
    ) {
        return new SQLiteTableConstructor(database, tableName);
    }

    @Override
    public void delete(
            @NotNull final String tableName
    ) {
        database.execute("DROP TABLE IF EXISTS " + tableName + ";");
    }
}
