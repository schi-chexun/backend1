# EasyActivity 校园活动管理系统

一个基于Spring Boot + MyBatis的校园活动管理系统后端项目。

## 技术栈

- **框架**: Spring Boot 3.2.0
- **数据库**: MySQL 8.0+
- **ORM**: MyBatis 3.0.3
- **Java版本**: 17
- **构建工具**: Maven

## 项目结构

```
src/main/java/com/easyactivity/
├── EasyActivityApplication.java    # 主启动类
├── common/                          # 通用类
│   ├── Result.java                  # 统一响应结果
│   └── GlobalExceptionHandler.java  # 全局异常处理
├── controller/                      # 控制器层
│   ├── ActivityController.java      # 活动控制器
│   ├── UserController.java          # 用户控制器
│   └── ActivityParticipantController.java  # 参与者控制器
├── service/                         # 服务层
│   ├── ActivityService.java         # 活动服务
│   ├── UserService.java             # 用户服务
│   └── ActivityParticipantService.java  # 参与者服务
├── mapper/                          # Mapper接口
│   ├── ActivityMapper.java
│   ├── UserMapper.java
│   └── ActivityParticipantMapper.java
└── entity/                          # 实体类
    ├── Activity.java                # 活动实体
    ├── User.java                    # 用户实体
    └── ActivityParticipant.java     # 参与者实体

src/main/resources/
├── application.properties           # 应用配置
├── mapper/                          # MyBatis XML映射文件
│   ├── ActivityMapper.xml
│   ├── UserMapper.xml
│   └── ActivityParticipantMapper.xml
└── sql/
    └── schema.sql                   # 数据库初始化脚本
```

## 快速开始

### 1. 环境要求

- JDK 17+
- Maven 3.6+
- MySQL 8.0+

### 2. 数据库配置

1. 创建MySQL数据库（或使用schema.sql自动创建）
2. 修改 `src/main/resources/application.properties` 中的数据库连接信息：

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/easy_activity?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=root
```

### 3. 初始化数据库

执行 `src/main/resources/sql/schema.sql` 脚本创建表结构和测试数据。

### 4. 运行项目

```bash
# 使用Maven运行
mvn spring-boot:run

# 或者打包后运行
mvn clean package
java -jar target/easy-activity-backend-0.0.1-SNAPSHOT.jar
```

### 5. 访问API

项目启动后，API基础路径为：`http://localhost:8080/api`

## API接口文档

### 活动相关接口

- `GET /api/activities` - 获取所有活动
- `GET /api/activities/{id}` - 根据ID获取活动
- `GET /api/activities/status/{status}` - 根据状态获取活动列表
- `POST /api/activities` - 创建活动
- `PUT /api/activities/{id}` - 更新活动
- `DELETE /api/activities/{id}` - 删除活动
- `PUT /api/activities/{id}/status` - 更新活动状态

### 用户相关接口

- `GET /api/users/{id}` - 根据ID获取用户
- `POST /api/users/register` - 用户注册
- `PUT /api/users/{id}` - 更新用户信息
- `DELETE /api/users/{id}` - 删除用户

### 参与者相关接口

- `POST /api/participants/register?activityId={id}&userId={id}` - 报名参加活动
- `DELETE /api/participants/cancel?activityId={id}&userId={id}` - 取消报名
- `POST /api/participants/checkin?activityId={id}&userId={id}` - 签到
- `GET /api/participants/activity/{activityId}` - 获取活动的参与者列表
- `GET /api/participants/user/{userId}` - 获取用户参与的活动列表

## 测试数据

数据库初始化脚本包含以下测试数据：

- **管理员用户**: username: `admin`, password: `admin123`
- **普通用户**: username: `user1`, password: `user123`
- **普通用户**: username: `user2`, password: `user123`

## 注意事项

1. 确保MySQL服务已启动
2. 确保数据库连接配置正确
3. 首次运行前需要执行数据库初始化脚本
4. 密码字段在实际生产环境中应该加密存储

## 开发说明

- 统一使用 `Result<T>` 作为API响应格式
- 异常统一由 `GlobalExceptionHandler` 处理
- 数据库字段使用下划线命名，Java实体使用驼峰命名，MyBatis自动转换

