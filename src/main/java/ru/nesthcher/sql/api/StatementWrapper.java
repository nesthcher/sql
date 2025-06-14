package ru.nesthcher.sql.api;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RequiredArgsConstructor
public class StatementWrapper {
    private final AbstractDatabase database;
    private String query;
    private boolean sync = false;

    public static StatementWrapper create(
            @NotNull final AbstractDatabase database,
            @NotNull final String query
    ) {
        return new StatementWrapper(database).setQuery(query);
    }

    public StatementWrapper setQuery(
            @NotNull final String query
    ) {
        this.query = query;
        return this;
    }

    public StatementWrapper sync() {
        sync = true;
        return this;
    }

    private @NotNull PreparedStatement createStatement(
            final int generatedKeys,
            final Object... objects
    ) throws SQLException {
        final PreparedStatement ps = database.getConnection().prepareStatement(query, generatedKeys);
        if (objects != null) {
            if (objects.length == 1 && objects[0] instanceof List) {
                int i = 1;
                for (Object obj : (List<?>) objects[0])
                    ps.setObject(i++, obj);
            } else {
                for (int i = 0; i < objects.length; i++)
                    ps.setObject(i + 1, objects[i]);
            }
        }

        if (objects == null || objects.length == 0) {
            ps.clearParameters();
        }
        return ps;
    }

    private void validateQuery() {
        if (query == null || query.isEmpty()) throw new IllegalStateException("Query оказался null");
    }

    public int execute(
            final int generatedKeys,
            final Object... objects
    ) {
        validateQuery();

        final Callable<Integer> callable = () -> {
            try (PreparedStatement ps = createStatement(generatedKeys, objects)) {
                ps.execute();

                ResultSet rs = ps.getGeneratedKeys();
                return (rs.next() ? rs.getInt(1) : -1);
            }
        };

        return handle(callable);
    }

    public <T> T executeQuery(
            final ResponseHandler<ResultSet, T> handler,
            final Object... objects
    ) {
        validateQuery();

        final Callable<T> callable = () -> {
            try (PreparedStatement ps = createStatement(PreparedStatement.NO_GENERATED_KEYS, objects)) {

                final ResultSet rs = ps.executeQuery();

                return handler.handleResponse(rs);
            }
        };

        return handle(callable);
    }

    private <T> T handle(
            final Callable<T> callable
    ) {
        if (!sync) {
            final Future<T> future = AbstractDatabase.QUERY_EXECUTOR.submit(callable);
            try {
                return future.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException("Не удалось выполнить асинхронный запрос ", e);
            }
        } else try {
            return callable.call();
        } catch (Exception e) {
            throw new RuntimeException("Не удалось выполнить синхронный запрос", e);
        }
    }
}
