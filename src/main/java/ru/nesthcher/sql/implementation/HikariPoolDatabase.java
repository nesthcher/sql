package ru.nesthcher.sql.implementation;

import com.zaxxer.hikari.HikariDataSource;
import org.jetbrains.annotations.NotNull;
import ru.nesthcher.sql.api.AbstractDatabase;
import ru.nesthcher.sql.api.ResponseHandler;
import ru.nesthcher.sql.api.StatementWrapper;
import ru.nesthcher.sql.api.query.AbstractQuery;
import ru.nesthcher.sql.api.query.constructor.Query;
import ru.nesthcher.sql.api.table.AbstractTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class HikariPoolDatabase implements AbstractDatabase {
    protected HikariDataSource dataSource;
    protected Connection connection = null;
    protected AbstractTable table;
    protected AbstractQuery query;

    protected void onConnect() {
        this.dataSource = configureDataSource(new HikariDataSource());
    }

    protected abstract @NotNull HikariDataSource configureDataSource(
            @NotNull final HikariDataSource source
    );

    @Override
    public int executeTestQuery() {
        return execute("SELECT 1 + 1;");
    }

    @Override
    public int execute(
            @NotNull final String query,
            final Object... objects
    ) {
        return execute(StatementWrapper.create(this, query), objects);
    }

    @Override
    public int execute(
            @NotNull final Query query
    ) {
        return execute(StatementWrapper.create(this, query.toString()), query.getPreparedObjects());
    }

    @Override
    public int execute(
            @NotNull final StatementWrapper wrapper,
            final Object... objects
    ) {
        return wrapper.execute(PreparedStatement.RETURN_GENERATED_KEYS, objects);
    }

    @Override
    public <T> T executeQuery(
            @NotNull final String query,
            final ResponseHandler<ResultSet, T> handler,
            Object... objects
    ) {
        return executeQuery(StatementWrapper.create(this, query), handler, objects);
    }

    @Override
    public <T> T executeQuery(
            @NotNull final Query query,
            final ResponseHandler<ResultSet, T> handler
    ) {
        return executeQuery(StatementWrapper.create(this, query.toString()), handler, query.getPreparedObjects());
    }

    @Override
    public <T> T executeQuery(
            @NotNull final StatementWrapper wrapper,
            final ResponseHandler<ResultSet, T> handler,
            final Object... objects
    ) {
        return wrapper.executeQuery(handler, objects);
    }

    @Override
    public Connection getConnection() {
        refreshConnection();
        return connection;
    }

    public boolean isLocalConnected() {
        try {
            return connection != null && !connection.isClosed() && connection.isValid(500);
        } catch (SQLException ignore) {
        }
        return false;
    }

    @Override
    public boolean isConnected() {
        refreshConnection();
        return isLocalConnected();
    }

    protected void refreshConnection() {
        try {
            if (isLocalConnected()) return;
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            onConnect();
        }
    }

    @Override
    public void close() {
        try {
            this.connection.close();
        } catch (SQLException ignore) {
        }
    }

    @Override
    public abstract AbstractTable table();

    @Override
    public abstract AbstractQuery query();
}