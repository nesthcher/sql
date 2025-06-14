package ru.nesthcher.sql.implementation;

import com.zaxxer.hikari.HikariDataSource;
import org.jetbrains.annotations.NotNull;
import ru.nesthcher.sql.api.query.AbstractQuery;
import ru.nesthcher.sql.implementation.query.SQLiteQuery;
import ru.nesthcher.sql.api.table.AbstractTable;
import ru.nesthcher.sql.implementation.table.SQLiteTable;

public final class SQLiteDatabase extends HikariPoolDatabase {
    private final String path;

    public SQLiteDatabase(
            @NotNull final String path
	) {
        this.path = path;
        onConnect();
    }

    @Override
    protected @NotNull HikariDataSource configureDataSource(
            @NotNull final HikariDataSource source
    ) {
        source.setDriverClassName("org.sqlite.JDBC");
        source.setJdbcUrl("jdbc:sqlite:" + path);
        source.setMaximumPoolSize(1);
//        source.setMinimumIdle(86400000);
//        source.setMaxLifetime(86400000);
        source.addDataSourceProperty("cachePrepStmts", "true");
        source.addDataSourceProperty("prepStmtCacheSize", "250");
        source.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return source;
    }

    @Override
    public AbstractTable table() {
        if(table == null) table = new SQLiteTable(this);
        return table;
    }

    @Override
    public AbstractQuery query() {
        if(query == null) query = new SQLiteQuery(this);
        return query;
    }
}
