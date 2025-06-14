package ru.nesthcher.sql.implementation.query;

import org.jetbrains.annotations.NotNull;
import ru.nesthcher.sql.api.AbstractDatabase;
import ru.nesthcher.sql.api.query.BaseQuery;
import ru.nesthcher.sql.api.query.constructor.delete.AbstractQueryDelete;
import ru.nesthcher.sql.implementation.query.constructor.delete.MySQLQueryDelete;
import ru.nesthcher.sql.api.query.constructor.insert.AbstractQueryInsert;
import ru.nesthcher.sql.implementation.query.constructor.insert.MySQLQueryInsert;
import ru.nesthcher.sql.api.query.constructor.select.AbstractQuerySelect;
import ru.nesthcher.sql.implementation.query.constructor.select.MySQLQuerySelect;
import ru.nesthcher.sql.api.query.constructor.update.AbstractQueryUpdate;
import ru.nesthcher.sql.implementation.query.constructor.update.MySQLQueryUpdate;

public final class MySQLQuery extends BaseQuery {

    public MySQLQuery(
            @NotNull final AbstractDatabase database
    ) {
        super(database);
    }

    @Override
    public AbstractQueryDelete delete(
            @NotNull final String table
    ) {
        return new MySQLQueryDelete(table);
    }

    @Override
    public AbstractQueryInsert insert(
            @NotNull final String table
    ) {
        return new MySQLQueryInsert(table);
    }

    @Override
    public AbstractQuerySelect select(
            @NotNull final String table
    ) {
        return new MySQLQuerySelect(table);
    }

    @Override
    public AbstractQueryUpdate update(
            @NotNull final String table
    ) {
        return new MySQLQueryUpdate(table);
    }
}
