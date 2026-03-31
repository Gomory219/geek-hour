CREATE OR REPLACE FUNCTION set_update_time()
    RETURNS TRIGGER AS
$$
BEGIN
    NEW.update_time = NOW(); -- 把更新时间设为当前时间
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TABLE tb_user
(
    id           VARCHAR(32) PRIMARY KEY,
    account      VARCHAR(32)  NOT NULL UNIQUE,
    user_name    VARCHAR(32)  NOT NULL,
    password     VARCHAR(255) NOT NULL,
    avatar_image VARCHAR(255),
    phone        VARCHAR(32),
    email        VARCHAR(255),
    profile      TEXT,
    role         VARCHAR(32) DEFAULT 'user',
    create_time  TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,
    update_time  TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,
    is_deleted   BOOLEAN     DEFAULT FALSE
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
    BEFORE UPDATE
    ON tb_user
    FOR EACH ROW
EXECUTE FUNCTION set_update_time();

CREATE TABLE tb_vip
(
    id          VARCHAR(32) PRIMARY KEY,
    user_id     VARCHAR(32),
    start_time  TIMESTAMP,
    end_time    TIMESTAMP,
    is_active   BOOLEAN   DEFAULT FALSE,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted  BOOLEAN   DEFAULT FALSE
);

COMMENT ON TABLE tb_vip IS 'VIP会员表';
COMMENT ON COLUMN tb_vip.id IS 'VIP记录ID，主键';
COMMENT ON COLUMN tb_vip.user_id IS '用户ID，关联tb_user.id';
COMMENT ON COLUMN tb_vip.start_time IS 'VIP开始时间';
COMMENT ON COLUMN tb_vip.end_time IS 'VIP结束时间';
COMMENT ON COLUMN tb_vip.is_active IS '是否激活：true=激活中，false=未激活/已过期';
COMMENT ON COLUMN tb_vip.create_time IS '创建时间';
COMMENT ON COLUMN tb_vip.update_time IS '更新时间，自动更新';
COMMENT ON COLUMN tb_vip.is_deleted IS '是否删除（软删除）：true=已删除';

CREATE TRIGGER trigger_tb_vip_update_time
    BEFORE UPDATE
    ON tb_vip
    FOR EACH ROW
EXECUTE FUNCTION set_update_time();

CREATE TABLE tb_course
(
    id           VARCHAR(32) PRIMARY KEY,
    category_id  INTEGER,
    name         VARCHAR(32) NOT NULL,
    price        NUMERIC(10, 2),
    cover_image  VARCHAR(255),
    profile      TEXT,
    introduction TEXT,
    likes        INTEGER   DEFAULT 0,
    view_count   INTEGER   DEFAULT 0,
    is_free      BOOLEAN   DEFAULT FALSE,
    is_vip       BOOLEAN   DEFAULT FALSE,
    is_feature   BOOLEAN   DEFAULT FALSE,
    level        VARCHAR(32),
    create_time  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted   BOOLEAN   DEFAULT FALSE
);

COMMENT ON TABLE tb_course IS '课程表';
COMMENT ON COLUMN tb_course.id IS '课程ID';
COMMENT ON COLUMN tb_course.category_id IS '分类ID，关联tb_category.id';
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
COMMENT ON COLUMN tb_course.level IS '课程难度：beginner-入门，intermediate-进阶，advanced-高级';
COMMENT ON COLUMN tb_course.create_time IS '创建时间';
COMMENT ON COLUMN tb_course.update_time IS '更新时间，自动更新';
COMMENT ON COLUMN tb_course.is_deleted IS '是否删除（软删除）：true=已删除';

CREATE TRIGGER trigger_tb_course_update
    BEFORE UPDATE
    ON tb_course
    FOR EACH ROW
EXECUTE FUNCTION set_update_time();


CREATE TABLE tb_category
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(100),
    type        VARCHAR(32) NOT NULL,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted  BOOLEAN   DEFAULT FALSE
);

