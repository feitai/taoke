# taoke   2023.07-2023.08 饕客点餐系统
开发环境：JDK 1.8,Linux
开发工具：IntelliJ IDEA,Navicat,Postman,JMeter
相关技术：Spring Cloud, Nacos, Spring Security, Spring Cloud Gateway, MySQL, Redis, Sentinel, Vue, JMeter, Docker, Git, MyBatis Plus
项目描述:点餐系统，是一个基于 Spring Cloud 架构的微服务应用，旨在提供顾客方便的点餐和支付体
验。该系统通过拆分为不同的微服务来增强服务的稳定性和可扩展性。
技术要点: • 引入 Spring Security 用于用户登录和请求鉴权，确保只有经授权的用户可以访问系统功能。
• 使用 Spring Cloud 微服务框架开发账号、交易和支付系统，并利用 Nacos 实现服务注册和发现，确
保系统的动态扩展和高可用性。
• 配置和管理 Spring Cloud Gateway，统一接入和管理请求，同时实施请求路由和分发策略，以提高
系统性能和安全性。
• 使用 MySQL 数据库存储主要业务数据，负责数据库设计和维护，并集成 Redis 缓存，以提高系统响
应速度并减轻数据库压力。
• 应用 Sentinel 来保障服务的稳定性，实施流量控制和降级策略，同时使用 JMeter 进行压力测试，评
估系统在高负载下的性能表现，并根据测试结果进行优化和改进。
• 与前端协同工作，使用 Vue 框架开发用户界面，以确保用户友好的点餐体验。
• 采用 Docker 容器化部署，将微服务打包成容器。
• 利用 Git 进行版本控制，确保代码的版本历史和协作效率。
项目总结:
该点餐系统基于 Spring Cloud 微服务架构，实现了用户登录和鉴权、服务注册和发现、请求管理、数
据库与缓存优化、稳定性保障、性能测试、前后端协作和容器化部署。采用 Vue 框架开发用户界面，确
保用户友好体验。通过 Docker 容器化，提高部署效率。版本控制和协作使用 Git。项目为顾客提供了
高性能、安全可靠的点餐服务。
