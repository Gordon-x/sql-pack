# SQL组装工具<a name="sql_pack"></a>

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
![Latest Version](https://img.shields.io/badge/version-1.0.SNAPSHOT-9cf?style=for-the-badge&logo=appveyor)

## 目录结构

```
- cn.voiceads.sql
    |- common               
        |- pgsql            pgsql拼接公共类
        |- util             工具
    |- sql                  组装接口
        |- pgsql            pgsql接口实现类
```

## 使用示例

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
    .from("test1", "t1")
    .whereLike("t.name", "abc")
    .andWhere(cond)
    .toSql();

assert "select * from test1 as t1 where t.name like '%abc%' and (t.id = 1 or t.name != 'gordon')".equals(sql);
```

## 注意

- from, with, join等方法中等alias参数请务必填写，否则会出现SQL执行异常或拼接SQL失败。
- filterJoin等方法在使用过程中需注意与相关表字段与条件/分组的条件同步，避免filterJoin不成立时造成其相关字段仍然保留在sql内导致SQL错误。建议对同一个filter表的字段/条件使用相同的条件。

## UML
![image](https://git.iflytek.com/CBG_ILS/sql_pack/-/wikis/uploads/b48cb57cada4aa209a828ad7e92c8d50/image.png)

## 条件构造对象

![image](https://git.iflytek.com/CBG_ILS/sql_pack/-/wikis/uploads/fa5c46422be4de333f98fbbacac837ce/image.png)
