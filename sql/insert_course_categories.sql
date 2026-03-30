-- =============================================
-- 课程两级分类数据插入脚本
-- 8 个一级分类 + 34 个二级分类 = 42 条记录
-- =============================================

-- 步骤1: 插入一级分类
INSERT INTO tb_category (name, type) VALUES
('前端开发', 'COURSE'),
('后端开发', 'COURSE'),
('移动开发', 'COURSE'),
('数据库', 'COURSE'),
('云计算与运维', 'COURSE'),
('人工智能', 'COURSE'),
('网络安全', 'COURSE'),
('数据科学与分析', 'COURSE');

-- 步骤2: 插入一级分类的自身引用 (depth=0)
INSERT INTO tb_category_relation (id, ancestor, descendant, depth)
SELECT 'cr_p_' || id, id, id, 0 FROM tb_category WHERE type = 'COURSE' AND id <= 8;

-- 步骤3: 插入二级分类
INSERT INTO tb_category (name, type) VALUES
-- 前端开发 (parent_id = 1)
('HTML/CSS', 'COURSE'),
('JavaScript', 'COURSE'),
('Vue.js', 'COURSE'),
('React', 'COURSE'),
('前端工程化', 'COURSE'),
-- 后端开发 (parent_id = 2)
('Java', 'COURSE'),
('Python', 'COURSE'),
('Go', 'COURSE'),
('Node.js', 'COURSE'),
('Spring Boot', 'COURSE'),
-- 移动开发 (parent_id = 3)
('Android', 'COURSE'),
('iOS', 'COURSE'),
('Flutter', 'COURSE'),
('React Native', 'COURSE'),
-- 数据库 (parent_id = 4)
('MySQL', 'COURSE'),
('Redis', 'COURSE'),
('MongoDB', 'COURSE'),
('PostgreSQL', 'COURSE'),
-- 云计算与运维 (parent_id = 5)
('Linux', 'COURSE'),
('Docker', 'COURSE'),
('Kubernetes', 'COURSE'),
('CI/CD', 'COURSE'),
-- 人工智能 (parent_id = 6)
('机器学习', 'COURSE'),
('深度学习', 'COURSE'),
('自然语言处理', 'COURSE'),
('计算机视觉', 'COURSE'),
-- 网络安全 (parent_id = 7)
('Web安全', 'COURSE'),
('渗透测试', 'COURSE'),
('密码学', 'COURSE'),
('安全运维', 'COURSE'),
-- 数据科学与分析 (parent_id = 8)
('数据分析', 'COURSE'),
('数据可视化', 'COURSE'),
('大数据', 'COURSE'),
('统计学', 'COURSE');

-- 步骤4: 插入二级分类的自身引用 (depth=0)
INSERT INTO tb_category_relation (id, ancestor, descendant, depth)
SELECT 'cr_s_' || id, id, id, 0 FROM tb_category WHERE type = 'COURSE' AND id NOT IN (
    SELECT ancestor FROM tb_category_relation WHERE depth = 0
);

-- 步骤5: 插入父子关系 (depth=1)
-- 前端开发(id=1) -> 其子分类
INSERT INTO tb_category_relation (id, ancestor, descendant, depth) VALUES
('cr_r_1_9', 1, (SELECT id FROM tb_category WHERE name = 'HTML/CSS' AND type = 'COURSE'), 1),
('cr_r_1_10', 1, (SELECT id FROM tb_category WHERE name = 'JavaScript' AND type = 'COURSE'), 1),
('cr_r_1_11', 1, (SELECT id FROM tb_category WHERE name = 'Vue.js' AND type = 'COURSE'), 1),
('cr_r_1_12', 1, (SELECT id FROM tb_category WHERE name = 'React' AND type = 'COURSE'), 1),
('cr_r_1_13', 1, (SELECT id FROM tb_category WHERE name = '前端工程化' AND type = 'COURSE'), 1),

