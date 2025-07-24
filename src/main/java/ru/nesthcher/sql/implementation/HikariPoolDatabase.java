package ru.nesthcher.sql.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.jetbrains.annotations.NotNull;

import com.zaxxer.hikari.HikariDataSource;

import ru.nesthcher.sql.api.AbstractDatabase;
import ru.nesthcher.sql.api.ResponseHandler;
import ru.nesthcher.sql.api.StatementWrapper;
import ru.nesthcher.sql.api.query.AbstractQuery;
import ru.nesthcher.sql.api.query.constructor.Query;
import ru.nesthcher.sql.api.table.AbstractTable;

/**
 * Абстрактный класс `HikariPoolDatabase` реализует интерфейс `AbstractDatabase` и предоставляет базовую функциональность
 * для работы с базой данных, используя HikariCP для управления пулом соединений.
 */
public abstract class HikariPoolDatabase implements AbstractDatabase {
    /**
     * Источник данных HikariCP.
     */
    protected HikariDataSource dataSource;
    /**
     * Текущее соединение с базой данных.
     */
    protected Connection connection = null;
    /**
     * Объект для работы с таблицами.
     */
    protected AbstractTable table;
    /**
     * Объект для выполнения запросов.
     */
    protected AbstractQuery query;

    /**
     * Метод, вызываемый при подключении к базе данных.
     * Инициализирует источник данных HikariCP.
     */
    protected void onConnect() {
        this.dataSource = configureDataSource(new HikariDataSource());
    }

    /**
     * Абстрактный метод для конфигурации источника данных HikariCP.
     * Должен быть реализован в классах-наследниках.
     * @param source Исходный источник данных HikariCP.
     * @return Сконфигурированный источник данных HikariCP.
     */
    protected abstract @NotNull HikariDataSource configureDataSource(
            @NotNull HikariDataSource source
    );

    /**
     * Выполняет тестовый запрос к базе данных.
     * @return Результат выполнения запроса.
     */
    @Override
    public int executeTestQuery() {
        return execute("SELECT 1 + 1;");
    }

    /**
     * Выполняет SQL запрос к базе данных.
     * @param query SQL запрос.
     * @param objects Параметры для prepared statement.
     * @return Количество затронутых строк.
     */
    @Override
    public int execute(
            @NotNull String query,
            Object... objects
    ) {
        return execute(StatementWrapper.create(this, query), objects);
    }

    /**
     * Выполняет SQL запрос к базе данных, используя объект `Query`.
     * @param query Объект `Query`, содержащий SQL запрос и параметры.
     * @return Количество затронутых строк.
     */
    @Override
    public int execute(
            @NotNull Query query
    ) {
        return execute(StatementWrapper.create(this, query.toString()), query.getPreparedObjects());
    }

    /**
     * Выполняет SQL запрос к базе данных, используя объект `StatementWrapper`.
     * @param wrapper Объект `StatementWrapper`, содержащий SQL запрос и параметры.
     * @param objects Параметры для prepared statement.
     * @return Количество затронутых строк.
     */
    @Override
    public int execute(
            @NotNull StatementWrapper wrapper,
            Object... objects
    ) {
        return wrapper.execute(PreparedStatement.RETURN_GENERATED_KEYS, objects);
    }

    /**
     * Выполняет SQL запрос к базе данных и обрабатывает результат с помощью `ResponseHandler`.
     * @param query SQL запрос.
     * @param handler Объект `ResponseHandler` для обработки результата запроса.
     * @param objects Параметры для prepared statement.
     * @param <T> Тип возвращаемого значения.
     * @return Результат обработки запроса.
     */
    @Override
    public <T> T executeQuery(
            @NotNull String query,
            ResponseHandler<ResultSet, T> handler,
            Object... objects
    ) {
        return executeQuery(StatementWrapper.create(this, query), handler, objects);
    }

    /**
     * Выполняет SQL запрос к базе данных, используя объект `Query` и обрабатывает результат с помощью `ResponseHandler`.
     * @param query Объект `Query`, содержащий SQL запрос и параметры.
     * @param handler Объект `ResponseHandler` для обработки результата запроса.
     * @param <T> Тип возвращаемого значения.
     * @return Результат обработки запроса.
     */
    @Override
    public <T> T executeQuery(
            @NotNull Query query,
            ResponseHandler<ResultSet, T> handler
    ) {
        return executeQuery(StatementWrapper.create(this, query.toString()), handler, query.getPreparedObjects());
    }

    /**
     * Выполняет SQL запрос к базе данных, используя объект `StatementWrapper` и обрабатывает результат с помощью `ResponseHandler`.
     * @param wrapper Объект `StatementWrapper`, содержащий SQL запрос и параметры.
     * @param handler Объект `ResponseHandler` для обработки результата запроса.
     * @param objects Параметры для prepared statement.
     * @param <T> Тип возвращаемого значения.
     * @return Результат обработки запроса.
     */
    @Override
    public <T> T executeQuery(
            @NotNull StatementWrapper wrapper,
            ResponseHandler<ResultSet, T> handler,
            Object... objects
    ) {
        return wrapper.executeQuery(handler, objects);
    }

    /**
     * Возвращает текущее соединение с базой данных.
     * @return Объект `Connection`.
     */
    @Override
    public Connection getConnection() {
        refreshConnection();
        return connection;
    }

    /**
     * Проверяет, установлено ли локальное соединение с базой данных.
     * @return `true`, если соединение установлено и валидно, иначе `false`.
     */
    public boolean isLocalConnected() {
        try {
            return connection != null && !connection.isClosed() && connection.isValid(500);
        } catch (SQLException ignore) {
        }
        return false;
    }

    /**
     * Проверяет, установлено ли соединение с базой данных.
     * @return `true`, если соединение установлено и валидно, иначе `false`.
     */
    @Override
    public boolean isConnected() {
        refreshConnection();
        return isLocalConnected();
    }

    /**
     * Обновляет соединение с базой данных, если оно не установлено или не валидно.
     */
    protected void refreshConnection() {
        try {
            if (isLocalConnected()) return;
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            onConnect();
        }
    }

    /**
     * Закрывает соединение с базой данных.
     */
    @Override
    public void close() {
        try {
            this.connection.close();
        } catch (SQLException ignore) {
        }
    }

    /**
     * Абстрактный метод для получения объекта для работы с таблицами.
     * Должен быть реализован в классах-наследниках.
     * @return Объект `AbstractTable`.
     */
    @Override
    public abstract AbstractTable table();

    /**
     * Абстрактный метод для получения объекта для выполнения запросов.
     * Должен быть реализован в классах-наследниках.
     * @return Объект `AbstractQuery`.
     */
    @Override
    public abstract AbstractQuery query();
}