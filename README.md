# EasyActivity 校园活动管理系统 - 后端工程
> 基于 Spring Boot 开发的校园活动管理后端服务，接口统一请求前缀：`/api`

## ✅ 一、运行环境要求【必须修改！】
### 核心环境（强制匹配，否则启动失败）
1. **JDK 版本**：✅ 必须使用 **JDK 17** (禁止使用 JDK8/JDK21/JDK24 等其他版本)
2. **Maven 版本**：3.8.0+ （推荐 3.9.12）
3. **编译环境**：Lombok 1.18.30+ （已在pom.xml中配置适配JDK17版本）

### 环境修改说明【重点】
- 本地需自行配置 JDK 17 环境变量，或在启动脚本/PowerShell中手动指定JDK17路径
- 禁止使用系统自动配置的高版本JDK（如JDK24），会导致编译报错 `java.lang.ExceptionInInitializerError: com.sun.tools.javac.code.TypeTag :: UNKNOWN`

## ✅ 二、本地快速启动步骤
### 方式1：PowerShell 手动启动（推荐，无环境冲突）
1. 打开 PowerShell 切换到本项目根目录 `E:\转移备用\backend1\backend`
2. 手动指定JDK17环境（关键，防止系统JDK版本冲突）
   ```powershell
   $env:JAVA_HOME = "D:\Users\jdk-17"
   $env:PATH = "$env:JAVA_HOME\bin;C:\Windows\System32;C:\Windows"
