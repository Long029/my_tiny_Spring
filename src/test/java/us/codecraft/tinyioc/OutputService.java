package us.codecraft.tinyioc;

public class OutputService {

    private HelloWorldService helloWorldService;

    public String output(String text){
        return text;
    }

    public void setHelloWorldService(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }
}
