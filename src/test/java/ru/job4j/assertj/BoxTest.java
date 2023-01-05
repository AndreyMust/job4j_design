package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 12);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
    }

    @Test
    void numberOfEdgeSphere() {
        Box box = new Box(0, 10);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices).isEqualTo(0);
    }

    @Test
    void numberOfEdgeCube() {
        Box box = new Box(8, 12);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices).isEqualTo(8);
    }

    @Test
    void isExistSphere() {
        Box box = new Box(0, 10);
        boolean isExist = box.isExist();
        assertThat(isExist).isTrue();
    }

    @Test
    void isExistCube() {
        Box box = new Box(8, 12);
        boolean isExist = box.isExist();
        assertThat(isExist).isTrue();
    }

    @Test
    void areaSphereIsCloseTo() {
        Box box = new Box(0, 10);
        double area = box.getArea();
        assertThat(area).isCloseTo(1256.6d, withPrecision(0.1d));
    }

    @Test
    void areaSphereIsLessThan() {
        Box box = new Box(0, 10);
        double area = box.getArea();
        assertThat(area).isLessThan(1256.7d);
    }
}