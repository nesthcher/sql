package ru.nesthcher.sql.util;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Pair<A, B> {

    private A first;
    private B second;

    public boolean isEmpty() {
        return first == null || second == null;
    }
}