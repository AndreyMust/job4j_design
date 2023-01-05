package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {

    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkNamesEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }

    @Test
    void checkNamesContainEqual() {
        NameLoad nameLoad = new NameLoad();
        String param = "aaa";
        assertThatThrownBy(() -> nameLoad.parse(param))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(param)
                .hasMessageContaining("the symbol \"=\"");
    }

    @Test
    void checkNamesContainKey() {
        NameLoad nameLoad = new NameLoad();
        String param = "=333";
        assertThatThrownBy(() -> nameLoad.parse(param))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(param)
                .hasMessageContaining("not contain a key");
    }

    @Test
    void checkNamesContainValue() {
        NameLoad nameLoad = new NameLoad();
        String param = "aaa=";
        assertThatThrownBy(() -> nameLoad.parse(param))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(param)
                .hasMessageContaining("not contain a value");
    }
}