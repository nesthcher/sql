package ru.nesthcher.sql.implementation.query.constructor.update;

import org.jetbrains.annotations.NotNull;
import ru.nesthcher.sql.api.query.QueryUtil;
import ru.nesthcher.sql.api.query.constructor.update.AbstractQueryUpdate;
import ru.nesthcher.sql.util.Pair;

import java.util.List;

public final class MySQLQueryUpdate extends AbstractQueryUpdate {
    public MySQLQueryUpdate(
            @NotNull final String table
    ) {
        super(table);
    }

    @Override
    public String toString() {
        final Pair<String, List<Object>> whereConvertEntries = where.getConvertEntries();
        final Pair<String, List<Object>> setConvertEntries = QueryUtil.convertEntries(entries, false);
        preparedObjects = setConvertEntries.getSecond();
        preparedObjects.addAll(whereConvertEntries.getSecond());

        return "UPDATE `" + table + "` SET " + setConvertEntries.getFirst() + " WHERE "
                + whereConvertEntries.getFirst() + (limitSize != 0 ? " LIMIT " + limitSize : "") + ";";
    }

}
