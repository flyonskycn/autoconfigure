# autoconfigure
## 项目介绍
1. 按spring boot方式引入对swagger、cat、apollo的支持

### 样例项目
[样例项目参见](https://github.com/flyonskycn/micro-service-study)

### cat的埋点支持
1. 单独支持feign。
2. 支持Spring FeignClient,并且支持FeginClient开启Hystrix。
3. 支持RestTemplate埋点；
4. 支持Web的Filter埋点；
5. 支持对方法的AOP埋点，只需在方法上增加CatAnnotation即可。

### apollo
1. 支持客户端以archaius的方式使用配置项。
2. 支持动态修改日志级别。

### swagger 
1. 方便项目引入swagger的文档功能。