package modern_processor;

import model.Request;

public class BaseProcessor {

    public void process(Request request){
        System.out.println("Request processed successfully " + request);
    }

}
