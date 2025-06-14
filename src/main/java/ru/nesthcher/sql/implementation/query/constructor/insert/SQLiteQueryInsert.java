package ru.nesthcher.sql.implementation.query.constructor.insert;

import org.jetbrains.annotations.NotNull;
import ru.nesthcher.sql.api.query.QueryUtil;
import ru.nesthcher.sql.api.query.constructor.insert.AbstractQueryInsert;
import ru.nesthcher.sql.util.Pair;

import java.util.List;
import java.util.stream.Collectors;

public final class SQLiteQueryInsert extends AbstractQueryInsert {
    public SQLiteQueryInsert(
            @NotNull final String table
    ) {
        super(table);
    }

    @Override
    public String toString() {
        final String columns = entries.keySet().stream().map(s -> "`" + s + "`")
                .collect(Collectors.joining(", "));
        final String values = entries.keySet().stream().map(s -> "?")
                .collect(Collectors.joining(", "));

        final Pair<String, List<Object>> convertEntries = QueryUtil.convertEntries(entries, false);
        preparedObjects = convertEntries.getSecond();

        return "INSERT INTO `" + table + "` (" + columns + ") VALUES (" + values + ");";
    }
}
