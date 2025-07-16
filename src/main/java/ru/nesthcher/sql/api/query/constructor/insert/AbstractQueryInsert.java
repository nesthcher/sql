package ru.nesthcher.sql.api.query.constructor.insert;

import java.util.LinkedHashMap;
import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;

import lombok.Getter;
import ru.nesthcher.sql.api.query.constructor.Query;
import ru.nesthcher.utils.container.Pair;

/**
 * Абстрактный класс `AbstractQueryInsert` представляет собой конструктор SQL запроса для вставки данных в таблицу.
 */
@Getter
public abstract class AbstractQueryInsert implements Query {
    /**
     * Название таблицы, в которую необходимо вставить данные.
     */
    protected final String table;
    /**
     * Карта записей, где ключ - название колонки, значение - пара (значение, null).
     */
    protected final LinkedHashMap<String, Pair<Object, Object>> entries = new LinkedHashMap<>();
    /**
     * Список значений для prepared statement.
     */
    protected ArrayList<Object> preparedObjects = null;

    /**
     * Конструктор класса `AbstractQueryInsert`.
     * @param table Название таблицы, в которую необходимо вставить данные.
     * @throws IllegalArgumentException Если название таблицы пустое.
     */
    public AbstractQueryInsert(
            @NotNull final String table
    ) {
        if (table.isEmpty()) throw new IllegalArgumentException("Название таблицы не может быть пустым");
        this.table = table;
    }

    /**
     * Устанавливает значение для колонки.
     * @param column Название колонки.
     * @param value Значение.
     * @return Текущий объект `AbstractQueryInsert`.
     */
    public AbstractQueryInsert set(
            @NotNull final String column,
            final Object value
    ) {
        entries.put(column, new Pair<>(value, null));
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
