package us.codecraft.tinyioc;

import org.junit.Assert;

public class OutputService {

    private HelloWorldService helloWorldService;

    public String output(String text){
        Assert.assertNotNull(helloWorldService);
        return text;
    }

    public void setHelloWorldService(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }
}
