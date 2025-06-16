package ru.nesthcher.sql.api.table;

import org.jetbrains.annotations.NotNull;

import lombok.Getter;
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

    @Override
    public abstract AbstractTableConstructor constructor(
            @NotNull final String name
    );

    @Override
    public abstract void delete(
            @NotNull final String name
    );
}
