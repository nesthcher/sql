package ru.nesthcher.sql.interfaces.table.constructor;

import org.jetbrains.annotations.NotNull;

import ru.nesthcher.sql.interfaces.table.constructor.column.AbstractTableColumn;
import ru.nesthcher.sql.interfaces.table.constructor.column.ColumnType;

/**
 * Интерфейс `AbstractTableConstructor` определяет методы для конструирования таблиц в базе данных.
 */
public interface AbstractTableConstructor {
    /**
     * Создает новую колонку таблицы.
     * @param name Название колонки.
     * @param columnType Тип колонки.
     * @return Объект `AbstractTableColumn`, представляющий колонку таблицы.
     */
    AbstractTableColumn newColumn(@NotNull String name, @NotNull ColumnType columnType);
    /**
     * Добавляет колонку в таблицу.
     * @param column Объект `AbstractTableColumn`, представляющий колонку таблицы.
     */
    void addColumn(@NotNull AbstractTableColumn column);
    /**
     * Удаляет колонку из таблицы.
     * @param name Название колонки, которую необходимо удалить.
     */
    void removeColumn(@NotNull String name);
    /**
     * Добавляет индекс для колонки.
     * @param column Название колонки, для которой необходимо создать индекс.
     */
    void addIndex(@NotNull String column);
    /**
     * Преобразует конструктор таблицы в SQL строку.
     * @return SQL строка, представляющая конструктор таблицы.
     */
    @Override @NotNull String toString();
    /**
     * Создает таблицу в базе данных.
     */
    void create();
}
