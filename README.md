# SQL

Библиотека для работы с различными СУБД (MySQL, SQLite) с унифицированным API.

> [!IMPORTANT]\
> Данный проект создается исключительно в целях проверки и улучшения навыков. Его использование в продакшен-версиях не рекомендуется, так как код может содержать нестабильные или неоптимизированные решения. Если у вас есть время и желание помочь в развитии проекта, я буду рад любым замечаниям и критике. Все идеи и предложения по улучшению приветствуются — они помогут сделать проект лучше!

## Обзор

Эта библиотека предоставляет единый интерфейс для работы с базами данных, что позволяет выполнять операции создания таблиц, выполнения запросов и управления соединениями независимо от используемой СУБД. Поддержка SQLite и MySQL обеспечивает гибкость и удобство интеграции в приложения. Также планируется поддержка PostgreSQL и других СУБД.

## Функции

-   Унифицированный API для работы с различными СУБД.
-   Поддержка операций SELECT, INSERT, UPDATE и DELETE.
-   Конструкторы запросов с prepared statements.
-   Конфигурация пула соединений через HikariCP.
-   Легкость расширения для поддержки новых СУБД.

## Оглавление

-   [Подключение к базе данных](#подключение-к-базе-данных)
-   [Работа с таблицами](#работа-с-таблицами)
-   [Выполнение запросов](#выполнение-запросов)
-   [Создание поддержки новой СУБД](#создание-поддержки-новой-субд)
-   [Зависимости](#зависимости)
-   [Лицензия](#лицензия)

## Подключение к базе данных

### SQLite

```java
AbstractDatabase database = new SQLiteDatabase("path/to/database.db");
```

### MySQL

```java
AbstractDatabase database = new MySQLDatabase(
        "localhost",    // хост
        "user",         // пользователь
        "password",     // пароль
        "database",     // имя базы данных
        3306            // порт
);
```

## Работа с таблицами

### Создание таблицы

```java
AbstractTableConstructor constructor = database.table().constructor("users");

constructor.newColumn("id", ColumnType.INT).primaryKey(true).autoIncrement(true);
constructor.newColumn("username", ColumnType.VARCHAR_32).setNull(false);
constructor.newColumn("email", ColumnType.VARCHAR_64).setNull(false).unique(true);
constructor.newColumn("created_at", ColumnType.TIMESTAMP).setDefaultValue("CURRENT_TIMESTAMP");

constructor.create();
```

### Удаление таблицы

```java
database.table().delete("users");
```

## Выполнение запросов

### SELECT

```java
AbstractQuerySelect selectQuery = database.query()
        .select("users")
        .where("age", QuerySymbol.GREATER, 18)
        .limit(10);

database.execute(selectQuery);
```

### INSERT

```java
AbstractQueryInsert insertQuery = database.query()
        .insert("users")
        .value("username", "john_doe")
        .value("email", "john@example.com");

database.execute(insertQuery);
```

### UPDATE

```java
AbstractQueryUpdate updateQuery = database.query()
        .update("users")
        .set("email", "new_email@example.com")
        .where("id", QuerySymbol.EQUALS, 1);

database.execute(updateQuery);
```

### DELETE

```java
AbstractQueryDelete deleteQuery = database.query()
        .delete("users")
        .where("id", QuerySymbol.EQUALS, 1);

database.execute(deleteQuery);
```

## Создание поддержки новой СУБД

Для добавления поддержки новой СУБД необходимо реализовать следующие классы:

### 1. Класс базы данных

```java
public final class NewDatabase extends HikariPoolDatabase {
    @Override
    protected @NotNull HikariDataSource configureDataSource(@NotNull HikariDataSource source) {
        // Конфигурация подключения
        return source;
    }

    @Override
    public AbstractTable table() {
        if(table == null) table = new NewTable(this);
        return table;
    }

    @Override
    public AbstractQuery query() {
        if(query == null) query = new NewQuery(this);
        return query;
    }
}
```

### 2. Классы для работы с таблицами

```java
public final class NewTable extends BaseTable {
    public NewTable(@NotNull AbstractDatabase database) {
        super(database);
    }

    @Override
    public AbstractTableConstructor constructor(@NotNull String tableName) {
        return new NewTableConstructor(database, tableName);
    }

    @Override
    public void delete(@NotNull String tableName) {
        database.execute("DROP TABLE IF EXISTS `" + tableName + "`;");
    }
}
```

### 3. Классы для работы с запросами

```java
public final class NewQuery extends BaseQuery {
    public NewQuery(@NotNull AbstractDatabase database) {
        super(database);
    }

    @Override
    public AbstractQuerySelect select(@NotNull String table) {
        return new NewQuerySelect(table);
    }

    @Override
    public AbstractQueryInsert insert(@NotNull String table) {
        return new NewQueryInsert(table);
    }

    @Override
    public AbstractQueryUpdate update(@NotNull String table) {
        return new NewQueryUpdate(table);
    }

    @Override
    public AbstractQueryDelete delete(@NotNull String table) {
        return new NewQueryDelete(table);
    }
}
```

## Зависимости

```xml
<dependency>
    <groupId>com.zaxxer</groupId>
    <artifactId>HikariCP</artifactId>
    <version>5.0.1</version>
</dependency>
<dependency>
    <groupId>ru.nesthcher</groupId>
    <artifactId>utils</artifactId>
    <version>1.0.3</version>
</dependency>
```

## Лицензия

Эта библиотека доступна по [MIT лицензии](https://opensource.org/license/mit). Можете использовать её свободно.
