# Swagger

[TOC]

## 概述

​        Swagger是一个开源软件框架，由大型工具生态系统支持，可帮助开发人员设计，构建，记录和使用RESTful Web服务。虽然大多数用户通过Swagger UI工具识别Swagger，但Swagger工具集支持自动文档，代码生成和测试用例生成。

**为什么要使用Swagger**

​        前后端分离的开发模式下，维持一份及时更新且完整的Rest API文档是十分重要的。传统的API文档通常是由后端开发人员手动编写，这种方式通常难以保证文档的及时性；为此，Swagger给我们提供了一个全新的维护API文档的方式，Swagger的优点如下：

1. 代码变，文档变。只需要少量的注解，Swagger 就可以根据代码自动生成 API 文档，很好的保证了文档的时效性。
2. 跨语言性，支持 40 多种语言。
3. Swagger UI 呈现出来的是一份可交互式的 API 文档，我们可以直接在文档页面尝试 API 的调用，省去了准备复杂的调用参数的过程。
4. 还可以将文档规范导入相关的工具（例如 SoapUI）, 这些工具将会为我们自动地创建自动化测试。

## SpringBoot整合Swagger

1. 创建SpringBoot项目，导入Spring，SpringMVC依赖；导入Swagger，Swagger UI依赖。	

  ```java
  <?xml version="1.0" encoding="UTF-8"?>
  <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
      <modelVersion>4.0.0</modelVersion>
      <parent>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-parent</artifactId>
          <version>2.2.6.RELEASE</version>
          <relativePath/> <!-- lookup parent from repository -->
      </parent>
      <groupId>com.yzb</groupId>
      <artifactId>spring-boot-swagger</artifactId>
      <version>0.0.1-SNAPSHOT</version>
      <name>spring-boot-swagger</name>
      <description>Demo project for Spring Boot</description>
  
      <properties>
          <java.version>1.8</java.version>
          <swagger.version>2.9.2</swagger.version>
      </properties>
  
      <dependencies>
          <!--Spring、SpringMVC依赖-->
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-web</artifactId>
          </dependency>
          <!--Swagger依赖-->
          <dependency>
              <groupId>io.springfox</groupId>
              <artifactId>springfox-swagger2</artifactId>
              <version>${swagger.version}</version>
          </dependency>
          <!--Swagger-ui依赖-->
          <dependency>
              <groupId>io.springfox</groupId>
              <artifactId>springfox-swagger-ui</artifactId>
              <version>${swagger.version}</version>
          </dependency>
  
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-test</artifactId>
              <scope>test</scope>
              <exclusions>
                  <exclusion>
                      <groupId>org.junit.vintage</groupId>
                      <artifactId>junit-vintage-engine</artifactId>
                  </exclusion>
              </exclusions>
          </dependency>
      </dependencies>
  
      <build>
          <plugins>
              <plugin>
                  <groupId>org.springframework.boot</groupId>
                  <artifactId>spring-boot-maven-plugin</artifactId>
              </plugin>
          </plugins>
      </build>
  </project>
  ```

2. 启用Swagger

   ```java
   @EnableSwagger2
   @Configuration
   public class SwaggerConfig {
       /*
        * @EnableSwagger2注解即启用了Swagger，相当于进行了下面两个Bean的配置
        */   
   //    @Bean
   //    public ApiInfo apiInfo(){
   //        return ApiInfo.DEFAULT;
   //    }
   //    @Bean
   //    public Docket docket(){
   //       return new Docket(DocumentationType.SWAGGER_2)
   //               .apiInfo(apiInfo());
   //    } 
   }
   
   ```

3. 创建Controller

   ```java
   @Controller
   public class HelloController {
       @ResponseBody
       @RequestMapping(value = "/hello",method = RequestMethod.GET)
       public String helloSwagger(){
           return "Hello Swagger";
       }
   }
   ```

4. 集成Swagger UI后，可以在浏览器中对Swagger生成的文档进行更直观的查看，测试等，如果不集成Swagger UI，那么访问`localhost:8080/v2/api-docs`即可查看Swagger根据项目生成的JSON文档，可读性较差。而集成Swagger UI后，界面可视化、可交互、更直观。

   ![Snipaste_2020-05-06_11-13-36](C:\Users\yzb\Pictures\Snipaste_2020-05-06_11-13-36.png)

