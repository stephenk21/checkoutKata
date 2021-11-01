package cart;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class CartTestParameters {

    static Stream<Arguments> cartItemsToAdd() {
        return Stream.of(
                Arguments.of(3, 2, 0, 0, 175),
                Arguments.of(1, 1, 1, 1, 115),
                Arguments.of(4, 2, 0, 0, 225),
                Arguments.of(0, 0, 0, 0, 0)
        );
    }
}
