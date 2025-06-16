package ru.nesthcher.sql.api.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Перечисление `QuerySymbol` представляет символы сравнения в SQL запросах.
 */
@AllArgsConstructor
@Getter
public enum QuerySymbol {
    /**
     * Символ сравнения "равно".
     */
    EQUALLY("="),
    /**
     * Символ сравнения "больше или равно".
     */
    MORE_OR_EQUAL(">="),
    /**
     * Символ сравнения "больше".
     */
    MORE(">"),
    /**
     * Символ сравнения "меньше".
     */
    LESS("<"),
    /**
     * Символ сравнения "меньше или равно".
     */
    LESS_OR_EQUAL("<="),
    /**
     * Символ сравнения "не равно".
     */
    NOT_EQUAL("!=");

    /**
     * SQL представление символа сравнения.
     */
    private final String symbol;
}
