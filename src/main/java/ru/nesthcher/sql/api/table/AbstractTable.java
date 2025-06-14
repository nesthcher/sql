package ru.nesthcher.sql.api.table;

import org.jetbrains.annotations.NotNull;
import ru.nesthcher.sql.api.AbstractDatabase;
import ru.nesthcher.sql.api.table.constructor.AbstractTableConstructor;

public interface AbstractTable {
    AbstractDatabase getDatabase();
    AbstractTableConstructor constructor(@NotNull final String tableName);
    void delete(@NotNull final String tableName);
}
