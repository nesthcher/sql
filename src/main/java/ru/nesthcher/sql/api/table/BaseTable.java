package ru.nesthcher.sql.api.table;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.nesthcher.sql.api.AbstractDatabase;
import ru.nesthcher.sql.api.table.constructor.AbstractTableConstructor;

@Getter
public abstract class BaseTable implements AbstractTable {
    protected final AbstractDatabase database;

    protected BaseTable(
            @NotNull final AbstractDatabase database
    ) {
        this.database = database;
    }

    public abstract AbstractTableConstructor constructor(
            @NotNull final String name
    );
}
