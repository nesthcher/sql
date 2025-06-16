package ru.nesthcher.sql.api.query.constructor;

import java.util.List;

public interface Query {
    @Override String toString();

    List<Object> getPreparedObjects();
}
