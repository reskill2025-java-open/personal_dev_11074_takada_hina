-- users テーブルにデータを挿入するクエリ
INSERT INTO users (name, password)
VALUES
('田中太郎', 'test123'),
('鈴木一郎', 'test456');

-- titles テーブルにデータを挿入するクエリ
INSERT INTO titles(user_id, title, deadline, title_progress, title_contents)
VALUES
(1, '宿題', '2025-12-31', 0, '冬休みの宿題をやる'),

(2, '掃除', '2025-12-31', 0, '年末までに家の掃除をする');

-- tasks テーブルにデータを挿入するクエリ
INSERT INTO tasks (id, title_id, task_progress, task_title)
VALUES
(1, 1, 0, '国語'),
(2, 1, 0, '算数'),
(3, 2, 0, 'お風呂掃除'),
(4, 2, 1, 'キッチンの掃除'),
(5, 2, 0, 'リビングの掃除');