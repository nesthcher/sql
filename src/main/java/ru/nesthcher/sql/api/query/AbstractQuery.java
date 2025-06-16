package ru.nesthcher.sql.api.query;

import org.jetbrains.annotations.NotNull;

import ru.nesthcher.sql.api.AbstractDatabase;
import ru.nesthcher.sql.api.query.constructor.delete.AbstractQueryDelete;
import ru.nesthcher.sql.api.query.constructor.insert.AbstractQueryInsert;
import ru.nesthcher.sql.api.query.constructor.select.AbstractQuerySelect;
import ru.nesthcher.sql.api.query.constructor.update.AbstractQueryUpdate;

/**
 * Интерфейс `AbstractQuery` определяет методы для работы с SQL запросами.
 */
public interface AbstractQuery {
    /**
     * Возвращает базу данных, с которой работает запрос.
     * @return Объект `AbstractDatabase`, представляющий базу данных.
     */
    AbstractDatabase getDatabase();
    /**
     * Создает конструктор запроса на удаление данных.
     * @param table Название таблицы, из которой необходимо удалить данные.
     * @return Объект `AbstractQueryDelete`, представляющий конструктор запроса на удаление данных.
     */
    AbstractQueryDelete delete(@NotNull final String table);
    /**
     * Создает конструктор запроса на вставку данных.
     * @param table Название таблицы, в которую необходимо вставить данные.
     * @return Объект `AbstractQueryInsert`, представляющий конструктор запроса на вставку данных.
     */
    AbstractQueryInsert insert(@NotNull final String table);
    /**
     * Создает конструктор запроса на выборку данных.
     * @param table Название таблицы, из которой необходимо выбрать данные.
     * @return Объект `AbstractQuerySelect`, представляющий конструктор запроса на выборку данных.
     */
    AbstractQuerySelect select(@NotNull final String table);
    /**
     * Создает конструктор запроса на обновление данных.
     * @param table Название таблицы, в которой необходимо обновить данные.
     * @return Объект `AbstractQueryUpdate`, представляющий конструктор запроса на обновление данных.
     */
    AbstractQueryUpdate update(@NotNull final String table);
}
