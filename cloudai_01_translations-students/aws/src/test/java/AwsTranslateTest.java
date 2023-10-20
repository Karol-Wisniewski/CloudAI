import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AwsTranslateTest {

    AwsTranslate underTest = new AwsTranslate();

    static final String TEST_INPUT = "Our greatest weakness lies in giving up. The most certain way to succeed is always to try just one more time.";

    // TODO create test
    @Test
    void shouldTranslateWithAwsTranslate() {

        // when
        String output = underTest.translate(TEST_INPUT, "en", "fr");

        // then
        assertThat(output).containsAnyOf("faiblesse", "toujours");
    }
}
