package ru.nesthcher.sql.interfaces.table.constructor.column;

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
            @NotNull String name,
            @NotNull ColumnType columnType
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
            boolean nullValue
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
            Object defaultValue
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
            boolean primaryKey
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
            boolean unique
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
            boolean autoIncrement
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
