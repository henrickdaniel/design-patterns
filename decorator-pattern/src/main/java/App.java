import model.Request;
import modern_decorator.RequestProcessor;
import modern_processor.BaseProcessor;
import modern_processor.LogProcessor;
import modern_processor.ValidationProcessor;

import java.util.Map;

public class App {

    public static void main(String[] args) {
        var logProcessor = new LogProcessor();
        var validationProcessor = new ValidationProcessor();
        var baseProcessor = new BaseProcessor();

        RequestProcessor emptyFunction = request -> {
        };

        var processorPipeline = emptyFunction.andThen(logProcessor::process)
                .andThen(validationProcessor::validate)
                .andThen(baseProcessor::process);

        var headers = Map.of("Authorization", "Bearer token");
        var validRequest = new Request("198.168.1.1", headers);
        var invalidRequest = new Request("198.168.1.1", null);

        processorPipeline.process(validRequest);
        processorPipeline.process(invalidRequest);

    }

}
