package ru.nesthcher.sql.api.query.constructor.where;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.nesthcher.sql.api.query.QuerySymbol;
import ru.nesthcher.sql.api.query.QueryUtil;
import ru.nesthcher.sql.util.Pair;

import java.util.LinkedHashMap;
import java.util.List;

@Getter
public class QueryWhere implements AbstractQueryWhere {
    private final LinkedHashMap<String, Pair<Object, Object>> entries = new LinkedHashMap<>();

    public void put(
            @NotNull final String column,
            @NotNull final QuerySymbol symbol,
            final Object result
    ) {
        entries.put(column, new Pair<>(result, symbol));
    }

    public Pair<String, List<Object>> getConvertEntries() {
        return QueryUtil.convertEntries(entries, true);
    }
}
