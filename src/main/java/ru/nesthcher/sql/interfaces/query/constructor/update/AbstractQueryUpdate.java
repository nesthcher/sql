package ru.nesthcher.sql.interfaces.query.constructor.update;

import java.util.LinkedHashMap;
import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;

import lombok.Getter;
import ru.nesthcher.sql.interfaces.query.QuerySymbol;
import ru.nesthcher.sql.interfaces.query.constructor.Query;
import ru.nesthcher.sql.interfaces.query.constructor.where.AbstractQueryWhere;
import ru.nesthcher.sql.interfaces.query.constructor.where.QueryWhere;
import ru.nesthcher.utils.container.Pair;

/**
 * Абстрактный класс `AbstractQueryUpdate` представляет собой конструктор SQL запроса для обновления данных в таблице.
 */
@Getter
public abstract class AbstractQueryUpdate implements Query {
    /**
     * Название таблицы, которую необходимо обновить.
     */
    protected final String table;
    /**
     * Карта записей, где ключ - название колонки, значение - пара (новое значение, null).
     */
    protected final LinkedHashMap<String, Pair<Object, Object>> entries = new LinkedHashMap<>();
    /**
     * Список значений для prepared statement.
     */
    protected ArrayList<Object> preparedObjects = null;
    /**
     * Конструктор условия WHERE.
     */
    protected final AbstractQueryWhere where = new QueryWhere();
    /**
     * Ограничение на количество обновляемых строк.
     */
    protected int limitSize = 0;

    /**
     * Конструктор класса `AbstractQueryUpdate`.
     * @param table Название таблицы, которую необходимо обновить.
     * @throws IllegalArgumentException Если название таблицы пустое.
     */
    public AbstractQueryUpdate(
            @NotNull String table
    ) {
        if (table.isEmpty()) throw new IllegalArgumentException("Название таблицы не может быть пустым");
        this.table = table;
    }

    /**
     * Устанавливает новое значение для колонки.
     * @param column Название колонки.
     * @param value Новое значение.
     * @return Текущий объект `AbstractQueryUpdate`.
     */
    public AbstractQueryUpdate set(
            @NotNull String column,
            Object value
    ) {
        entries.put(column, new Pair<>(value, null));
        return this;
    }

    /**
     * Добавляет условие WHERE в запрос.
     * @param column Название колонки.
     * @param symbol Символ сравнения.
     * @param result Значение для сравнения.
     * @return Текущий объект `AbstractQueryUpdate`.
     */
    public AbstractQueryUpdate where(
            @NotNull String column,
            @NotNull QuerySymbol symbol,
            Object result
    ) {
        where.put(column, symbol, result);
        return this;
    }

    /**
     * Устанавливает ограничение на количество обновляемых строк равным 1.
     * @return Текущий объект `AbstractQueryUpdate`.
     */
    public AbstractQueryUpdate limit() {
        return limit(1);
    }

    /**
     * Устанавливает ограничение на количество обновляемых строк.
     * @param limit Количество обновляемых строк.
     * @return Текущий объект `AbstractQueryUpdate`.
     */
    public AbstractQueryUpdate limit(
            int limit
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
