package ru.nesthcher.sql.implementation.query.constructor.delete;

import org.jetbrains.annotations.NotNull;
import ru.nesthcher.sql.api.query.constructor.delete.AbstractQueryDelete;
import ru.nesthcher.sql.util.Pair;

import java.util.List;

public final class MySQLQueryDelete extends AbstractQueryDelete {
    public MySQLQueryDelete(
            @NotNull final String table
    ) {
        super(table);
    }

    @Override
    public String toString() {
        final Pair<String, List<Object>> convertEntries = where.getConvertEntries();
        preparedObjects = convertEntries.getSecond();
        return "DELETE FROM `" + table + "` WHERE " + convertEntries.getFirst()
                + (limitSize != 0 ? " LIMIT " + limitSize : "") + ";";
    }
}
