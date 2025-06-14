package ru.nesthcher.sql.api.query.constructor.insert;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.nesthcher.sql.api.query.constructor.Query;
import ru.nesthcher.sql.util.Pair;

import java.util.LinkedHashMap;
import java.util.List;

@Getter
public abstract class AbstractQueryInsert implements Query {
    protected final String table;
    protected final LinkedHashMap<String, Pair<Object, Object>> entries = new LinkedHashMap<>();
    protected List<Object> preparedObjects = null;

    public AbstractQueryInsert(
            @NotNull final String table
    ) {
        this.table = table;
    }

    public AbstractQueryInsert set(
            @NotNull final String column,
            final Object value
    ) {
        entries.put(column, new Pair<>(value, null));
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