COMMENT ON TABLE tb_category IS '分类表';
COMMENT ON COLUMN tb_category.id IS '分类ID，主键，自增';
COMMENT ON COLUMN tb_category.name IS '分类名称';
COMMENT ON COLUMN tb_category.type IS '分类类型：course-课程分类，tutorial-教程分类';
COMMENT ON COLUMN tb_category.create_time IS '创建时间';
COMMENT ON COLUMN tb_category.update_time IS '更新时间，自动更新';
COMMENT ON COLUMN tb_category.is_deleted IS '是否删除（软删除）：true=已删除';

CREATE TRIGGER trigger_tb_category_update_time
    BEFORE UPDATE
    ON tb_category
    FOR EACH ROW
EXECUTE FUNCTION set_update_time();

DROP TABLE IF EXISTS tb_category_relation;

CREATE TABLE tb_category_relation
(
    id         VARCHAR(32) PRIMARY KEY,
    ancestor   BIGINT NOT NULL,
    descendant BIGINT NOT NULL,
    depth      INT,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted  BOOLEAN   DEFAULT FALSE
);

COMMENT ON COLUMN tb_category_relation.id IS '分类关系ID';
COMMENT ON TABLE tb_category_relation IS '分类关系表（闭包表，存储树形结构）';
COMMENT ON COLUMN tb_category_relation.ancestor IS '祖先节点ID，关联tb_category.id';
COMMENT ON COLUMN tb_category_relation.descendant IS '子孙节点ID，关联tb_category.id';
COMMENT ON COLUMN tb_category_relation.depth IS '层级深度：0表示自己，1表示直接子节点，以此类推';
COMMENT ON COLUMN tb_category_relation.create_time IS '创建时间';
COMMENT ON COLUMN tb_category_relation.update_time IS '更新时间，自动更新';
COMMENT ON COLUMN tb_category_relation.is_deleted IS '是否删除（软删除）：true=已删除';



CREATE TRIGGER trigger_tb_category_relation_update_time
    BEFORE UPDATE
    ON tb_category_relation
    FOR EACH ROW
EXECUTE FUNCTION set_update_time();

CREATE TABLE tb_tutorial
(
    id                 VARCHAR(32) PRIMARY KEY,
    title               VARCHAR(32) NOT NULL,
    type               VARCHAR(32) NOT NULL,
    content            TEXT,
    video_url          VARCHAR(255),
    parent_tutorial_id VARCHAR(32),
    course_id          VARCHAR(32) NOT NULL,
    likes              INTEGER   DEFAULT 0,
    view_count         INTEGER   DEFAULT 0,
    author_id          VARCHAR(32),
    is_trail           BOOLEAN   DEFAULT FALSE,
    create_time        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted         BOOLEAN   DEFAULT FALSE
);

COMMENT ON TABLE tb_tutorial IS '教程表（课程章节）';
COMMENT ON COLUMN tb_tutorial.id IS '教程ID，主键';
COMMENT ON COLUMN tb_tutorial.type IS '教程类型：video-视频教程，article-文章教程';
COMMENT ON COLUMN tb_tutorial.name IS '教程名称';
COMMENT ON COLUMN tb_tutorial.content IS '教程内容（markdown格式）';
COMMENT ON COLUMN tb_tutorial.video_url IS '视频地址URL';
COMMENT ON COLUMN tb_tutorial.parent_tutorial_id IS '父教程ID，用于构建章节层级结构';
COMMENT ON COLUMN tb_tutorial.course_id IS '所属课程ID，关联tb_course.id';
COMMENT ON COLUMN tb_tutorial.likes IS '点赞数';
COMMENT ON COLUMN tb_tutorial.view_count IS '浏览量';
COMMENT ON COLUMN tb_tutorial.author_id IS '作者ID，关联tb_user.id';
COMMENT ON COLUMN tb_tutorial.is_trail IS '是否试看：true=可试看，false=需购买/VIP';
COMMENT ON COLUMN tb_tutorial.create_time IS '创建时间';
COMMENT ON COLUMN tb_tutorial.update_time IS '更新时间，自动更新';
COMMENT ON COLUMN tb_tutorial.is_deleted IS '是否删除（软删除）：true=已删除';

