package ru.nesthcher.sql.api.query.constructor.where;

import org.jetbrains.annotations.NotNull;
import ru.nesthcher.sql.api.query.QuerySymbol;
import ru.nesthcher.sql.util.Pair;

import java.util.List;

public interface AbstractQueryWhere {
    void put(@NotNull final String column, @NotNull final QuerySymbol symbol, final Object result);

    Pair<String, List<Object>> getConvertEntries();
}
