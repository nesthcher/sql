package ru.nesthcher.sql.implementation.query.constructor.select;

import org.jetbrains.annotations.NotNull;
import ru.nesthcher.sql.api.query.constructor.select.AbstractQuerySelect;
import ru.nesthcher.sql.util.Pair;

import java.util.List;

public final class MySQLQuerySelect extends AbstractQuerySelect {
    public MySQLQuerySelect(
            @NotNull final String table
    ) {
        super(table);
    }

    @Override
    public String toString() {
        final Pair<String, List<Object>> convertEntries = where.getConvertEntries();
        preparedObjects = convertEntries.getSecond();
        return "SELECT " + (result == null ? "*" : "`" + result + "`") + " FROM `" + table
                + "` WHERE " + convertEntries.getFirst() + (limitSize != 0 ? " LIMIT " + limitSize : "") + ";";
    }
}
