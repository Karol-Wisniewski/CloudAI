import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;



class DeeplLAuthorTest {

    DeepL translator = new DeepL();

    DeepLAuthor underTest = new DeepLAuthor(translator);

    @Test
    void test() {
        // given

        // when
        String output = underTest.translate("en");

        // then
        assertThat(output).contains("December", "November", "Ballady i romanse");
    }
}
