import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DeepLTest {

    static final String TEST_INPUT = "Our greatest weakness lies in giving up. The most certain way to succeed is always to try just one more time.";

    DeepL  underTest = new DeepL();

    @Test
    void shouldTranslateWithDeepL() {

        // when
        String output = underTest.translate(TEST_INPUT, "fr");

        // then
        assertThat(output).containsAnyOf("faiblesse", "toujours");
    }

    // TODO create tests
}
