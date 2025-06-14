package ru.nesthcher.sql.api;

import org.jetbrains.annotations.NotNull;
import ru.nesthcher.sql.api.query.AbstractQuery;
import ru.nesthcher.sql.api.query.constructor.Query;
import ru.nesthcher.sql.api.table.AbstractTable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public interface AbstractDatabase {
    ExecutorService QUERY_EXECUTOR = Executors.newCachedThreadPool();

    int executeTestQuery();
    int execute(@NotNull final String query, final Object... objects);
    int execute(@NotNull final Query query);
    int execute(@NotNull final StatementWrapper wrapper, final Object... objects);
    <T> T executeQuery(@NotNull final String query, final ResponseHandler<ResultSet, T> handler, final Object... objects);
    <T> T executeQuery(@NotNull final Query Query, final ResponseHandler<ResultSet, T> handler);
    <T> T executeQuery(@NotNull final StatementWrapper wrapper, final ResponseHandler<ResultSet, T> handler, final Object... objects);
    void close();
    Connection getConnection();
    boolean isConnected();

    AbstractTable table();
    AbstractQuery query();
}
