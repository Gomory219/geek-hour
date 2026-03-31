-- 插入教程数据到 tb_tutorial 表
-- 规则：每个课程包含文字型和视频型教程，只有文字教程可以有子教程

-- ==================== HTML5与CSS3零基础入门 (c001) ====================
-- 视频教程
INSERT INTO tb_tutorial (id, title, type, content, video_url, parent_tutorial_id, course_id, likes, view_count, author_id, is_trail, create_time, update_time, is_deleted) VALUES
('t001', 'HTML基础语法视频讲解', 'VIDEO', '本节课将介绍HTML的基本结构、常用标签以及文档类型声明。通过本节课学习，你将能够编写一个基础的HTML页面。', 'https://example.com/videos/html-basic.mp4', NULL, 'c001', 45, 680, 'u001', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t002', 'CSS3选择器与样式实战', 'VIDEO', '深入学习CSS3的各种选择器，包括类选择器、ID选择器、属性选择器等，以及如何应用样式到HTML元素。', 'https://example.com/videos/css3-selector.mp4', NULL, 'c001', 38, 520, 'u001', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t003', 'Flex布局完全指南', 'VIDEO', '全面讲解Flex弹性布局，包括容器属性和项目属性，通过实战案例掌握响应式布局技巧。', 'https://example.com/videos/flex-layout.mp4', NULL, 'c001', 56, 890, 'u001', false, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false);

-- 文字教程（带子教程）
INSERT INTO tb_tutorial (id, title, type, content, video_url, parent_tutorial_id, course_id, likes, view_count, author_id, is_trail, create_time, update_time, is_deleted) VALUES
('t004', 'HTML5标签完全手册', 'TEXT', 'HTML5标签完整参考手册，包含所有常用标签的详细说明和示例代码。', NULL, NULL, 'c001', 78, 1200, 'u001', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t005', '文本标签', 'TEXT', 'HTML文本相关标签详解，包括h1-h6、p、span、strong、em等标签的用法。', NULL, 't004', 'c001', 23, 340, 'u001', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t006', '列表标签', 'TEXT', 'HTML列表标签详解，包括ul无序列表、ol有序列表、dl定义列表的使用方法。', NULL, 't004', 'c001', 18, 280, 'u001', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t007', '表单标签', 'TEXT', 'HTML表单标签详解，包括input、textarea、select、button等表单元素的用法。', NULL, 't004', 'c001', 34, 520, 'u001', false, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t008', '多媒体标签', 'TEXT', 'HTML5多媒体标签详解，包括audio、video、canvas等标签的使用方法。', NULL, 't004', 'c001', 28, 450, 'u001', false, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false);

-- ==================== JavaScript核心语法精讲 (c003) ====================
-- 视频教程
INSERT INTO tb_tutorial (id, title, type, content, video_url, parent_tutorial_id, course_id, likes, view_count, author_id, is_trail, create_time, update_time, is_deleted) VALUES
('t009', 'JavaScript变量与数据类型', 'VIDEO', '深入理解JavaScript中的变量声明（var、let、const）以及基本数据类型和引用数据类型。', 'https://example.com/videos/js-variable.mp4', NULL, 'c003', 89, 1230, 'u002', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t010', 'JavaScript函数与作用域', 'VIDEO', '学习JavaScript函数定义、参数传递、返回值，以及作用域链和闭包的概念。', 'https://example.com/videos/js-function.mp4', NULL, 'c003', 78, 980, 'u002', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t011', 'ES6+新特性详解', 'VIDEO', '全面讲解ES6及后续版本的新特性，包括箭头函数、解构赋值、模板字符串、Promise等。', 'https://example.com/videos/es6-features.mp4', NULL, 'c003', 92, 1450, 'u002', false, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false);

-- 文字教程（带子教程）
INSERT INTO tb_tutorial (id, title, type, content, video_url, parent_tutorial_id, course_id, likes, view_count, author_id, is_trail, create_time, update_time, is_deleted) VALUES
('t012', 'JavaScript数组方法大全', 'TEXT', 'JavaScript数组常用方法完整参考，包含forEach、map、filter、reduce等方法的详细说明。', NULL, NULL, 'c003', 134, 2100, 'u002', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t013', '遍历方法', 'TEXT', '数组遍历方法详解，包括forEach、map、filter、find、some、every等方法的使用。', NULL, 't012', 'c003', 56, 890, 'u002', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t014', '增删改方法', 'TEXT', '数组增删改方法详解，包括push、pop、shift、unshift、splice等方法的使用。', NULL, 't012', 'c003', 45, 680, 'u002', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t015', '转换方法', 'TEXT', '数组转换方法详解，包括join、toString、split、flat、flatMap等方法的使用。', NULL, 't012', 'c003', 38, 560, 'u002', false, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false);

-- ==================== Vue3从入门到实战 (c005) ====================
-- 视频教程
INSERT INTO tb_tutorial (id, title, type, content, video_url, parent_tutorial_id, course_id, likes, view_count, author_id, is_trail, create_time, update_time, is_deleted) VALUES
('t016', 'Vue3项目搭建与基础语法', 'VIDEO', '从零开始搭建Vue3项目，学习Vue3的模板语法、插值表达式、指令等基础内容。', 'https://example.com/videos/vue3-basic.mp4', NULL, 'c005', 123, 2100, 'u003', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t017', 'Vue3组合式API', 'VIDEO', '深入学习Vue3的组合式API，包括setup函数、ref、reactive、computed等核心API的使用。', 'https://example.com/videos/vue3-composition.mp4', NULL, 'c005', 145, 2340, 'u003', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t018', 'Pinia状态管理', 'VIDEO', '学习Vue3官方推荐的状态管理库Pinia，包括Store定义、State、Getters、Actions的使用。', 'https://example.com/videos/pinia-state.mp4', NULL, 'c005', 98, 1560, 'u003', false, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false);

-- 文字教程（带子教程）
INSERT INTO tb_tutorial (id, title, type, content, video_url, parent_tutorial_id, course_id, likes, view_count, author_id, is_trail, create_time, update_time, is_deleted) VALUES
('t019', 'Vue3组件通信完全指南', 'TEXT', 'Vue3组件间通信的完整指南，涵盖props、emit、provide/inject、slot等多种通信方式。', NULL, NULL, 'c005', 167, 2800, 'u003', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t020', 'Props与Emit', 'TEXT', '父子组件通信基础，使用props传递数据，使用emit触发事件。', NULL, 't019', 'c005', 78, 1200, 'u003', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t021', 'Provide与Inject', 'TEXT', '跨层级组件通信，使用provide和inject实现依赖注入。', NULL, 't019', 'c005', 56, 890, 'u003', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t022', '插槽Slot', 'TEXT', '使用插槽实现内容分发，包括默认插槽、具名插槽、作用域插槽。', NULL, 't019', 'c005', 67, 1050, 'u003', false, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false);

-- ==================== Java零基础到精通 (c010) ====================
-- 视频教程
INSERT INTO tb_tutorial (id, title, type, content, video_url, parent_tutorial_id, course_id, likes, view_count, author_id, is_trail, create_time, update_time, is_deleted) VALUES
('t023', 'Java基础语法入门', 'VIDEO', '学习Java的基本语法，包括变量、数据类型、运算符、流程控制等基础知识。', 'https://example.com/videos/java-basic.mp4', NULL, 'c010', 156, 3200, 'u004', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t024', 'Java面向对象编程', 'VIDEO', '深入理解Java的面向对象特性，包括类、对象、封装、继承、多态等核心概念。', 'https://example.com/videos/java-oop.mp4', NULL, 'c010', 134, 2680, 'u004', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t025', 'Java集合框架详解', 'VIDEO', '全面讲解Java集合框架，包括List、Set、Map等接口及其实现类的使用。', 'https://example.com/videos/java-collection.mp4', NULL, 'c010', 112, 1890, 'u004', false, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false);

-- 文字教程（带子教程）
INSERT INTO tb_tutorial (id, title, type, content, video_url, parent_tutorial_id, course_id, likes, view_count, author_id, is_trail, create_time, update_time, is_deleted) VALUES
('t026', 'Java异常处理体系', 'TEXT', 'Java异常处理的完整指南，包括异常类型、try-catch-finally、throw、throws等关键字的使用。', NULL, NULL, 'c010', 145, 2300, 'u004', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t027', '异常类型体系', 'TEXT', 'Java异常类的层次结构，包括Throwable、Error、Exception、RuntimeException等。', NULL, 't026', 'c010', 67, 1050, 'u004', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t028', 'Try-Catch-Finally', 'TEXT', '异常捕获与处理的语法，包括try、catch、finally块的使用规则和最佳实践。', NULL, 't026', 'c010', 89, 1400, 'u004', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t029', '自定义异常', 'TEXT', '如何创建和使用自定义异常类，以及异常处理的设计原则。', NULL, 't026', 'c010', 56, 890, 'u004', false, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false);

-- ==================== Python编程快速上手 (c012) ====================
-- 视频教程
INSERT INTO tb_tutorial (id, title, type, content, video_url, parent_tutorial_id, course_id, likes, view_count, author_id, is_trail, create_time, update_time, is_deleted) VALUES
('t030', 'Python环境搭建与基础语法', 'VIDEO', '从零开始搭建Python开发环境，学习Python的基本语法、变量、运算符等。', 'https://example.com/videos/python-basic.mp4', NULL, 'c012', 189, 3500, 'u005', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t031', 'Python数据结构与算法', 'VIDEO', '学习Python中的列表、元组、字典、集合等数据结构，以及常用算法的实现。', 'https://example.com/videos/python-datastructure.mp4', NULL, 'c012', 167, 2890, 'u005', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t032', 'Python文件操作与异常处理', 'VIDEO', '掌握Python的文件读写操作，以及异常处理机制和日志记录。', 'https://example.com/videos/python-file.mp4', NULL, 'c012', 145, 2340, 'u005', false, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false);

-- 文字教程（带子教程）
INSERT INTO tb_tutorial (id, title, type, content, video_url, parent_tutorial_id, course_id, likes, view_count, author_id, is_trail, create_time, update_time, is_deleted) VALUES
('t033', 'Python装饰器完全指南', 'TEXT', 'Python装饰器的完整教程，从基础语法到高级应用，包括函数装饰器和类装饰器。', NULL, NULL, 'c012', 178, 2900, 'u005', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t034', '装饰器基础', 'TEXT', '装饰器的基本概念和语法，如何定义和使用简单的装饰器。', NULL, 't033', 'c012', 89, 1400, 'u005', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t035', '带参数的装饰器', 'TEXT', '如何创建带参数的装饰器，以及装饰器工厂的使用方法。', NULL, 't033', 'c012', 78, 1200, 'u005', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t036', '装饰器高级应用', 'TEXT', '装饰器的高级用法，包括类装饰器、装饰器叠加、functools模块等。', NULL, 't033', 'c012', 67, 1050, 'u005', false, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false);

-- ==================== MySQL从入门到调优 (c023) ====================
-- 视频教程
INSERT INTO tb_tutorial (id, title, type, content, video_url, parent_tutorial_id, course_id, likes, view_count, author_id, is_trail, create_time, update_time, is_deleted) VALUES
('t037', 'MySQL安装与基础配置', 'VIDEO', '学习如何在Windows和Linux系统上安装MySQL数据库，并进行基础配置。', 'https://example.com/videos/mysql-install.mp4', NULL, 'c023', 134, 2100, 'u006', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t038', 'SQL语句基础查询', 'VIDEO', '全面讲解SQL基础查询语句，包括SELECT、WHERE、ORDER BY、LIMIT等子句的使用。', 'https://example.com/videos/sql-basic.mp4', NULL, 'c023', 156, 2890, 'u006', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t039', 'MySQL索引原理与优化', 'VIDEO', '深入理解MySQL索引的数据结构、索引类型，以及如何进行SQL查询优化。', 'https://example.com/videos/mysql-index.mp4', NULL, 'c023', 178, 3200, 'u006', false, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false);

-- 文字教程（带子教程）
INSERT INTO tb_tutorial (id, title, type, content, video_url, parent_tutorial_id, course_id, likes, view_count, author_id, is_trail, create_time, update_time, is_deleted) VALUES
('t040', 'SQL连接查询完全指南', 'TEXT', 'SQL连接查询的完整教程，包括内连接、外连接、自连接等各种连接方式。', NULL, NULL, 'c023', 189, 3100, 'u006', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t041', '内连接', 'TEXT', 'INNER JOIN内连接的使用方法，包括等值连接、自然连接等。', NULL, 't040', 'c023', 89, 1400, 'u006', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t042', '外连接', 'TEXT', 'LEFT JOIN、RIGHT JOIN、FULL OUTER JOIN外连接的使用场景和语法。', NULL, 't040', 'c023', 98, 1560, 'u006', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t043', '自连接与交叉连接', 'TEXT', '自连接和交叉连接的使用方法，以及在实际场景中的应用。', NULL, 't040', 'c023', 78, 1200, 'u006', false, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false);

-- ==================== Spring Boot3实战教程 (c017) ====================
-- 视频教程
INSERT INTO tb_tutorial (id, title, type, content, video_url, parent_tutorial_id, course_id, likes, view_count, author_id, is_trail, create_time, update_time, is_deleted) VALUES
('t044', 'Spring Boot快速入门', 'VIDEO', '从零开始创建Spring Boot项目，学习自动配置原理和starter依赖管理。', 'https://example.com/videos/springboot-basic.mp4', NULL, 'c017', 145, 2560, 'u007', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t045', 'Spring Boot数据访问', 'VIDEO', '学习Spring Boot如何整合JPA、MyBatis进行数据访问操作。', 'https://example.com/videos/springboot-data.mp4', NULL, 'c017', 123, 1980, 'u007', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t046', 'Spring Boot安全认证', 'VIDEO', '使用Spring Security实现用户认证和授权，包括JWT令牌的使用。', 'https://example.com/videos/springboot-security.mp4', NULL, 'c017', 98, 1560, 'u007', false, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false);

-- 文字教程（带子教程）
INSERT INTO tb_tutorial (id, title, type, content, video_url, parent_tutorial_id, course_id, likes, view_count, author_id, is_trail, create_time, update_time, is_deleted) VALUES
('t047', 'Spring Boot自动配置原理', 'TEXT', '深入理解Spring Boot的自动配置机制，包括@EnableAutoConfiguration、条件注解等核心原理。', NULL, NULL, 'c017', 134, 2100, 'u007', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t048', '自动配置注解', 'TEXT', 'Spring Boot自动配置相关注解详解，包括@Conditional系列注解的使用。', NULL, 't047', 'c017', 67, 1050, 'u007', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t049', '自定义Starter', 'TEXT', '如何创建自定义的Spring Boot Starter，实现模块化的自动配置。', NULL, 't047', 'c017', 89, 1400, 'u007', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t050', '配置文件加载', 'TEXT', 'Spring Boot配置文件的加载顺序和优先级，包括application.properties和YAML的使用。', NULL, 't047', 'c017', 78, 1200, 'u007', false, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false);

-- ==================== Docker容器化部署实战 (c029) ====================
-- 视频教程
INSERT INTO tb_tutorial (id, title, type, content, video_url, parent_tutorial_id, course_id, likes, view_count, author_id, is_trail, create_time, update_time, is_deleted) VALUES
('t051', 'Docker核心概念', 'VIDEO', '学习Docker的核心概念，包括镜像、容器、仓库等，理解容器化技术的优势。', 'https://example.com/videos/docker-concept.mp4', NULL, 'c029', 112, 1890, 'u008', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t052', 'Docker镜像管理', 'VIDEO', '学习如何拉取、构建、推送Docker镜像，以及Dockerfile的编写技巧。', 'https://example.com/videos/docker-image.mp4', NULL, 'c029', 134, 2230, 'u008', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t053', 'Docker Compose容器编排', 'VIDEO', '使用Docker Compose编排多容器应用，学习服务定义和网络配置。', 'https://example.com/videos/docker-compose.mp4', NULL, 'c029', 145, 2560, 'u008', false, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false);

-- 文字教程（带子教程）
INSERT INTO tb_tutorial (id, title, type, content, video_url, parent_tutorial_id, course_id, likes, view_count, author_id, is_trail, create_time, update_time, is_deleted) VALUES
('t054', 'Dockerfile最佳实践', 'TEXT', '编写高效Dockerfile的最佳实践指南，包括镜像优化、多阶段构建等技巧。', NULL, NULL, 'c029', 123, 1900, 'u008', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t055', '基础指令', 'TEXT', 'Dockerfile基础指令详解，包括FROM、RUN、COPY、ADD、CMD、ENTRYPOINT等。', NULL, 't054', 'c029', 78, 1200, 'u008', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t056', '优化技巧', 'TEXT', 'Dockerfile优化技巧，包括构建缓存、镜像分层、多阶段构建等。', NULL, 't054', 'c029', 89, 1400, 'u008', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t057', '安全实践', 'TEXT', 'Docker镜像安全最佳实践，包括最小化镜像、用户权限、漏洞扫描等。', NULL, 't054', 'c029', 67, 1050, 'u008', false, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false);

-- ==================== React18全面解析 (c007) ====================
-- 视频教程
INSERT INTO tb_tutorial (id, title, type, content, video_url, parent_tutorial_id, course_id, likes, view_count, author_id, is_trail, create_time, update_time, is_deleted) VALUES
('t058', 'React核心概念', 'VIDEO', '学习React的核心概念，包括组件、JSX、Props、State等基础知识。', 'https://example.com/videos/react-concept.mp4', NULL, 'c007', 134, 2230, 'u009', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t059', 'React Hooks详解', 'VIDEO', '深入学习React Hooks，包括useState、useEffect、useContext等常用Hooks的使用。', 'https://example.com/videos/react-hooks.mp4', NULL, 'c007', 156, 2890, 'u009', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t060', 'React18新特性', 'VIDEO', '全面讲解React18的新特性，包括并发模式、自动批处理、Suspense改进等。', 'https://example.com/videos/react18-new.mp4', NULL, 'c007', 123, 2100, 'u009', false, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false);

-- 文字教程（带子教程）
INSERT INTO tb_tutorial (id, title, type, content, video_url, parent_tutorial_id, course_id, likes, view_count, author_id, is_trail, create_time, update_time, is_deleted) VALUES
('t061', 'React性能优化完全指南', 'TEXT', 'React应用性能优化的完整指南，包括组件优化、状态优化、渲染优化等方面。', NULL, NULL, 'c007', 145, 2300, 'u009', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t062', '组件优化', 'TEXT', '使用React.memo、useMemo、useCallback等API优化组件性能。', NULL, 't061', 'c007', 89, 1400, 'u009', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t063', '代码分割', 'TEXT', '使用React.lazy和Suspense实现代码分割和懒加载，优化应用初始加载性能。', NULL, 't061', 'c007', 78, 1200, 'u009', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t064', '渲染优化', 'TEXT', '避免不必要的重渲染，使用虚拟列表、防抖节流等技术优化渲染性能。', NULL, 't061', 'c007', 67, 1050, 'u009', false, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false);

-- ==================== 机器学习算法与实践 (c032) ====================
-- 视频教程
INSERT INTO tb_tutorial (id, title, type, content, video_url, parent_tutorial_id, course_id, likes, view_count, author_id, is_trail, create_time, update_time, is_deleted) VALUES
('t065', '机器学习基础概念', 'VIDEO', '介绍机器学习的基本概念、分类、应用场景，以及常用的机器学习算法。', 'https://example.com/videos/ml-concept.mp4', NULL, 'c032', 178, 3200, 'u010', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t066', '线性回归与逻辑回归', 'VIDEO', '深入讲解线性回归和逻辑回归算法的原理，以及使用Scikit-learn实现。', 'https://example.com/videos/ml-regression.mp4', NULL, 'c032', 156, 2680, 'u010', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t067', '决策树与随机森林', 'VIDEO', '学习决策树算法的构建过程，以及集成学习中的随机森林算法。', 'https://example.com/videos/ml-tree.mp4', NULL, 'c032', 145, 2340, 'u010', false, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false);

-- 文字教程（带子教程）
INSERT INTO tb_tutorial (id, title, type, content, video_url, parent_tutorial_id, course_id, likes, view_count, author_id, is_trail, create_time, update_time, is_deleted) VALUES
('t068', 'Scikit-learn完全指南', 'TEXT', 'Scikit-learn机器学习库的完整使用指南，包括数据预处理、模型训练、评估等。', NULL, NULL, 'c032', 167, 2700, 'u010', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t069', '数据预处理', 'TEXT', '使用Scikit-learn进行数据预处理，包括标准化、归一化、编码等操作。', NULL, 't068', 'c032', 89, 1400, 'u010', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t070', '模型训练与评估', 'TEXT', '使用fit、predict等方法训练模型，以及使用各种指标评估模型性能。', NULL, 't068', 'c032', 98, 1560, 'u010', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t071', '模型调优', 'TEXT', '使用网格搜索、随机搜索等方法进行超参数调优，以及交叉验证的使用。', NULL, 't068', 'c032', 78, 1200, 'u010', false, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false);

-- ==================== 大数据Spark实战教程 (c042) ====================
-- 视频教程
INSERT INTO tb_tutorial (id, title, type, content, video_url, parent_tutorial_id, course_id, likes, view_count, author_id, is_trail, create_time, update_time, is_deleted) VALUES
('t072', 'Spark Core核心编程', 'VIDEO', '学习Spark Core的核心概念，包括RDD、算子操作、持久化等。', 'https://example.com/videos/spark-core.mp4', NULL, 'c042', 89, 1456, 'u011', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t073', 'Spark SQL数据分析', 'VIDEO', '使用Spark SQL进行结构化数据处理，学习DataFrame和DataSet API。', 'https://example.com/videos/spark-sql.mp4', NULL, 'c042', 78, 1234, 'u011', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t074', 'Spark Streaming实时计算', 'VIDEO', '学习Spark Streaming实时流处理技术，包括DStream和Structured Streaming。', 'https://example.com/videos/spark-streaming.mp4', NULL, 'c042', 67, 987, 'u011', false, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false);

-- 文字教程（带子教程）
INSERT INTO tb_tutorial (id, title, type, content, video_url, parent_tutorial_id, course_id, likes, view_count, author_id, is_trail, create_time, update_time, is_deleted) VALUES
('t075', 'Spark RDD完全指南', 'TEXT', 'Spark RDD的完整使用指南，包括RDD创建、转换操作、行动操作等。', NULL, NULL, 'c042', 78, 1200, 'u011', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t076', 'RDD创建', 'TEXT', '如何创建RDD，包括从内存中创建、从外部存储创建等不同方式。', NULL, 't075', 'c042', 45, 680, 'u011', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t077', '转换算子', 'TEXT', 'RDD转换算子详解，包括map、flatMap、filter、reduceByKey等常用算子。', NULL, 't075', 'c042', 56, 890, 'u011', true, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false),
('t078', '行动算子', 'TEXT', 'RDD行动算子详解，包括collect、count、reduce、foreach等常用算子。', NULL, 't075', 'c042', 48, 750, 'u011', false, '2026-03-31 10:00:00', '2026-03-31 10:00:00', false);

-- ==================== 数据统计 ====================
-- 总共78条教程数据：
-- - 视频教程：33条（每个课程3条视频教程）
-- - 文字教程：45条（每个课程1个父教程 + 4个子教程）
--
-- 课程分布：
-- 1. c001: HTML5与CSS3 (t001-t008) - 3视频 + 1父文字 + 4子文字
-- 2. c003: JavaScript (t009-t015) - 3视频 + 1父文字 + 4子文字
-- 3. c005: Vue3 (t016-t022) - 3视频 + 1父文字 + 4子文字
-- 4. c010: Java (t023-t029) - 3视频 + 1父文字 + 4子文字
-- 5. c012: Python (t030-t036) - 3视频 + 1父文字 + 4子文字
-- 6. c023: MySQL (t037-t043) - 3视频 + 1父文字 + 4子文字
-- 7. c017: Spring Boot (t044-t050) - 3视频 + 1父文字 + 4子文字
-- 8. c029: Docker (t051-t057) - 3视频 + 1父文字 + 4子文字
-- 9. c007: React (t058-t064) - 3视频 + 1父文字 + 4子文字
-- 10. c032: 机器学习 (t065-t071) - 3视频 + 1父文字 + 4子文字
-- 11. c042: Spark (t072-t078) - 3视频 + 1父文字 + 4子文字
--
-- 规则说明：
-- - type='VIDEO' 表示视频教程，不能有子教程
-- - type='TEXT' 表示文字教程，可以有子教程
-- - parent_tutorial_id=NULL 表示顶级教程
-- - parent_tutorial_id不为NULL 表示是子教程
-- - is_trail=true 表示试看教程（每个课程前3个教程设为试看）
