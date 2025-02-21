package modern_processor;

import model.Request;

public class ValidationProcessor {

    public void validate(Request request){
        if(isRequestInvalid(request)) {
            throw new IllegalArgumentException("Missing Authorization header");
        }
        System.out.println("Request validated successfully " + request);
    }

    private boolean isRequestInvalid(Request request) {
        return request.headers() == null || !request.headers().containsKey("Authorization");
    }
}