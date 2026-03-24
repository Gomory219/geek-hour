CREATE OR REPLACE FUNCTION set_update_time()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.update_time = NOW(); -- 把更新时间设为当前时间
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TABLE tb_user (
                         id VARCHAR(32) PRIMARY KEY,
                         account VARCHAR(32) NOT NULL UNIQUE,
                         user_name VARCHAR(32) NOT NULL,
                         password VARCHAR(255) NOT NULL,
                         avatar_image VARCHAR(255),
                         phone VARCHAR(32),
                         email VARCHAR(255),
                         profile TEXT,
                         role VARCHAR(32) DEFAULT 'user',
                         create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         is_deleted BOOLEAN DEFAULT FALSE
);

COMMENT ON TABLE tb_user IS '用户表';
COMMENT ON COLUMN tb_user.id IS '用户ID，主键';
COMMENT ON COLUMN tb_user.account IS '账号，唯一';
COMMENT ON COLUMN tb_user.user_name IS '用户名';
COMMENT ON COLUMN tb_user.password IS '密码，bcrypt哈希存储';
COMMENT ON COLUMN tb_user.avatar_image IS '头像URL';
COMMENT ON COLUMN tb_user.phone IS '手机号';
COMMENT ON COLUMN tb_user.email IS '邮箱';
COMMENT ON COLUMN tb_user.profile IS '个人简介';
COMMENT ON COLUMN tb_user.role IS '角色：user-普通用户，admin-管理员，vip-VIP用户';
COMMENT ON COLUMN tb_user.create_time IS '创建时间';
COMMENT ON COLUMN tb_user.update_time IS '更新时间，自动更新';
COMMENT ON COLUMN tb_user.is_deleted IS '是否删除，默认False';

CREATE TRIGGER trigger_tb_user_update_time
    BEFORE UPDATE ON tb_user
    FOR EACH ROW
    EXECUTE FUNCTION set_update_time();

CREATE TABLE tb_vip (
                        id VARCHAR(32) PRIMARY KEY,
                        user_id VARCHAR(32),
                        start_time TIMESTAMP,
                        end_time TIMESTAMP,
                        is_active BOOLEAN DEFAULT FALSE,
                        create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        is_deleted BOOLEAN DEFAULT FALSE
);

CREATE TABLE tb_course (
                           id VARCHAR(32) PRIMARY KEY,
                           category_id INTEGER,
                           name VARCHAR(32) NOT NULL,
                           price NUMERIC(10, 2),
                           cover_image VARCHAR(255),
                           profile TEXT,
                           introduction TEXT,
                           likes INTEGER DEFAULT 0,
                           view_count INTEGER DEFAULT 0,
                           is_free BOOLEAN DEFAULT FALSE,
                           is_vip BOOLEAN DEFAULT FALSE,
                           is_feature BOOLEAN DEFAULT FALSE,
                           level VARCHAR(32),
                           create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           is_deleted BOOLEAN DEFAULT FALSE
);

COMMENT ON COLUMN tb_course.id IS '课程ID';
COMMENT ON COLUMN tb_course.name IS '课程名称';
COMMENT ON COLUMN tb_course.price IS '课程价格';
COMMENT ON COLUMN tb_course.cover_image IS '课程封面图片地址';
COMMENT ON COLUMN tb_course.profile IS '课程简介';
COMMENT ON COLUMN tb_course.introduction IS '课程详情介绍';
COMMENT ON COLUMN tb_course.likes IS '点赞数';
COMMENT ON COLUMN tb_course.view_count IS '浏览量';
COMMENT ON COLUMN tb_course.is_free IS '是否免费：true=是，false=否';
COMMENT ON COLUMN tb_course.is_vip IS '是否VIP专属：true=是，false=否';
COMMENT ON COLUMN tb_course.is_feature IS '是否推荐/精选：true=是，false=否';
COMMENT ON COLUMN tb_course.create_time IS '创建时间';
COMMENT ON COLUMN tb_course.update_time IS '更新时间';
COMMENT ON COLUMN tb_course.is_deleted IS '是否删除（软删除）：true=已删除';

CREATE TRIGGER trigger_tb_course_update
    BEFORE UPDATE ON tb_course
    FOR EACH ROW
    EXECUTE FUNCTION set_update_time();


CREATE TABLE tb_category (
                             id SERIAL PRIMARY KEY,
                             name VARCHAR(100),
                             type VARCHAR(32) NOT NULL,
                             create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             is_deleted BOOLEAN DEFAULT FALSE
);

CREATE TABLE tb_category_relation (
                                      ancestor BIGINT NOT NULL,
                                      descendant BIGINT NOT NULL,
                                      depth INT,
                                      PRIMARY KEY (ancestor, descendant)
);

CREATE TABLE tb_tutorial (
                             id VARCHAR(32) PRIMARY KEY,
                             name VARCHAR(32) NOT NULL,
                             content TEXT,
                             video_url VARCHAR(255),
                             parent_tutorial_id VARCHAR(32),
                             course_id VARCHAR(32) NOT NULL,
                             likes INTEGER DEFAULT 0,
                             view_count INTEGER DEFAULT 0,
                             author_id VARCHAR(32),
                             is_trail BOOLEAN DEFAULT FALSE,
                             create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             is_deleted BOOLEAN DEFAULT FALSE
);

CREATE TABLE tb_user_course (
                                id VARCHAR(32) PRIMARY KEY,
                                user_id VARCHAR(32),
                                course_id VARCHAR(32),
                                begin_time TIMESTAMP,
                                end_time TIMESTAMP,
                                create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                is_deleted BOOLEAN DEFAULT FALSE
);

CREATE TABLE tb_user_tutorial(
                                 id VARCHAR(32) PRIMARY KEY,
                                 user_id VARCHAR(32),
                                 tutorial_id VARCHAR(32),
                                 progress INTEGER DEFAULT 0,
                                 is_started BOOLEAN DEFAULT FALSE,
                                 is_completed BOOLEAN DEFAULT FALSE,
                                 create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                 update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                 is_deleted BOOLEAN DEFAULT FALSE
);


CREATE TABLE tb_like(
                        id VARCHAR(32) PRIMARY KEY,
                        user_id VARCHAR(32),
                        biz_type VARCHAR(32),
                        biz_id VARCHAR(32),
                        create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        is_deleted BOOLEAN DEFAULT FALSE
);


CREATE TABLE tb_comment(
                           id VARCHAR(32) PRIMARY KEY,
                           user_id VARCHAR(32),
                           biz_type VARCHAR(32),
                           biz_id VARCHAR(32),
                           parent_id VARCHAR(32),
                           reply_user_id VARCHAR(32),
                           conversation_id VARCHAR(32),
                           is_markdown BOOLEAN DEFAULT FALSE,
                           content TEXT,
                           likes INTEGER DEFAULT 0,
                           create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           is_deleted BOOLEAN DEFAULT FALSE
);


CREATE TABLE tb_user_certificate (
                                     id VARCHAR(32) PRIMARY KEY,
                                     user_id VARCHAR(32),
                                     course_id VARCHAR(32),
                                     course_name VARCHAR(255),
                                     course_cover_image VARCHAR(255),
                                     time_taken_description VARCHAR(32) ,
                                     create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                     update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                     is_deleted BOOLEAN DEFAULT FALSE
);


























