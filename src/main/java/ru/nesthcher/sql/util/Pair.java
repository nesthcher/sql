package ru.nesthcher.sql.util;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Класс `Pair` представляет собой пару объектов типа A и B.
 * @param <A> Тип первого объекта в паре.
 * @param <B> Тип второго объекта в паре.
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Pair<A, B> {

    /**
     * Первый объект в паре.
     */
    private A first;
    /**
     * Второй объект в паре.
     */
    private B second;

    /**
     * Проверяет, является ли пара пустой.
     * Пара считается пустой, если хотя бы один из объектов (first или second) равен null.
     * @return true, если пара пустая, иначе false.
     */
    public boolean isEmpty() {
        return first == null || second == null;
    }
}