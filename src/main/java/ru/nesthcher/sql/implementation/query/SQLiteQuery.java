package ru.nesthcher.sql.implementation.query;

import org.jetbrains.annotations.NotNull;
import ru.nesthcher.sql.api.AbstractDatabase;
import ru.nesthcher.sql.api.query.BaseQuery;
import ru.nesthcher.sql.api.query.constructor.delete.AbstractQueryDelete;
import ru.nesthcher.sql.implementation.query.constructor.delete.SQLiteQueryDelete;
import ru.nesthcher.sql.api.query.constructor.insert.AbstractQueryInsert;
import ru.nesthcher.sql.implementation.query.constructor.insert.SQLiteQueryInsert;
import ru.nesthcher.sql.api.query.constructor.select.AbstractQuerySelect;
import ru.nesthcher.sql.implementation.query.constructor.select.SQLiteQuerySelect;
import ru.nesthcher.sql.api.query.constructor.update.AbstractQueryUpdate;
import ru.nesthcher.sql.implementation.query.constructor.update.SQLiteQueryUpdate;

public final class SQLiteQuery extends BaseQuery {

    public SQLiteQuery(
            @NotNull final AbstractDatabase database
    ) {
        super(database);
    }

    @Override
    public AbstractQueryDelete delete(
            @NotNull final String table
    ) {
        return new SQLiteQueryDelete(table);
    }

    @Override
    public AbstractQueryInsert insert(
            @NotNull final String table
    ) {
        return new SQLiteQueryInsert(table);
    }

    @Override
    public AbstractQuerySelect select(
            @NotNull final String table
    ) {
        return new SQLiteQuerySelect(table);
    }

    @Override
    public AbstractQueryUpdate update(
            @NotNull final String table
    ) {
        return new SQLiteQueryUpdate(table);
    }
}
