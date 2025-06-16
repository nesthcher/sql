package ru.nesthcher.sql.api.query.constructor.where;

import java.util.LinkedHashMap;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import lombok.Getter;
import ru.nesthcher.sql.api.query.QuerySymbol;
import ru.nesthcher.sql.api.query.QueryUtil;
import ru.nesthcher.sql.util.Pair;

@Getter
public class QueryWhere implements AbstractQueryWhere {
    private final LinkedHashMap<String, Pair<Object, Object>> entries = new LinkedHashMap<>();

    @Override
    public void put(
            @NotNull final String column,
            @NotNull final QuerySymbol symbol,
            final Object result
    ) {
        if (column.isEmpty()) throw new IllegalArgumentException("Название колонки не может быть пустым");
        entries.put(column, new Pair<>(result, symbol));
    }

    @Override
    public Pair<String, List<Object>> getConvertEntries() {
        return QueryUtil.convertEntries(entries, true);
    }
}
