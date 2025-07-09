-- 各種テーブル削除
    DROP TABLE IF EXISTS users;
    DROP TABLE IF EXISTS titles;
    DROP TABLE IF EXISTS tasks;

    -- users テーブルを作成するクエリ
    CREATE TABLE users (
    id SERIAL NOT NULL,
    name VARCHAR(20),
    password VARCHAR(50),
    PRIMARY KEY(id)
    );

    -- titles テーブルを作成するクエリ
    CREATE TABLE titles(
    id SERIAL NOT NULL,
    user_id INTEGER,
    title VARCHAR(20),
    deadline DATE,
    title_progress INTEGER,
    title_contents VARCHAR(50),
    PRIMARY KEY(id)
    );

    -- tasksテーブルを作成するクエリ
    CREATE TABLE tasks(
    id SERIAL NOT NULL,
    title_id INTEGER,
    task_progress INTEGER,
    task_title VARCHAR(20),
    PRIMARY KEY(id)
    );

