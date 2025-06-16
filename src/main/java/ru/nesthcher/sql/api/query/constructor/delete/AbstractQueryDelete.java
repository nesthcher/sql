package ru.nesthcher.sql.api.query.constructor.delete;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import lombok.Getter;
import ru.nesthcher.sql.api.query.QuerySymbol;
import ru.nesthcher.sql.api.query.constructor.Query;
import ru.nesthcher.sql.api.query.constructor.where.AbstractQueryWhere;
import ru.nesthcher.sql.api.query.constructor.where.QueryWhere;

/**
 * Абстрактный класс `AbstractQueryDelete` представляет собой конструктор SQL запроса для удаления данных из таблицы.
 */
@Getter
public abstract class AbstractQueryDelete implements Query {
    /**
     * Название таблицы, из которой необходимо удалить данные.
     */
    protected final String table;
    
    /**
     * Конструктор условия WHERE.
     */
    protected final AbstractQueryWhere where = new QueryWhere();
    
    /**
     * Список значений для prepared statement.
     */
    protected List<Object> preparedObjects = null;
    
    /**
     * Ограничение на количество удаляемых строк.
     */
    protected int limitSize = 0;

    /**
     * Конструктор класса `AbstractQueryDelete`.
     * @param table Название таблицы, из которой необходимо удалить данные.
     * @throws IllegalArgumentException Если название таблицы пустое.
     */
    public AbstractQueryDelete(
            @NotNull final String table
    ) {
        if (table.isEmpty()) throw new IllegalArgumentException("Название таблицы не может быть пустым");
        this.table = table;
    }

    /**
     * Добавляет условие WHERE в запрос.
     * @param column Название колонки.
     * @param symbol Символ сравнения.
     * @param result Значение для сравнения.
     * @return Текущий объект `AbstractQueryDelete`.
     */
    public AbstractQueryDelete where(
            @NotNull final String column,
            @NotNull final QuerySymbol symbol,
            @NotNull final Object result
    ) {
        where.put(column, symbol, result);
        return this;
    }

    /**
     * Устанавливает ограничение на количество удаляемых строк равным 1.
     * @return Текущий объект `AbstractQueryDelete`.
     */
    public AbstractQueryDelete limit() {
        return limit(1);
    }

    /**
     * Устанавливает ограничение на количество удаляемых строк.
     * @param limit Количество удаляемых строк.
     * @return Текущий объект `AbstractQueryDelete`.
     */
    public AbstractQueryDelete limit(
            final int limit
    ) {
        if (limit < 0) return this;
        this.limitSize = limit;
        return this;
    }

    /**
     * Возвращает список значений для prepared statement.
     * @return Список значений для prepared statement.
     * @throws NullPointerException Если параметры prepared statement не указаны.
     */
    @Override
    public List<Object> getPreparedObjects() {
        if (preparedObjects == null) throw new NullPointerException("Не указаны параметры preparedObjects");
        return preparedObjects;
    }

    /**
     * Абстрактный метод для преобразования конструктора запроса в SQL строку.
     * Должен быть реализован в классах-наследниках.
     * @return SQL строка, представляющая конструктор запроса.
     */
    @Override
    public abstract String toString();
}
