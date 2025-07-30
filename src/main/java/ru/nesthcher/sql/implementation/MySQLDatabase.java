package ru.nesthcher.sql.implementation;

import org.jetbrains.annotations.NotNull;

import com.zaxxer.hikari.HikariDataSource;

import ru.nesthcher.sql.interfaces.query.AbstractQuery;
import ru.nesthcher.sql.interfaces.table.AbstractTable;
import ru.nesthcher.sql.implementation.query.MySQLQuery;
import ru.nesthcher.sql.implementation.table.MySQLTable;

/**
 * Класс `MySQLDatabase` реализует подключение к базе данных MySQL, используя HikariCP для управления пулом соединений.
 */
public final class MySQLDatabase extends HikariPoolDatabase {
    /**
     * Хост базы данных MySQL.
     */
    private final String host;
    /**
     * Пароль для подключения к базе данных MySQL.
     */
    private final String password;
    /**
     * Имя пользователя для подключения к базе данных MySQL.
     */
    private final String user;
    /**
     * Имя базы данных MySQL.
     */
    private final String data;
    /**
     * Порт для подключения к базе данных MySQL.
     */
    private final int port;

    /**
     * Конструктор класса `MySQLDatabase`.
     * @param host Хост базы данных MySQL.
     * @param user Имя пользователя для подключения к базе данных MySQL.
     * @param password Пароль для подключения к базе данных MySQL.
     * @param data Имя базы данных MySQL.
     * @param port Порт для подключения к базе данных MySQL.
     */
    public MySQLDatabase(
            @NotNull String host,
            @NotNull String user,
            @NotNull String password,
            @NotNull String data,
            int port
	) {
        this.host = host;
        this.password = password;
        this.user = user;
        this.data = data;
        this.port = port;
        onConnect();
    }

    /**
     * Конфигурирует источник данных HikariCP для подключения к базе данных MySQL.
     * @param source Исходный источник данных HikariCP.
     * @return Сконфигурированный источник данных HikariCP.
     */
    @Override
    protected @NotNull HikariDataSource configureDataSource(
            @NotNull HikariDataSource source
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

    /**
     * Возвращает объект для работы с таблицами MySQL.
     * @return Объект `MySQLTable`.
     */
    @Override
    public AbstractTable table() {
        if(table == null) table = new MySQLTable(this);
        return table;
    }

    /**
     * Возвращает объект для выполнения запросов MySQL.
     * @return Объект `MySQLQuery`.
     */
    @Override
    public AbstractQuery query() {
        if(query == null) query = new MySQLQuery(this);
        return query;
    }
}
