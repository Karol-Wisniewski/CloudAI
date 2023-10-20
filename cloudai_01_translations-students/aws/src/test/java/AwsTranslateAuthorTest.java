import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class AwsTranslateAuthorTest {

    AwsTranslate translator = new AwsTranslate();

    AwsTranslateAuthor underTest = new AwsTranslateAuthor(translator);

    @Test
    void shouldTranslateAuthorInfo() {
        // given
        String targetLang = "en";

        // when
        String output = underTest.translate(targetLang);

        // then
        // TODO add assertion (using assertj is recommended)
        Assertions.assertThat(output).contains("Ballady i romanse");
    }
}
