package ru.job4j.io;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairByName() {
        String path = "./data/pair_config.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
    }

    @Test
    public void whenNoPairByName() {
        String path = "./data/pair_config.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("surname"), is(nullValue()));
    }

    @Test
    public void whenCheckComment() {
        String path = "./data/pair_config.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("#CheckForComment"), is(nullValue()));
    }

    @Test
    public void whenDublEquals() {
        String path = "./data/pair_config.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("myName"), is("Andrew=1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNonValue() {
        /*NoValue=*/
        String path = "./data/pair_config2.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("NoValue"), is(""));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNonKey() {
        /*=NoKeyValue*/
        String path = "./data/pair_config2.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value(""), is("NoKeyValue"));
    }

    @Test
    public void whenNonEquals() {
        /*key1:value1*/
        String path = "./data/pair_config.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key1"), is(nullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenEmptyEquals() {
        /*=*/
        String path = "./data/pair_config2.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value(""), is(""));
    }
}