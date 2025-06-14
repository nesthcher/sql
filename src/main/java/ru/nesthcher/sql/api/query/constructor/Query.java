package ru.nesthcher.sql.api.query.constructor;

import java.util.List;

public interface Query {
    String toString();

    List<Object> getPreparedObjects();
}
