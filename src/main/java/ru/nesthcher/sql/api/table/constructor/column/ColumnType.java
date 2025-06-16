package ru.nesthcher.sql.api.table.constructor.column;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Перечисление `ColumnType` представляет типы колонок в базе данных.
 */
@AllArgsConstructor
@Getter
public enum ColumnType {
    /**
     * Тип колонки INT.
     */
    INT("INT"),
    /**
     * Тип колонки INTEGER.
     */
    INTEGER("INTEGER"),
    /**
     * Тип колонки INT(11).
     */
    INT_11("INT(11)"),
    /**
     * Тип колонки INT(2).
     */
    INT_2("INT(2)"),
    /**
     * Тип колонки INT(5).
     */
    INT_5("INT(5)"),
    /**
     * Тип колонки TINYINT.
     */
    TINY_INT("TINYINT"),
    /**
     * Тип колонки TINYINT(2).
     */
    TINY_INT_2("TINYINT(2)"),
    /**
     * Тип колонки VARCHAR(16).
     */
    VARCHAR_16("VARCHAR(16)"),
    /**
     * Тип колонки VARCHAR(32).
     */
    VARCHAR_32("VARCHAR(32)"),
    /**
     * Тип колонки VARCHAR(48).
     */
    VARCHAR_48("VARCHAR(48)"),
    /**
     * Тип колонки VARCHAR(64).
     */
    VARCHAR_64("VARCHAR(64)"),
    /**
     * Тип колонки VARCHAR(128).
     */
    VARCHAR_128("VARCHAR(128)"),
    /**
     * Тип колонки TEXT(0).
     */
    TEXT("TEXT(0)"),
    /**
     * Тип колонки BIGINT(18).
     */
    BIG_INT("BIGINT(18)"),
    /**
     * Тип колонки BOOLEAN.
     */
    BOOLEAN("BOOLEAN"),
    /**
     * Тип колонки DOUBLE.
     */
    DOUBLE("DOUBLE"),
    /**
     * Тип колонки TIMESTAMP.
     */
    TIMESTAMP("TIMESTAMP"),
    ;

    /**
     * SQL представление типа колонки.
     */
    private final String sql;
}