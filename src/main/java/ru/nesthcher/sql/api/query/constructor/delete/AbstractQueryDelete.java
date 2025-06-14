package ru.nesthcher.sql.api.query.constructor.delete;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.nesthcher.sql.api.query.QuerySymbol;
import ru.nesthcher.sql.api.query.constructor.Query;
import ru.nesthcher.sql.api.query.constructor.where.AbstractQueryWhere;
import ru.nesthcher.sql.api.query.constructor.where.QueryWhere;

import java.util.List;

@Getter
public abstract class AbstractQueryDelete implements Query {
    protected final String table;
    protected final AbstractQueryWhere where = new QueryWhere();
    protected List<Object> preparedObjects = null;
    protected int limitSize = 0;

    public AbstractQueryDelete(
            @NotNull final String table
    ) {
        this.table = table;
    }

    public AbstractQueryDelete where(
            @NotNull final String column,
            @NotNull final QuerySymbol symbol,
            @NotNull final Object result
    ) {
        where.put(column, symbol, result);
        return this;
    }

    public AbstractQueryDelete limit() {
        return limit(1);
    }

    public AbstractQueryDelete limit(
            final int limit
    ) {
        if (limit < 0) return this;
        this.limitSize = limit;
        return this;
    }

    @Override
    public List<Object> getPreparedObjects() {
        if (preparedObjects == null) throw new NullPointerException("не указаны параметры preparedObjects");
        return preparedObjects;
    }

    @Override
    public abstract String toString();
}
