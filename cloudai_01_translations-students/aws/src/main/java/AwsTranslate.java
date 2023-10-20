import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.translate.AmazonTranslate;
import com.amazonaws.services.translate.AmazonTranslateClient;
import com.amazonaws.services.translate.model.*;

class AwsTranslate {

    String translate(String input, String sourceLang, String targetLang) {
        // TODO create instance of AWS credentials provider: AWSStaticCredentialsProvider
        // TODO create client for AWS Translate
        // TODO create request
        // TODO (optionally) define settings for request
        // TODO call the service using the client

        AWSCredentialsProvider awsCreds = DefaultAWSCredentialsProviderChain.getInstance();

        AmazonTranslate client = AmazonTranslateClient.builder()
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds.getCredentials()))
                .withRegion(Regions.EU_WEST_3)
                .build();

        TranslateTextRequest request = new TranslateTextRequest()
                .withText(input)
                .withSourceLanguageCode(sourceLang)
                .withTargetLanguageCode(targetLang);

        TranslateTextResult result = client.translateText(request);

        return result.getTranslatedText();
    }
}
