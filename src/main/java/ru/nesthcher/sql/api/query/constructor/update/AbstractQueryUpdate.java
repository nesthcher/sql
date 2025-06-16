package ru.nesthcher.sql.api.query.constructor.update;

import java.util.LinkedHashMap;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import lombok.Getter;
import ru.nesthcher.sql.api.query.QuerySymbol;
import ru.nesthcher.sql.api.query.constructor.Query;
import ru.nesthcher.sql.api.query.constructor.where.AbstractQueryWhere;
import ru.nesthcher.sql.api.query.constructor.where.QueryWhere;
import ru.nesthcher.sql.util.Pair;

@Getter
public abstract class AbstractQueryUpdate implements Query {
    protected final String table;
    protected final LinkedHashMap<String, Pair<Object, Object>> entries = new LinkedHashMap<>();
    protected List<Object> preparedObjects = null;
    protected final AbstractQueryWhere where = new QueryWhere();
    protected int limitSize = 0;

    public AbstractQueryUpdate(
            @NotNull final String table
    ) {
        if (table.isEmpty()) throw new IllegalArgumentException("Название таблицы не может быть пустым");
        this.table = table;
    }

    public AbstractQueryUpdate set(
            @NotNull final String column,
            final Object value
    ) {
        entries.put(column, new Pair<>(value, null));
        return this;
    }

    public AbstractQueryUpdate where(
            @NotNull final String column,
            @NotNull final QuerySymbol symbol,
            final Object result
    ) {
        where.put(column, symbol, result);
        return this;
    }

    public AbstractQueryUpdate limit() {
        return limit(1);
    }

    public AbstractQueryUpdate limit(
            final int limit
    ) {
        if (limit < 0) return this;

        this.limitSize = limit;
        return this;
    }

    @Override
    public List<Object> getPreparedObjects() {
        if (preparedObjects == null)
            throw new NullPointerException("Не указаны параметры preparedpreparedObjectsEntrys");
        return preparedObjects;
    }

    @Override
    public abstract String toString();
}