-- 后端开发(id=2) -> 其子分类
('cr_r_2_14', 2, (SELECT id FROM tb_category WHERE name = 'Java' AND type = 'COURSE'), 1),
('cr_r_2_15', 2, (SELECT id FROM tb_category WHERE name = 'Python' AND type = 'COURSE'), 1),
('cr_r_2_16', 2, (SELECT id FROM tb_category WHERE name = 'Go' AND type = 'COURSE'), 1),
('cr_r_2_17', 2, (SELECT id FROM tb_category WHERE name = 'Node.js' AND type = 'COURSE'), 1),
('cr_r_2_18', 2, (SELECT id FROM tb_category WHERE name = 'Spring Boot' AND type = 'COURSE'), 1),

-- 移动开发(id=3) -> 其子分类
('cr_r_3_19', 3, (SELECT id FROM tb_category WHERE name = 'Android' AND type = 'COURSE'), 1),
('cr_r_3_20', 3, (SELECT id FROM tb_category WHERE name = 'iOS' AND type = 'COURSE'), 1),
('cr_r_3_21', 3, (SELECT id FROM tb_category WHERE name = 'Flutter' AND type = 'COURSE'), 1),
('cr_r_3_22', 3, (SELECT id FROM tb_category WHERE name = 'React Native' AND type = 'COURSE'), 1),

-- 数据库(id=4) -> 其子分类
('cr_r_4_23', 4, (SELECT id FROM tb_category WHERE name = 'MySQL' AND type = 'COURSE'), 1),
('cr_r_4_24', 4, (SELECT id FROM tb_category WHERE name = 'Redis' AND type = 'COURSE'), 1),
('cr_r_4_25', 4, (SELECT id FROM tb_category WHERE name = 'MongoDB' AND type = 'COURSE'), 1),
('cr_r_4_26', 4, (SELECT id FROM tb_category WHERE name = 'PostgreSQL' AND type = 'COURSE'), 1),

-- 云计算与运维(id=5) -> 其子分类
('cr_r_5_27', 5, (SELECT id FROM tb_category WHERE name = 'Linux' AND type = 'COURSE'), 1),
('cr_r_5_28', 5, (SELECT id FROM tb_category WHERE name = 'Docker' AND type = 'COURSE'), 1),
('cr_r_5_29', 5, (SELECT id FROM tb_category WHERE name = 'Kubernetes' AND type = 'COURSE'), 1),
('cr_r_5_30', 5, (SELECT id FROM tb_category WHERE name = 'CI/CD' AND type = 'COURSE'), 1),

-- 人工智能(id=6) -> 其子分类
('cr_r_6_31', 6, (SELECT id FROM tb_category WHERE name = '机器学习' AND type = 'COURSE'), 1),
('cr_r_6_32', 6, (SELECT id FROM tb_category WHERE name = '深度学习' AND type = 'COURSE'), 1),
('cr_r_6_33', 6, (SELECT id FROM tb_category WHERE name = '自然语言处理' AND type = 'COURSE'), 1),
('cr_r_6_34', 6, (SELECT id FROM tb_category WHERE name = '计算机视觉' AND type = 'COURSE'), 1),

-- 网络安全(id=7) -> 其子分类
('cr_r_7_35', 7, (SELECT id FROM tb_category WHERE name = 'Web安全' AND type = 'COURSE'), 1),
('cr_r_7_36', 7, (SELECT id FROM tb_category WHERE name = '渗透测试' AND type = 'COURSE'), 1),
('cr_r_7_37', 7, (SELECT id FROM tb_category WHERE name = '密码学' AND type = 'COURSE'), 1),
('cr_r_7_38', 7, (SELECT id FROM tb_category WHERE name = '安全运维' AND type = 'COURSE'), 1),

-- 数据科学与分析(id=8) -> 其子分类
('cr_r_8_39', 8, (SELECT id FROM tb_category WHERE name = '数据分析' AND type = 'COURSE'), 1),
('cr_r_8_40', 8, (SELECT id FROM tb_category WHERE name = '数据可视化' AND type = 'COURSE'), 1),
('cr_r_8_41', 8, (SELECT id FROM tb_category WHERE name = '大数据' AND type = 'COURSE'), 1),
('cr_r_8_42', 8, (SELECT id FROM tb_category WHERE name = '统计学' AND type = 'COURSE'), 1);
