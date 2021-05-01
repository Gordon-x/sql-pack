# SQL Splicing Tool<a name="sql_pack"></a>

```
 ███████╗  ██████╗  ██╗         ██████╗   █████╗   ██████╗ ██╗  ██╗
 ██╔════╝ ██╔═══██╗ ██║         ██╔══██╗ ██╔══██╗ ██╔════╝ ██║ ██╔╝
 ███████╗ ██║   ██║ ██║         ██████╔╝ ███████║ ██║      █████╔╝ 
 ╚════██║ ██║▄▄ ██║ ██║         ██╔═══╝  ██╔══██║ ██║      ██╔═██╗ 
 ███████║ ╚██████╔╝ ███████╗    ██║      ██║  ██║ ╚██████╗ ██║  ██╗
 ╚══════╝  ╚══▀▀═╝  ╚══════╝    ╚═╝      ╚═╝  ╚═╝  ╚═════╝ ╚═╝  ╚═╝
```

![Java Version](https://img.shields.io/badge/jvm-1.8-blue?style=for-the-badge&logo=appveyor)
![Build Status](https://img.shields.io/badge/build-pass-brightgreen?style=for-the-badge&logo=appveyor)
![Latest Version](https://img.shields.io/badge/version-1.0.0-9cf?style=for-the-badge&logo=appveyor)

[中文文档](README.md)

## Project Structure

```
- cn.voiceads.sql
    |- common               
        |- pgsql            
        |- util             
    |- sql                  
        |- pgsql            
```

## Demo

```java
String sql = Query.find()
    .from("test", "t")
    .select("t.id", "t.name")
    .toSql();
assert "select t.id,t.name from test as t".equals(sql);
```

```java
ICondition cond = new Condition()
    .where("t.id", Operator.EQ, 1)
    .orWhere("t.name", Operator.NE, "gordon");

String sql = Query.find()
    .from("test", "t")
    .whereLike("t.name", "abc")
    .andWhere(cond)
    .toSql();

assert "select * from test as t where t.name like '%abc%' and (t.id = 1 or t.name != 'gordon')".equals(sql);
```

## Tips

- Please fill in the `alias` parameter in methods such as `from`, `with` and `join`, otherwise SQL execution exception or splicing SQL failure will occur.
- The Boolean parameters of the `filterJoin` method should be consistent with the occurrence conditions of its related fields
