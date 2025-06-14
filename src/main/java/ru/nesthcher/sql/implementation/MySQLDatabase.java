package ru.nesthcher.sql.implementation;

import com.zaxxer.hikari.HikariDataSource;
import org.jetbrains.annotations.NotNull;
import ru.nesthcher.sql.api.query.AbstractQuery;
import ru.nesthcher.sql.implementation.query.MySQLQuery;
import ru.nesthcher.sql.api.table.AbstractTable;
import ru.nesthcher.sql.implementation.table.MySQLTable;

public final class MySQLDatabase extends HikariPoolDatabase {
    private final String host, password, user, data;
    private final int port;

    public MySQLDatabase(
            @NotNull final String host,
            @NotNull final String user,
            @NotNull final String password,
            @NotNull final String data,
            final int port
	) {
        this.host = host;
        this.password = password;
        this.user = user;
        this.data = data;
        this.port = port;
        onConnect();
    }

    @Override
    protected @NotNull HikariDataSource configureDataSource(
            @NotNull final HikariDataSource source
    ) {
        source.setDriverClassName("com.mysql.cj.jdbc.Driver");
        source.setJdbcUrl("jdbc:mysql://" + host + ":" + port + "/" + data
                + "?useUnicode=true&characterEncoding=utf-8&autoReconnect=true");
        source.setUsername(user);
        source.setPassword(password);
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
        if(table == null) table = new MySQLTable(this);
        return table;
    }

    @Override
    public AbstractQuery query() {
        if(query == null) query = new MySQLQuery(this);
        return query;
    }
}
