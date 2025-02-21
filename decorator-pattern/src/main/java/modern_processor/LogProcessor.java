package modern_processor;

import model.Request;

public class LogProcessor {

    public void process(Request request){
        System.out.println("Loggin request: " + request);
    }
}
