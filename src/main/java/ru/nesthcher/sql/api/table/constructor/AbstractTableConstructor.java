package ru.nesthcher.sql.api.table.constructor;

import org.jetbrains.annotations.NotNull;

import ru.nesthcher.sql.api.table.constructor.column.AbstractTableColumn;
import ru.nesthcher.sql.api.table.constructor.column.ColumnType;

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
    AbstractTableColumn newColumn(@NotNull final String name, @NotNull final ColumnType columnType);
    /**
     * Добавляет колонку в таблицу.
     * @param column Объект `AbstractTableColumn`, представляющий колонку таблицы.
     */
    void addColumn(@NotNull final AbstractTableColumn column);
    /**
     * Удаляет колонку из таблицы.
     * @param name Название колонки, которую необходимо удалить.
     */
    void removeColumn(@NotNull final String name);
    /**
     * Добавляет индекс для колонки.
     * @param column Название колонки, для которой необходимо создать индекс.
     */
    void addIndex(@NotNull final String column);
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
