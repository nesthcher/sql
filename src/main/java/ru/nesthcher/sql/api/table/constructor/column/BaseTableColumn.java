package ru.nesthcher.sql.api.table.constructor.column;

import org.jetbrains.annotations.NotNull;

import lombok.Getter;

/**
 * Абстрактный класс `BaseTableColumn` реализует интерфейс `AbstractTableColumn` и предоставляет базовую функциональность
 * для представления колонки таблицы в базе данных.
 */
@Getter
public abstract class BaseTableColumn implements AbstractTableColumn {
    /**
     * Название колонки.
     */
    protected final String name;
    /**
     * Тип колонки.
     */
    protected final ColumnType columnType;

    /**
     * Флаг, указывающий, может ли колонка содержать `NULL` значения.
     */
    protected boolean nullValue;
    /**
     * Флаг, указывающий, является ли колонка первичным ключом.
     */
    protected boolean primaryKey;
    /**
     * Флаг, указывающий, является ли колонка уникальной.
     */
    protected boolean unique;
    /**
     * Флаг, указывающий, является ли колонка автоинкрементной.
     */
    protected boolean autoIncrement;

    /**
     * Значение по умолчанию для колонки.
     */
    protected Object defaultValue;

    /**
     * Конструктор класса `BaseTableColumn`.
     * @param name Название колонки.
     * @param columnType Тип колонки.
     */
    public BaseTableColumn(
            @NotNull final String name,
            @NotNull final ColumnType columnType
    ) {
        this.name = name;
        this.columnType = columnType;
    }

    /**
     * Устанавливает флаг, указывающий, может ли колонка содержать `NULL` значения.
     * @param nullValue Значение флага.
     * @return Текущий объект `BaseTableColumn`.
     */
    @Override
    public BaseTableColumn setNull(
            final boolean nullValue
    ) {
        this.nullValue = nullValue;
        return this;
    }

    /**
     * Устанавливает значение по умолчанию для колонки.
     * @param defaultValue Значение по умолчанию.
     * @return Текущий объект `BaseTableColumn`.
     */
    @Override
    public BaseTableColumn setDefaultValue(
            final Object defaultValue
    ) {
        this.defaultValue = defaultValue;
        return this;
    }

    /**
     * Устанавливает флаг, указывающий, является ли колонка первичным ключом.
     * @param primaryKey Значение флага.
     * @return Текущий объект `BaseTableColumn`.
     */
    @Override
    public BaseTableColumn primaryKey(
            final boolean primaryKey
    ) {
        this.primaryKey = primaryKey;
        return this;
    }

    /**
     * Устанавливает флаг, указывающий, является ли колонка уникальной.
     * @param unique Значение флага.
     * @return Текущий объект `BaseTableColumn`.
     */
    @Override
    public BaseTableColumn unique(
            final boolean unique
    ) {
        this.unique = unique;
        return this;
    }

    /**
     * Устанавливает флаг, указывающий, является ли колонка автоинкрементной.
     * @param autoIncrement Значение флага.
     * @return Текущий объект `BaseTableColumn`.
     */
    @Override
    public BaseTableColumn autoIncrement(
            final boolean autoIncrement
    ) {
        this.autoIncrement = autoIncrement;
        return this;
    }

    /**
     * Возвращает строковое представление значения по умолчанию для колонки.
     * @return Строковое представление значения по умолчанию.
     */
    protected @NotNull String getDefaultValueString() {
        return defaultValue == null ? "" : "'" + this.defaultValue.toString() + "'";
    }

    /**
     * Абстрактный метод для преобразования колонки в SQL строку.
     * Должен быть реализован в классах-наследниках.
     * @return SQL строка, представляющая колонку.
     */
    @Override
    public abstract @NotNull String toString();
}
