package ru.geekbrains;
//
// Source code recreated from a org.junit.jupiter.api.Assertions
//

import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;

@API(
        status = Status.STABLE,
        since = "5.0"
)
public class Assertions {
    @API(
            status = Status.STABLE,
            since = "5.3"
    )
    protected Assertions() {
    }
    public static boolean assertEquals(int expected, int actual) {
        if (expected == actual) {return true;};
        return false;
    }
}
