package ru.nesthcher.sql.api.table;

import org.jetbrains.annotations.NotNull;

import ru.nesthcher.sql.api.AbstractDatabase;
import ru.nesthcher.sql.api.table.constructor.AbstractTableConstructor;

/**
 * Интерфейс `AbstractTable` определяет методы для работы с таблицами в базе данных.
 */
public interface AbstractTable {
    /**
     * Возвращает базу данных, с которой работает таблица.
     * @return Объект `AbstractDatabase`, представляющий базу данных.
     */
    AbstractDatabase getDatabase();
    /**
     * Создает конструктор таблицы.
     * @param tableName Название таблицы.
     * @return Объект `AbstractTableConstructor`, представляющий конструктор таблицы.
     */
    AbstractTableConstructor constructor(@NotNull final String tableName);
    /**
     * Удаляет таблицу из базы данных.
     * @param tableName Название таблицы, которую необходимо удалить.
     */
    void delete(@NotNull final String tableName);
}
