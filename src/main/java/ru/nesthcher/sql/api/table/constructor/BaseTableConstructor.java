package ru.nesthcher.sql.api.table.constructor;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.nesthcher.sql.api.AbstractDatabase;
import ru.nesthcher.sql.api.table.constructor.column.AbstractTableColumn;
import ru.nesthcher.sql.api.table.constructor.column.ColumnType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class BaseTableConstructor implements AbstractTableConstructor {
    @Getter
    protected final AbstractDatabase database;
    @Getter
    protected final String name;
    protected final HashMap<String, AbstractTableColumn> tableColumns = new HashMap<>();
    protected final List<String> columns = new ArrayList<>();

    public BaseTableConstructor(
            @NotNull final AbstractDatabase database,
            @NotNull final String name
    ) {
        this.database = database;
        this.name = name;
    }

    @Override
    public abstract AbstractTableColumn newColumn(
            @NotNull final String name,
            @NotNull final ColumnType columnType
    );

    @Override
    public void addColumn(
            @NotNull final AbstractTableColumn column
    ) {
        tableColumns.put(column.getName(), column);
    }

    @Override
    public void removeColumn(@NotNull final String name) {
        tableColumns.remove(name);
    }

    @Override
    public final void addIndex(
            @NotNull final String column
    ) { //todo проверить
        this.columns.add(column);
    }

    @Override
    public abstract @NotNull String toString();

    @Override
    public abstract void create();
}
