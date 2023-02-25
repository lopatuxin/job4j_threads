package ru.job4j.cash;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CacheTest {

    Base model = new Base(1, 1);
    Cache cache = new Cache();

    @Test
    void whenAdd() {
        assertThat(cache.add(model)).isTrue();
    }

    @Test
    void whenNotAdd() {
        Base model2 = new Base(1, 0);
        cache.add(model);
        assertThat(cache.add(model2)).isFalse();
    }

    @Test
    void whenDelete() {
        cache.add(model);
        cache.delete(model);
        assertThat(cache.get(model.getId())).isNull();
    }

    @Test
    void whenUpdate() {
        cache.add(model);
        Base up = new Base(1, 1);
        up.setName("Anton");
        boolean result = cache.update(up);
        assertThat(result).isTrue();
        assertThat(cache.get(model.getId()).getVersion()).isEqualTo(model.getVersion() + 1);
        assertThat(cache.get(model.getId()).getName()).isEqualTo(up.getName());
    }

    @Test
    void whenDoNotUpdateVersion() {
        cache.add(model);
        Base up = new Base(1, 5);
        assertThatThrownBy(() -> cache.update(up)).hasMessageContaining("Versions are not equal");
    }
}