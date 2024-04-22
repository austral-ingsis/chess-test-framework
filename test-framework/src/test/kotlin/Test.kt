import edu.austral.dissis.chess.test.game.GameTester
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import java.util.stream.Stream

class Test {

    @TestFactory
    fun `required exam tests`(): Stream<DynamicTest> {

        return GameTester(TestGameRunnerImpl()).test()
    }

}