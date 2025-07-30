package ru.nesthcher.sql.interfaces.table.constructor;

import java.util.ArrayList;
import java.util.HashMap;

import org.jetbrains.annotations.NotNull;

import lombok.Getter;
import ru.nesthcher.sql.interfaces.AbstractDatabase;
import ru.nesthcher.sql.interfaces.table.constructor.column.AbstractTableColumn;
import ru.nesthcher.sql.interfaces.table.constructor.column.ColumnType;

public abstract class BaseTableConstructor implements AbstractTableConstructor {
    @Getter
    protected final AbstractDatabase database;
    @Getter
    protected final String name;
    protected final HashMap<String, AbstractTableColumn> tableColumns = new HashMap<>();
    protected final ArrayList<String> columns = new ArrayList<>();

    public BaseTableConstructor(
            @NotNull AbstractDatabase database,
            @NotNull String name
    ) {
        this.database = database;
        this.name = name;
    }

    @Override
    public abstract AbstractTableColumn newColumn(
            @NotNull String name,
            @NotNull ColumnType columnType
    );

    @Override
    public void addColumn(
            @NotNull AbstractTableColumn column
    ) {
        tableColumns.put(column.getName(), column);
    }

    @Override
    public void removeColumn(
            @NotNull String name
    ) {
        tableColumns.remove(name);
    }

    @Override
    public final void addIndex(
            @NotNull String column
    ) {
        this.columns.add(column);
    }

    @Override
    public abstract @NotNull String toString();

    @Override
    public abstract void create();
}
