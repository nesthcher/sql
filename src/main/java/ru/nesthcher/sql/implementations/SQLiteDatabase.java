package ru.nesthcher.sql.implementations;

import org.jetbrains.annotations.NotNull;

import com.zaxxer.hikari.HikariDataSource;

import ru.nesthcher.sql.interfaces.query.AbstractQuery;
import ru.nesthcher.sql.interfaces.table.AbstractTable;
import ru.nesthcher.sql.implementations.query.SQLiteQuery;
import ru.nesthcher.sql.implementations.table.SQLiteTable;

/**
 * Класс `SQLiteDatabase` реализует подключение к базе данных SQLite, используя HikariCP для управления пулом соединений.
 */
public final class SQLiteDatabase extends HikariPoolDatabase {
    /**
     * Путь к файлу базы данных SQLite.
     */
    private final String path;

    /**
     * Конструктор класса `SQLiteDatabase`.
     * @param path Путь к файлу базы данных SQLite.
     */
    public SQLiteDatabase(
            @NotNull String path
	) {
        this.path = path;
        onConnect();
    }

    /**
     * Конфигурирует источник данных HikariCP для подключения к базе данных SQLite.
     * @param source Исходный источник данных HikariCP.
     * @return Сконфигурированный источник данных HikariCP.
     */
    @Override
    protected @NotNull HikariDataSource configureDataSource(
            @NotNull HikariDataSource source
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

    /**
     * Возвращает объект для работы с таблицами SQLite.
     * @return Объект `SQLiteTable`.
     */
    @Override
    public AbstractTable table() {
        if(table == null) table = new SQLiteTable(this);
        return table;
    }

    /**
     * Возвращает объект для выполнения запросов SQLite.
     * @return Объект `SQLiteQuery`.
     */
    @Override
    public AbstractQuery query() {
        if(query == null) query = new SQLiteQuery(this);
        return query;
    }
}
