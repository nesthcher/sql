package ru.nesthcher.sql.api.query.constructor.select;

import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;

import lombok.Getter;
import ru.nesthcher.sql.api.query.QuerySymbol;
import ru.nesthcher.sql.api.query.constructor.Query;
import ru.nesthcher.sql.api.query.constructor.where.AbstractQueryWhere;
import ru.nesthcher.sql.api.query.constructor.where.QueryWhere;

/**
 * Абстрактный класс `AbstractQuerySelect` представляет собой конструктор SQL запроса для выборки данных из таблицы.
 */
@Getter
public abstract class AbstractQuerySelect implements Query {
    /**
     * Название таблицы, из которой необходимо выбрать данные.
     */
    protected final String table;
    /**
     * Конструктор условия WHERE.
     */
    protected final AbstractQueryWhere where = new QueryWhere();
    /**
     * Список значений для prepared statement.
     */
    protected ArrayList<Object> preparedObjects = null;
    /**
     * Название колонки, которую необходимо выбрать. Если null, то выбираются все колонки.
     */
    protected String result;
    /**
     * Ограничение на количество выбираемых строк.
     */
    protected int limitSize = 0;

    /**
     * Конструктор класса `AbstractQuerySelect`.
     * @param table Название таблицы, из которой необходимо выбрать данные.
     * @throws IllegalArgumentException Если название таблицы пустое.
     */
    public AbstractQuerySelect(
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
     * @return Текущий объект `AbstractQuerySelect`.
     */
    public AbstractQuerySelect where(
            @NotNull final String column,
            @NotNull final QuerySymbol symbol,
            final Object result
    ) {
        where.put(column, symbol, result);
        return this;
    }

    /**
     * Устанавливает ограничение на количество выбираемых строк равным 1.
     * @return Текущий объект `AbstractQuerySelect`.
     */
    public AbstractQuerySelect limit() {
        return limit(1);
    }

    /**
     * Устанавливает ограничение на количество выбираемых строк.
     * @param limit Количество выбираемых строк.
     * @return Текущий объект `AbstractQuerySelect`.
     */
    public AbstractQuerySelect limit(
            final int limit
    ) {
        if (limit < 0) return this;
        this.limitSize = limit;
        return this;
    }

    /**
     * Устанавливает название колонки, которую необходимо выбрать.
     * @param result Название колонки.
     * @return Текущий объект `AbstractQuerySelect`.
     */
    public AbstractQuerySelect result(
            @NotNull final String result
    ) {
        if (!result.equals("*")) this.result = result;
        return this;
    }

    /**
     * Возвращает список значений для prepared statement.
     * @return Список значений для prepared statement.
     * @throws NullPointerException Если параметры prepared statement не указаны.
     */
    @Override
    public ArrayList<Object> getPreparedObjects() {
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
