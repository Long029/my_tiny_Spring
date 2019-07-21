package us.codecraft.tinyioc;

public class HelloWorldServiceImpl implements HelloWorldService {
    private String text;

    private OutputService outputService;

    @Override
    public String helloWorld(){
        return outputService.output(text);
    }

    @Override
    public void helloPrint() {
        System.out.println(outputService.output(text));
    }

    public void setText(String text) {
        this.text = text;
    }
    public void setOutputService(OutputService outputService) {
        this.outputService = outputService;
    }
}
