# Trivia Arena

A console-based multiplayer quiz game built with **pure Java SE** — no frameworks, no shortcuts.

Questions are fetched live from [Open Trivia DB](https://opentdb.com), players compete simultaneously using real threads, and results are stored in PostgreSQL.

---

## ⚙️ Setup

1. Create a PostgreSQL database named `trivia_db` and run the SQL scripts in `/sql`
2. Edit `src/main/resources/application.properties` with your DB credentials
3. Run:
```bash
mvn compile exec:java -Dexec.mainClass="app.Main"
```

---

## 📚 Concepts Covered

`OOP` `Multithreading` `Stream API` `JDBC` `Serialization`  
`REST API` `Design Patterns` `Custom Annotations` `Generics` `Maven` `Lombok`

---
