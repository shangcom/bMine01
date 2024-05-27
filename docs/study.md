# Build.gradle Dependency Keywords

| **Keyword**            | **Description**                                    | **Scope**                    |
|------------------------|----------------------------------------------------|------------------------------|
| `implementation`       | 프로젝트에서 컴파일 및 런타임에 필요한 의존성을 추가합니다.  | Compile & Runtime            |
| `compileOnly`          | 컴파일 시에만 필요한 의존성을 추가합니다.                 | Compile Only                 |
| `annotationProcessor`  | 어노테이션 프로세싱에 필요한 의존성을 추가합니다.           | Annotation Processing Only   |
| `testImplementation`   | 테스트 컴파일 및 실행 시에 필요한 의존성을 추가합니다.      | Test Compile & Runtime       |
| `testCompileOnly`      | 테스트 컴파일 시에만 필요한 의존성을 추가합니다.           | Test Compile Only            |
| `testAnnotationProcessor` | 테스트 어노테이션 프로세싱에 필요한 의존성을 추가합니다.  | Test Annotation Processing   |
| `runtimeOnly`          | 런타임에만 필요한 의존성을 추가합니다.                    | Runtime Only                 |
| `developmentOnly`      | 개발 환경에서만 필요한 의존성을 추가합니다.                | Development Only             |

## Example Configuration

```gradle

플러그인을 사용하여 빌드 스크립트의 기능을 확장합니다.
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.2.5'
    id 'io.spring.dependency-management' version '1.1.4'
}

프로젝트의 메타데이터를 정의합니다.
group = 'org.zerock'
version = '0.0.1-SNAPSHOT'

Java 컴파일러의 호환성을 설정합니다.
java {
    sourceCompatibility = '17'
}

Maven BOM을 가져와서 의존성 버전을 관리합니다.
dependencyManagement {
    imports {
        mavenBom "org.springframework.boot:spring-boot-dependencies:3.2.5"
    }
}

의존성의 범위를 설정합니다.
configurations {
    compileOnly {
        extendsFrom annotationProcessor
    }
}

의존성을 다운로드할 리포지토리를 설정합니다.
repositories {
    mavenCentral()
}

프로젝트에 필요한 의존성을 선언합니다.
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
    implementation 'nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect'
    implementation 'org.springframework.boot:spring-boot-starter-web'

    compileOnly 'org.projectlombok:lombok'
    annotationProcessor 'org.projectlombok:lombok'
    
    developmentOnly 'org.springframework.boot:spring-boot-devtools'
    runtimeOnly 'org.mariadb.jdbc:mariadb-java-client'

    testCompileOnly 'org.projectlombok:lombok'
    testAnnotationProcessor 'org.projectlombok:lombok'

    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'

    implementation 'com.querydsl:querydsl-jpa'
    annotationProcessor 'com.querydsl:querydsl-apt'
    annotationProcessor 'com.querydsl:querydsl-apt:jpa'
}

빌드 작업을 설정합니다.
tasks.named('test') {
    useJUnitPlatform()
}
