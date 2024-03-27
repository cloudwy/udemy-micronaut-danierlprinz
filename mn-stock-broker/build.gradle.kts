plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.micronaut.application") version "4.3.2"
    id("io.micronaut.aot") version "4.3.2"
//    id("io.micronaut.application") version "3.3.2"
//    id("io.micronaut.aot") version "3.3.2"
    id("org.flywaydb.flyway") version "9.7.0"
}

version = "0.1"
group = "com.wy.udemy"

repositories {
    mavenCentral()
}

dependencies {
    annotationProcessor("io.micronaut.data:micronaut-data-processor")
    annotationProcessor("io.micronaut:micronaut-http-validation")  //1
    annotationProcessor("io.micronaut.serde:micronaut-serde-processor") //n
    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.1") //2
    implementation("io.micronaut:micronaut-management") //9
    // https://mvnrepository.com/artifact/io.micronaut.flyway/micronaut-flyway
    compileOnly("io.micronaut.flyway:micronaut-flyway") //3-implementation
    implementation("io.micronaut.sql:micronaut-jdbc-hikari") // 10
    // https://mvnrepository.com/artifact/jakarta.validation/jakarta.validation-api
    implementation("jakarta.validation:jakarta.validation-api:3.1.0-M1") //4
    compileOnly("io.micronaut:micronaut-http-client") //5
    testImplementation("io.micronaut:micronaut-http-client") //HttpClient in test
    runtimeOnly("ch.qos.logback:logback-classic") //6
    // https://mvnrepository.com/artifact/com.github.javafaker/javafaker
    // https://github.com/DiUS/java-faker/issues/327
    implementation("com.github.javafaker:javafaker:1.0.2"){exclude(module = "snakeyaml")} //n
    implementation("org.yaml:snakeyaml:1.17") //n
    // https://mvnrepository.com/artifact/org.projectlombok/lombok
    implementation("org.projectlombok:lombok") //n
    // https://mvnrepository.com/artifact/io.swagger.core.v3/swagger-annotations
    implementation("io.swagger.core.v3:swagger-annotations:2.2.20") //n
    implementation("io.micronaut.serde:micronaut-serde-jackson") //n
    /*Database Driver*/
    // https://mvnrepository.com/artifact/org.postgresql/postgresql
    implementation("org.postgresql:postgresql:42.7.3") //7
    /*Schema Migration*/
    runtimeOnly("org.flywaydb:flyway-database-postgresql") //8



//    annotationProcessor("io.micronaut.data:micronaut-data-processor")
//    annotationProcessor("io.micronaut:micronaut-http-validation") //1
//    annotationProcessor("io.micronaut.validation:micronaut-validation-processor")
//    implementation("io.micronaut:micronaut-jackson-databind") //2
////    implementation("io.micronaut:micronaut-management") //9
////    implementation("io.micronaut.data:micronaut-data-jdbc")
//    implementation("io.micronaut.flyway:micronaut-flyway") //3
//    implementation("io.micronaut.sql:micronaut-jdbc-hikari") //10 important!
////    implementation("io.micronaut.validation:micronaut-validation")
//    implementation("jakarta.validation:jakarta.validation-api") //4
//    compileOnly("io.micronaut:micronaut-http-client") //5
//    runtimeOnly("ch.qos.logback:logback-classic") //6
////    testImplementation("io.micronaut:micronaut-http-client")
//    implementation("org.postgresql:postgresql:42.7.3") //7
//    runtimeOnly("org.flywaydb:flyway-database-postgresql") //8
//    runtimeOnly("org.yaml:snakeyaml")
//
//    implementation("com.github.javafaker:javafaker:1.0.2"){exclude(module = "snakeyaml")}
//    implementation("org.yaml:snakeyaml:1.17")
//    annotationProcessor("io.micronaut.serde:micronaut-serde-processor")
//    implementation("io.micronaut.serde:micronaut-serde-jackson")
//    implementation("org.projectlombok:lombok")
//    implementation("io.swagger.core.v3:swagger-annotations:2.2.20")
}


application {
    mainClass.set("com.wy.udemy.Application")
}
java {
    sourceCompatibility = JavaVersion.toVersion("17")
    targetCompatibility = JavaVersion.toVersion("17")
}


graalvmNative.toolchainDetection.set(false)
micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("com.wy.udemy.*")
    }
    aot {
        // Please review carefully the optimizations enabled below
        // Check https://micronaut-projects.github.io/micronaut-aot/latest/guide/ for more details
        optimizeServiceLoading.set(false)
        convertYamlToJava.set(false)
        precomputeOperations.set(true)
        cacheEnvironment.set(true)
        optimizeClassLoading.set(true)
        deduceEnvironment.set(true)
        optimizeNetty.set(true)
    }
}





