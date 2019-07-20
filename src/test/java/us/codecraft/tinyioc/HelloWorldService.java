package us.codecraft.tinyioc;

public class HelloWorldService {

    private String text;

    private OutputService outputService;

    public String helloWorld(){
        return outputService.output(text);
//        return text;

    }


    public void setText(String text) {
        this.text = text;
    }

    public void setOutputService(OutputService outputService) {
        this.outputService = outputService;
    }
}