CREATE TRIGGER trigger_tb_tutorial_update_time
    BEFORE UPDATE
    ON tb_tutorial
    FOR EACH ROW
EXECUTE FUNCTION set_update_time();

CREATE TABLE tb_user_course
(
    id          VARCHAR(32) PRIMARY KEY,
    user_id     VARCHAR(32),
    course_id   VARCHAR(32),
    begin_time  TIMESTAMP,
    end_time    TIMESTAMP,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted  BOOLEAN   DEFAULT FALSE
);

COMMENT ON TABLE tb_user_course IS '用户课程关联表（用户购买/学习的课程）';
COMMENT ON COLUMN tb_user_course.id IS '记录ID，主键';
COMMENT ON COLUMN tb_user_course.user_id IS '用户ID，关联tb_user.id';
COMMENT ON COLUMN tb_user_course.course_id IS '课程ID，关联tb_course.id';
COMMENT ON COLUMN tb_user_course.begin_time IS '开始学习时间';
COMMENT ON COLUMN tb_user_course.end_time IS '结束学习时间/有效期';
COMMENT ON COLUMN tb_user_course.create_time IS '创建时间';
COMMENT ON COLUMN tb_user_course.update_time IS '更新时间，自动更新';
COMMENT ON COLUMN tb_user_course.is_deleted IS '是否删除（软删除）：true=已删除';

CREATE TRIGGER trigger_tb_user_course_update_time
    BEFORE UPDATE
    ON tb_user_course
    FOR EACH ROW
EXECUTE FUNCTION set_update_time();

CREATE TABLE tb_user_tutorial
(
    id           VARCHAR(32) PRIMARY KEY,
    user_id      VARCHAR(32),
    tutorial_id  VARCHAR(32),
    progress     INTEGER   DEFAULT 0,
    is_started   BOOLEAN   DEFAULT FALSE,
    is_completed BOOLEAN   DEFAULT FALSE,
    create_time  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted   BOOLEAN   DEFAULT FALSE
);

COMMENT ON TABLE tb_user_tutorial IS '用户教程学习进度表';
COMMENT ON COLUMN tb_user_tutorial.id IS '记录ID，主键';
COMMENT ON COLUMN tb_user_tutorial.user_id IS '用户ID，关联tb_user.id';
COMMENT ON COLUMN tb_user_tutorial.tutorial_id IS '教程ID，关联tb_tutorial.id';
COMMENT ON COLUMN tb_user_tutorial.progress IS '学习进度百分比：0-100';
COMMENT ON COLUMN tb_user_tutorial.is_started IS '是否开始学习：true=已开始，false=未开始';
COMMENT ON COLUMN tb_user_tutorial.is_completed IS '是否完成学习：true=已完成，false=未完成';
COMMENT ON COLUMN tb_user_tutorial.create_time IS '创建时间';
COMMENT ON COLUMN tb_user_tutorial.update_time IS '更新时间，自动更新';
COMMENT ON COLUMN tb_user_tutorial.is_deleted IS '是否删除（软删除）：true=已删除';

CREATE TRIGGER trigger_tb_user_tutorial_update_time
    BEFORE UPDATE
    ON tb_user_tutorial
    FOR EACH ROW
EXECUTE FUNCTION set_update_time();

CREATE TABLE tb_like
(
    id          VARCHAR(32) PRIMARY KEY,
    user_id     VARCHAR(32),
    biz_type    VARCHAR(32),
    biz_id      VARCHAR(32),
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted  BOOLEAN   DEFAULT FALSE
);

COMMENT ON TABLE tb_like IS '点赞表';
COMMENT ON COLUMN tb_like.id IS '点赞记录ID，主键';
COMMENT ON COLUMN tb_like.user_id IS '用户ID，关联tb_user.id';
COMMENT ON COLUMN tb_like.biz_type IS '业务类型：course-课程，tutorial-教程，comment-评论';
COMMENT ON COLUMN tb_like.biz_id IS '业务对象ID';
COMMENT ON COLUMN tb_like.create_time IS '创建时间（点赞时间）';
COMMENT ON COLUMN tb_like.update_time IS '更新时间，自动更新';
COMMENT ON COLUMN tb_like.is_deleted IS '是否删除（软删除）：true=已取消点赞';

