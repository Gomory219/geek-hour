-- =============================================
-- 为课程插入10个点赞数据
-- =============================================

-- 先创建一些测试用户（如果不存在）
INSERT INTO tb_user (id, account, user_name, password, role, is_deleted)
VALUES
    ('u001', 'user001', '张三', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 'USER', false),
    ('u002', 'user002', '李四', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 'USER', false),
    ('u003', 'user003', '王五', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 'USER', false),
    ('u004', 'user004', '赵六', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 'USER', false),
    ('u005', 'user005', '钱七', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 'USER', false),
    ('u006', 'user006', '孙八', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 'USER', false),
    ('u007', 'user007', '周九', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 'USER', false),
    ('u008', 'user008', '吴十', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 'USER', false),
    ('u009', 'user009', '郑十一', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 'USER', false),
    ('u010', 'user010', '王小二', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 'USER', false)
ON CONFLICT (id) DO NOTHING;

-- 为课程 "Python编程快速上手" (c012) 插入10个点赞
INSERT INTO tb_like (id, user_id, biz_type, biz_id, create_time, update_time, is_deleted)
VALUES
    ('l001', 'u001', 'COURSE', 'c012', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, false),
    ('l002', 'u002', 'COURSE', 'c012', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, false),
    ('l003', 'u003', 'COURSE', 'c012', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, false),
    ('l004', 'u004', 'COURSE', 'c012', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, false),
    ('l005', 'u005', 'COURSE', 'c012', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, false),
    ('l006', 'u006', 'COURSE', 'c012', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, false),
    ('l007', 'u007', 'COURSE', 'c012', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, false),
    ('l008', 'u008', 'COURSE', 'c012', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, false),
    ('l009', 'u009', 'COURSE', 'c012', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, false),
    ('l010', 'u010', 'COURSE', 'c012', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, false)
ON CONFLICT (id) DO NOTHING;

-- 验证插入结果
SELECT
    c.name AS course_name,
    COUNT(l.id) AS like_count
FROM tb_course c
LEFT JOIN tb_like l ON c.id = l.biz_id AND l.biz_type = 'COURSE' AND l.is_deleted = false
WHERE c.id = 'c012'
GROUP BY c.name;
