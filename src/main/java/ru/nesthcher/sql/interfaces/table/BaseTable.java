package ru.nesthcher.sql.interfaces.table;

import org.jetbrains.annotations.NotNull;

import lombok.Getter;
import ru.nesthcher.sql.interfaces.AbstractDatabase;
import ru.nesthcher.sql.interfaces.table.constructor.AbstractTableConstructor;

/**
 * Абстрактный класс `BaseTable` реализует интерфейс `AbstractTable` и предоставляет базовую функциональность
 * для работы с таблицами в базе данных.
 */
@Getter
public abstract class BaseTable implements AbstractTable {
    /**
     * База данных, с которой работает таблица.
     */
    protected final AbstractDatabase database;

    /**
     * Конструктор класса `BaseTable`.
     * @param database База данных, с которой работает таблица.
     */
    protected BaseTable(
            @NotNull AbstractDatabase database
    ) {
        this.database = database;
    }

    /**
     * Абстрактный метод для создания конструктора таблицы.
     * Должен быть реализован в классах-наследниках.
     * @param name Название таблицы.
     * @return Объект `AbstractTableConstructor`, представляющий конструктор таблицы.
     */
    @Override
    public abstract AbstractTableConstructor constructor(
            @NotNull String name
    );

    /**
     * Абстрактный метод для удаления таблицы из базы данных.
     * Должен быть реализован в классах-наследниках.
     * @param name Название таблицы, которую необходимо удалить.
     */
    @Override
    public abstract void delete(
            @NotNull String name
    );
}
