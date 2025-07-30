package ru.nesthcher.sql.interfaces.table.constructor.column;

import org.jetbrains.annotations.NotNull;

/**
 * Интерфейс `AbstractTableColumn` определяет методы для работы с колонками таблицы в базе данных.
 */
public interface AbstractTableColumn {
    /**
     * Возвращает название колонки.
     * @return Название колонки.
     */
    String getName();
    /**
     * Возвращает тип колонки.
     * @return Тип колонки.
     */
    ColumnType getColumnType();
    /**
     * Возвращает флаг, указывающий, может ли колонка содержать `NULL` значения.
     * @return `true`, если колонка может содержать `NULL` значения, иначе `false`.
     */
    boolean isNullValue();
    /**
     * Устанавливает флаг, указывающий, может ли колонка содержать `NULL` значения.
     * @param nullValue Значение флага.
     * @return Текущий объект `AbstractTableColumn`.
     */
    AbstractTableColumn setNull(boolean nullValue);
    /**
     * Возвращает значение по умолчанию для колонки.
     * @return Значение по умолчанию.
     */
    Object getDefaultValue();
    /**
     * Устанавливает значение по умолчанию для колонки.
     * @param defaultValue Значение по умолчанию.
     * @return Текущий объект `AbstractTableColumn`.
     */
    AbstractTableColumn setDefaultValue(Object defaultValue);
    /**
     * Возвращает флаг, указывающий, является ли колонка первичным ключом.
     * @return `true`, если колонка является первичным ключом, иначе `false`.
     */
    boolean isPrimaryKey();
    /**
     * Устанавливает флаг, указывающий, является ли колонка первичным ключом.
     * @param primaryKey Значение флага.
     * @return Текущий объект `AbstractTableColumn`.
     */
    AbstractTableColumn primaryKey(boolean primaryKey);
    /**
     * Возвращает флаг, указывающий, является ли колонка уникальной.
     * @return `true`, если колонка является уникальной, иначе `false`.
     */
    boolean isUnique();
    /**
     * Устанавливает флаг, указывающий, является ли колонка уникальной.
     * @param unique Значение флага.
     * @return Текущий объект `AbstractTableColumn`.
     */
    AbstractTableColumn unique(boolean unique);
    /**
     * Возвращает флаг, указывающий, является ли колонка автоинкрементной.
     * @return `true`, если колонка является автоинкрементной, иначе `false`.
     */
    boolean isAutoIncrement();
    /**
     * Устанавливает флаг, указывающий, является ли колонка автоинкрементной.
     * @param autoIncrement Значение флага.
     * @return Текущий объект `AbstractTableColumn`.
     */
    AbstractTableColumn autoIncrement(boolean autoIncrement);
    /**
     * Преобразует колонку в SQL строку.
     * @return SQL строка, представляющая колонку.
     */
    @Override @NotNull String toString();
}