5. 配置文档描述信息

   ```java
   /*
    * 文档描述信息配置
    */
   @Bean
   public ApiInfo apiInfo(){
       return new ApiInfo("标题：springboot整合swagger文档",
                          "描述信息",
                          "版本号1.0",
                          "服务条款",
                          new Contact("作者名","网站地址","email地址"),
                          "开源版本号",
                          "开源信息地址",
                          new ArrayList<>()
                         );
   }
   ```

   ![Snipaste_2020-05-06_11-44-23](C:\Users\yzb\Pictures\Snipaste_2020-05-06_11-44-23.png)

6. Docket配置扫描包

   ```java
   @Bean
   public Docket docket(){
       return new Docket(DocumentationType.SWAGGER_2)
           .apiInfo(apiInfo())
           .select()
   //        apis()方法用于配置扫描区域        
   //       .apis(RequestHandlerSelectors.any()) //扫描全部包
   //       .apis(RequestHandlerSelectors.none()) //全部不扫描
   //       扫描此包下的类的接口                           
           .apis(RequestHandlerSelectors.basePackage("com.yzb.controller"))
   //       扫描类上有Controller注解的        
   //      .apis(RequestHandlerSelectors.withClassAnnotation(Controller.class)) 
   //       扫描方法上有@RequestMapping注解的 
   //      .apis(RequestHandlerSelectors.withMethodAnnotation(RequestMapping.class))
           
   //       paths()方法用于路径过滤
           .paths(PathSelectors.any()) //扫描所有接口
   //      .paths(PathSelectors.none()) //不扫描接口
           .paths(PathSelectors.ant("/**")) //路径匹配则扫描
   //      .paths(PathSelectors.regex("")) //正则表达式匹配扫描符合的接口
           .build()
           ;
   }
   ```

   预览：

   ![image-20200507102136499](C:\Users\yzb\AppData\Roaming\Typora\typora-user-images\image-20200507102136499.png)

## Swagger注解

### Model注解

1. `@ApiModel`用于实体类

   | 注解属性      | 类型   | 描述       |
   | ------------- | ------ | ---------- |
   | value         | String | 实体类说明 |
   | description   | String |            |
   | parent        | Class  |            |
   | discriminator | String |            |
   | subTypes      | Class  |            |
   | reference     | String |            |

2. `@ApiModelProperty`用于实体类属性

   | 注解属性        | 类型    | 描述                   |
   | --------------- | ------- | ---------------------- |
   | value           | String  | 字段说明               |
   | name            | String  | 重写字段名称           |
   | dataType        | String  | 重写字段类型           |
   | required        | boolean | 是否必填               |
   | example         | String  | 举例说明               |
   | hidden          | boolean | 是否在文档中隐藏该字段 |
   | allowEmptyValue | boolean | 是否允许为空           |
   | allowableValues | String  | 该字段允许的值         |

### Controller注解

* `@Api`

  | 注解属性 | 类型     | 描述       |
  | -------- | -------- | ---------- |
  | tags     | String[] | 控制器标签 |
  | value    | String   | 控制器描述 |

### 接口注解

1. `@ApiOperation`

   | 注解属性   | 类型     | 描述         |
   | ---------- | -------- | ------------ |
   | value      | String   | 接口描述     |
   | notes      | String   | 接口发布说明 |
   | tags       | String[] | 接口标签     |
   | response   | Class<?> | 接口返回类型 |
   | httpMethod | String   | 接口请求方式 |

2. `@ApiIgnore` ：Swagger文档不会显示拥有该注解的接口

3. `@ApiImplicitParams` ：用于描述接口的非对象参数集

4. `ApiImplicitParam` ： 用于描述接口的非对象参数

   | 注解属性  | 描述           |
   | --------- | -------------- |
   | paramType | 查询参数类型   |
   | dataType  | 参数的数据类型 |
   | name      | 参数名         |
   | value     | 参数意义描述   |
   | required  | 是否必填       |

   

