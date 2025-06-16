package ru.nesthcher.sql.api;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.jetbrains.annotations.NotNull;

import lombok.RequiredArgsConstructor;

/**
 * Класс `StatementWrapper` оборачивает SQL запрос и предоставляет методы для его выполнения.
 */
@RequiredArgsConstructor
public class StatementWrapper {
    /**
     * База данных, с которой работает запрос.
     */
    private final AbstractDatabase database;
    /**
     * SQL запрос.
     */
    private String query;
    /**
     * Флаг, указывающий, выполняется ли запрос синхронно.
     */
    private boolean sync = false;

    /**
     * Создает новый объект `StatementWrapper`.
     * @param database База данных, с которой работает запрос.
     * @param query SQL запрос.
     * @return Объект `StatementWrapper`.
     */
    public static StatementWrapper create(
            @NotNull final AbstractDatabase database,
            @NotNull final String query
    ) {
        return new StatementWrapper(database).setQuery(query);
    }

    /**
     * Устанавливает SQL запрос.
     * @param query SQL запрос.
     * @return Текущий объект `StatementWrapper`.
     */
    public StatementWrapper setQuery(
            @NotNull final String query
    ) {
        this.query = query;
        return this;
    }

    /**
     * Устанавливает флаг, указывающий, выполняется ли запрос синхронно.
     * @return Текущий объект `StatementWrapper`.
     */
    public StatementWrapper sync() {
        sync = true;
        return this;
    }

    /**
     * Создает объект `PreparedStatement` для выполнения SQL запроса.
     * @param generatedKeys Флаг, указывающий, необходимо ли возвращать сгенерированные ключи.
     * @param objects Параметры для prepared statement.
     * @return Объект `PreparedStatement`.
     * @throws SQLException Если произошла ошибка при создании `PreparedStatement`.
     */
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

    /**
     * Проверяет, установлен ли SQL запрос.
     * @throws IllegalStateException Если SQL запрос не установлен.
     */
    private void validateQuery() {
        if (query == null || query.isEmpty()) throw new IllegalStateException("Query оказался null");
    }

    /**
     * Выполняет SQL запрос.
     * @param generatedKeys Флаг, указывающий, необходимо ли возвращать сгенерированные ключи.
     * @param objects Параметры для prepared statement.
     * @return Количество затронутых строк или сгенерированный ключ.
     */
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

    /**
     * Выполняет SQL запрос и обрабатывает результат с помощью `ResponseHandler`.
     * @param handler Объект `ResponseHandler` для обработки результата запроса.
     * @param objects Параметры для prepared statement.
     * @param <T> Тип возвращаемого значения.
     * @return Результат обработки запроса.
     */
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

    /**
     * Обрабатывает выполнение SQL запроса.
     * @param callable Объект `Callable`, выполняющий SQL запрос.
     * @param <T> Тип возвращаемого значения.
     * @return Результат выполнения запроса.
     */
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
