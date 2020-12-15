package ru.geekbrains;

import org.apiguardian.api.API;

import java.lang.annotation.*;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@API(
        status = API.Status.STABLE,
        since = "5.0"
)
public @interface AfterSuite {
}
