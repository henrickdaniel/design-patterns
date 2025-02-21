package modern_decorator;

import model.Request;

@FunctionalInterface
public interface RequestProcessor {

    void process(Request request);

    default RequestProcessor andThen(RequestProcessor nextRequestProcessor){
        return request -> {
          this.process(request);
          nextRequestProcessor.process(request);
        };
    }
}
