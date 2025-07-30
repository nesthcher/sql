package ru.nesthcher.sql.interfaces;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jetbrains.annotations.NotNull;

import ru.nesthcher.sql.interfaces.query.AbstractQuery;
import ru.nesthcher.sql.interfaces.query.constructor.Query;
import ru.nesthcher.sql.interfaces.table.AbstractTable;

/**
 * Интерфейс `AbstractDatabase` определяет методы для работы с базой данных.
 */
public interface AbstractDatabase {
    /**
     * Пул потоков для выполнения SQL запросов асинхронно.
     */
    ExecutorService QUERY_EXECUTOR = Executors.newCachedThreadPool();

    /**
     * Выполняет тестовый запрос к базе данных.
     * @return Количество затронутых строк.
     */
    int executeTestQuery();
    /**
     * Выполняет SQL запрос к базе данных.
     * @param query SQL запрос.
     * @param objects Параметры для prepared statement.
     * @return Количество затронутых строк.
     */
    int execute(@NotNull String query, Object... objects);
    /**
     * Выполняет SQL запрос к базе данных, используя объект `Query`.
     * @param query Объект `Query`, содержащий SQL запрос и параметры.
     * @return Количество затронутых строк.
     */
    int execute(@NotNull Query query);
    /**
     * Выполняет SQL запрос к базе данных, используя объект `StatementWrapper`.
     * @param wrapper Объект `StatementWrapper`, содержащий SQL запрос и параметры.
     * @param objects Параметры для prepared statement.
     * @return Количество затронутых строк.
     */
    int execute(@NotNull StatementWrapper wrapper, Object... objects);
    /**
     * Выполняет SQL запрос к базе данных и обрабатывает результат с помощью `ResponseHandler`.
     * @param query SQL запрос.
     * @param handler Объект `ResponseHandler` для обработки результата запроса.
     * @param objects Параметры для prepared statement.
     * @param <T> Тип возвращаемого значения.
     * @return Результат обработки запроса.
     */
    <T> T executeQuery(@NotNull String query, ResponseHandler<ResultSet, T> handler, Object... objects);
    /**
     * Выполняет SQL запрос к базе данных, используя объект `Query` и обрабатывает результат с помощью `ResponseHandler`.
     * @param Query Объект `Query`, содержащий SQL запрос и параметры.
     * @param handler Объект `ResponseHandler` для обработки результата запроса.
     * @param <T> Тип возвращаемого значения.
     * @return Результат обработки запроса.
     */
    <T> T executeQuery(@NotNull Query Query, ResponseHandler<ResultSet, T> handler);
    /**
     * Выполняет SQL запрос к базе данных, используя объект `StatementWrapper` и обрабатывает результат с помощью `ResponseHandler`.
     * @param wrapper Объект `StatementWrapper`, содержащий SQL запрос и параметры.
     * @param handler Объект `ResponseHandler` для обработки результата запроса.
     * @param objects Параметры для prepared statement.
     * @param <T> Тип возвращаемого значения.
     * @return Результат обработки запроса.
     */
    <T> T executeQuery(@NotNull StatementWrapper wrapper, ResponseHandler<ResultSet, T> handler, Object... objects);
    /**
     * Закрывает соединение с базой данных.
     */
    void close();
    /**
     * Возвращает текущее соединение с базой данных.
     * @return Объект `Connection`.
     */
    Connection getConnection();
    /**
     * Проверяет, установлено ли соединение с базой данных.
     * @return `true`, если соединение установлено и валидно, иначе `false`.
     */
    boolean isConnected();

    /**
     * Возвращает объект для работы с таблицами.
     * @return Объект `AbstractTable`.
     */
    AbstractTable table();
    /**
     * Возвращает объект для выполнения запросов.
     * @return Объект `AbstractQuery`.
     */
    AbstractQuery query();
}
