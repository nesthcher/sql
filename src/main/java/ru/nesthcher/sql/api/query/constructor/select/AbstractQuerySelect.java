package ru.nesthcher.sql.api.query.constructor.select;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.nesthcher.sql.api.query.QuerySymbol;
import ru.nesthcher.sql.api.query.constructor.Query;
import ru.nesthcher.sql.api.query.constructor.where.AbstractQueryWhere;
import ru.nesthcher.sql.api.query.constructor.where.QueryWhere;

import java.util.List;

@Getter
public abstract class AbstractQuerySelect implements Query {
    protected final String table;
    protected final AbstractQueryWhere where = new QueryWhere();
    protected List<Object> preparedObjects = null;
    protected String result;
    protected int limitSize = 0;

    public AbstractQuerySelect(
            @NotNull final String table
    ) {
        this.table = table;
    }

    public AbstractQuerySelect where(
            @NotNull final String column,
            @NotNull final QuerySymbol symbol,
            final Object result
    ) {
        where.put(column, symbol, result);
        return this;
    }

    public AbstractQuerySelect limit() {
        return limit(1);
    }

    public AbstractQuerySelect limit(
            final int limit
    ) {
        if (limit < 0) return this;
        this.limitSize = limit;
        return this;
    }

    public AbstractQuerySelect result(
            @NotNull final String result
    ) {
        if (!result.equals("*")) this.result = result;
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
