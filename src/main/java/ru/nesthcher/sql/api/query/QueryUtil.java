package ru.nesthcher.sql.api.query;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import ru.nesthcher.sql.util.Pair;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@UtilityClass
public class QueryUtil {
    public @NotNull Pair<String, List<Object>> convertEntries(
            @NotNull final LinkedHashMap<String, Pair<Object, Object>> entrys,
            final boolean isWhere
    ) {
        if (entrys.isEmpty()) throw new NullPointerException("не указаны параметры ENTRYS");
        final StringBuilder sb = new StringBuilder();
        final List<Object> values = new ArrayList<>();
        int size = 0;
        String column;
        for (final Map.Entry<String, Pair<Object, Object>> entry : entrys.entrySet()) {
            column = entry.getKey();
            values.add(entry.getValue().getFirst());
            sb.append("`")
                    .append(column)
                    .append("` ")
                    .append(isWhere ? ((QuerySymbol) entry.getValue().getSecond()).getSymbol() : "=")
                    .append(" ?");
            size++;
            if (size < entrys.size())
                sb.append(isWhere ? " AND " : " , ");
        }

        return new Pair<>(sb.toString(), values);
    }
}
