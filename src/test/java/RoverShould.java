import com.codurance.Rover;
import com.codurance.error.UnknownCommandException;
import com.codurance.model.Grid;
import com.codurance.model.Obstacle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoverShould {

    private final Grid grid = new Grid();
    private final Rover rover = new Rover(grid);

    @ParameterizedTest
    @CsvSource({
            "R, 0:0:E",
            "RR, 0:0:S",
            "RRR, 0:0:W",
            "RRRR, 0:0:N",
            "RRRRR, 0:0:E"
    })
    void rotate_right(String command, String expectedPosition) {
        assertEquals(expectedPosition, rover.execute(command));
    }

    @ParameterizedTest
    @CsvSource({
            "L, 0:0:W",
            "LL, 0:0:S",
            "LLL, 0:0:E",
            "LLLL, 0:0:N",
            "LLLLL, 0:0:W"
    })
    void rotate_left(String command, String expectedPosition) {
        assertEquals(expectedPosition, rover.execute(command));
    }

    @ParameterizedTest
    @CsvSource({
            "M, 0:1:N",
            "MM, 0:2:N",
            "MMM, 0:3:N",
            "MMMMMMMMM, 0:9:N",
    })
    void move(String command, String expectedPosition) {
        assertEquals(expectedPosition, rover.execute(command));
    }

    @ParameterizedTest
    @CsvSource({
            "MMMMMMMMMM, 0:0:N",
            "RMMMMMMMMMM, 0:0:E",
            "RRM, 0:9:S",
            "LM, 9:0:W",
    })
    void wrap_around_grid(String command, String expectedPosition) {
        assertEquals(expectedPosition, rover.execute(command));
    }

    @Test
    void avoid_obstacle() {
        grid.add(new Obstacle(0, 3));
        Rover target = new Rover(grid);

        assertEquals("O:0:2:N", target.execute("MMMM"));
    }

    @Test
    void reject_unknown_command() {
        UnknownCommandException exception = assertThrows(UnknownCommandException.class, () -> rover.execute("X"));

        assertEquals("Unknown command X, allowed commands are only L, R, M", exception.getMessage());
    }
}
