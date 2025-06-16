package ru.nesthcher.sql.api.query;

import org.jetbrains.annotations.NotNull;

import lombok.Getter;
import ru.nesthcher.sql.api.AbstractDatabase;
import ru.nesthcher.sql.api.query.constructor.delete.AbstractQueryDelete;
import ru.nesthcher.sql.api.query.constructor.insert.AbstractQueryInsert;
import ru.nesthcher.sql.api.query.constructor.select.AbstractQuerySelect;
import ru.nesthcher.sql.api.query.constructor.update.AbstractQueryUpdate;

/**
 * Абстрактный класс `BaseQuery` реализует интерфейс `AbstractQuery` и предоставляет базовую функциональность
 * для работы с SQL запросами.
 */
@Getter
public abstract class BaseQuery implements AbstractQuery {
    /**
     * База данных, с которой работает запрос.
     */
    protected final AbstractDatabase database;

    /**
     * Конструктор класса `BaseQuery`.
     * @param database База данных, с которой работает запрос.
     */
    protected BaseQuery(
            @NotNull final AbstractDatabase database
    ) {
        this.database = database;
    }

    /**
     * Абстрактный метод для создания конструктора запроса на удаление данных.
     * Должен быть реализован в классах-наследниках.
     * @param table Название таблицы, из которой необходимо удалить данные.
     * @return Объект `AbstractQueryDelete`, представляющий конструктор запроса на удаление данных.
     */
    @Override
    public abstract AbstractQueryDelete delete(
            @NotNull final String table
    );

    /**
     * Абстрактный метод для создания конструктора запроса на вставку данных.
     * Должен быть реализован в классах-наследниках.
     * @param table Название таблицы, в которую необходимо вставить данные.
     * @return Объект `AbstractQueryInsert`, представляющий конструктор запроса на вставку данных.
     */
    @Override
    public abstract AbstractQueryInsert insert(
            @NotNull final String table
    );

    /**
     * Абстрактный метод для создания конструктора запроса на выборку данных.
     * Должен быть реализован в классах-наследниках.
     * @param table Название таблицы, из которой необходимо выбрать данные.
     * @return Объект `AbstractQuerySelect`, представляющий конструктор запроса на выборку данных.
     */
    @Override
    public abstract AbstractQuerySelect select(
            @NotNull final String table
    );

    /**
     * Абстрактный метод для создания конструктора запроса на обновление данных.
     * Должен быть реализован в классах-наследниках.
     * @param table Название таблицы, в которой необходимо обновить данные.
     * @return Объект `AbstractQueryUpdate`, представляющий конструктор запроса на обновление данных.
     */
    @Override
    public abstract AbstractQueryUpdate update(
            @NotNull final String table
    );
}
