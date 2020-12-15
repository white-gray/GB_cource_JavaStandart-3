package ru.geekbrains;

import org.apiguardian.api.API;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@API(
        status = API.Status.EXPERIMENTAL,
        since = "5.4"
)
public @interface Order {
    @API(
            status = API.Status.EXPERIMENTAL,
            since = "5.6"
    )
    int DEFAULT = 1073741823;
    int value();

}