CREATE TRIGGER trigger_tb_like_update_time
    BEFORE UPDATE
    ON tb_like
    FOR EACH ROW
EXECUTE FUNCTION set_update_time();

CREATE TABLE tb_comment
(
    id              VARCHAR(32) PRIMARY KEY,
    user_id         VARCHAR(32),
    biz_type        VARCHAR(32),
    biz_id          VARCHAR(32),
    parent_id       VARCHAR(32),
    reply_user_id   VARCHAR(32),
    conversation_id VARCHAR(32),
    is_markdown     BOOLEAN   DEFAULT FALSE,
    content         TEXT,
    likes           INTEGER   DEFAULT 0,
    create_time     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted      BOOLEAN   DEFAULT FALSE
);

COMMENT ON TABLE tb_comment IS '评论表';
COMMENT ON COLUMN tb_comment.id IS '评论ID，主键';
COMMENT ON COLUMN tb_comment.user_id IS '评论用户ID，关联tb_user.id';
COMMENT ON COLUMN tb_comment.biz_type IS '业务类型：course-课程，tutorial-教程';
COMMENT ON COLUMN tb_comment.biz_id IS '业务对象ID';
COMMENT ON COLUMN tb_comment.parent_id IS '父评论ID（用于回复功能）';
COMMENT ON COLUMN tb_comment.reply_user_id IS '被回复用户ID，关联tb_user.id';
COMMENT ON COLUMN tb_comment.conversation_id IS '会话ID（同一对话的评论共享此ID）';
COMMENT ON COLUMN tb_comment.is_markdown IS '内容是否为markdown格式：true=是，false=否';
COMMENT ON COLUMN tb_comment.content IS '评论内容';
COMMENT ON COLUMN tb_comment.likes IS '点赞数';
COMMENT ON COLUMN tb_comment.create_time IS '创建时间（评论时间）';
COMMENT ON COLUMN tb_comment.update_time IS '更新时间，自动更新';
COMMENT ON COLUMN tb_comment.is_deleted IS '是否删除（软删除）：true=已删除';

CREATE TRIGGER trigger_tb_comment_update_time
    BEFORE UPDATE
    ON tb_comment
    FOR EACH ROW
EXECUTE FUNCTION set_update_time();

CREATE TABLE tb_user_certificate
(
    id                     VARCHAR(32) PRIMARY KEY,
    user_id                VARCHAR(32),
    course_id              VARCHAR(32),
    course_name            VARCHAR(255),
    course_cover_image     VARCHAR(255),
    time_taken_description VARCHAR(32),
    create_time            TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time            TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted             BOOLEAN   DEFAULT FALSE
);

COMMENT ON TABLE tb_user_certificate IS '用户证书表（完成课程后获得的证书）';
COMMENT ON COLUMN tb_user_certificate.id IS '证书ID，主键';
COMMENT ON COLUMN tb_user_certificate.user_id IS '用户ID，关联tb_user.id';
COMMENT ON COLUMN tb_user_certificate.course_id IS '课程ID，关联tb_course.id';
COMMENT ON COLUMN tb_user_certificate.course_name IS '课程名称（冗余字段，便于展示）';
COMMENT ON COLUMN tb_user_certificate.course_cover_image IS '课程封面图片（冗余字段，便于展示）';
COMMENT ON COLUMN tb_user_certificate.time_taken_description IS '完成耗时描述，如"3天完成"';
COMMENT ON COLUMN tb_user_certificate.create_time IS '创建时间（证书颁发时间）';
COMMENT ON COLUMN tb_user_certificate.update_time IS '更新时间，自动更新';
COMMENT ON COLUMN tb_user_certificate.is_deleted IS '是否删除（软删除）：true=已删除';

CREATE TRIGGER trigger_tb_user_certificate_update_time
    BEFORE UPDATE
    ON tb_user_certificate
    FOR EACH ROW
EXECUTE FUNCTION set_update_time();

