package ru.nesthcher.sql.api.query;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.nesthcher.sql.api.AbstractDatabase;
import ru.nesthcher.sql.api.query.constructor.delete.AbstractQueryDelete;
import ru.nesthcher.sql.api.query.constructor.insert.AbstractQueryInsert;
import ru.nesthcher.sql.api.query.constructor.select.AbstractQuerySelect;
import ru.nesthcher.sql.api.query.constructor.update.AbstractQueryUpdate;

@Getter
public abstract class BaseQuery implements AbstractQuery {
    protected final AbstractDatabase database;

    protected BaseQuery(
            @NotNull final AbstractDatabase database
    ) {
        this.database = database;
    }

    @Override
    public abstract AbstractQueryDelete delete(
            @NotNull final String table
    );

    @Override
    public abstract AbstractQueryInsert insert(
            @NotNull final String table
    );

    @Override
    public abstract AbstractQuerySelect select(
            @NotNull final String table
    );

    @Override
    public abstract AbstractQueryUpdate update(
            @NotNull final String table
    );
}
