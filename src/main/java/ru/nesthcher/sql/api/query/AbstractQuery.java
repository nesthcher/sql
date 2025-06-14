package ru.nesthcher.sql.api.query;

import org.jetbrains.annotations.NotNull;
import ru.nesthcher.sql.api.AbstractDatabase;
import ru.nesthcher.sql.api.query.constructor.delete.AbstractQueryDelete;
import ru.nesthcher.sql.api.query.constructor.insert.AbstractQueryInsert;
import ru.nesthcher.sql.api.query.constructor.select.AbstractQuerySelect;
import ru.nesthcher.sql.api.query.constructor.update.AbstractQueryUpdate;

public interface AbstractQuery {
    AbstractDatabase getDatabase();
    AbstractQueryDelete delete(@NotNull final String table);
    AbstractQueryInsert insert(@NotNull final String table);
    AbstractQuerySelect select(@NotNull final String table);
    AbstractQueryUpdate update(@NotNull final String table);
}
