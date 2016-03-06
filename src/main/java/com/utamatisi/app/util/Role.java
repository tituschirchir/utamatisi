package com.utamatisi.app.util;

import java.util.Arrays;
import java.util.List;

/**
 * Created by titus.chirchir12
 * Date Created 3/5/2016.
 * Package: ${PACKAGE}
 */
public enum Role {
    NONE,
    READ_ONLY("read"),
    READ_WRITE("read","write");

    private final List<String> levels;

    Role(String... levels) {

        this.levels = Arrays.asList(levels);
    }
}
