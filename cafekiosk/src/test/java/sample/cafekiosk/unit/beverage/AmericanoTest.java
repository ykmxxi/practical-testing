package sample.cafekiosk.unit.beverage;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class AmericanoTest {

    private Americano americano;

    @Test
    void getName() {
        americano = new Americano();

        assertThat(americano.getName()).isEqualTo("아메리카노");
    }

    @Test
    void getPrice() {
        americano = new Americano();

        assertThat(americano.getPrice()).isEqualTo(4000);
    }

}
