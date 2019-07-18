package us.codecraft.tinyioc.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import us.codecraft.tinyioc.AbstractBeanDefinationReader;
import us.codecraft.tinyioc.BeanDefiniation;
import us.codecraft.tinyioc.PropertyValue;
import us.codecraft.tinyioc.io.ResourceLoader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

public class XmlBeanDefinationReader extends AbstractBeanDefinationReader {

    public XmlBeanDefinationReader(ResourceLoader resourceLoader) {
        super(resourceLoader);
    }

    @Override
    public void readBeanDefinations(String location) throws Exception {
        InputStream inputStream = getResourceLoader().getResource(location).getInputStream();
        doLoadBeanDefination(inputStream);
    }

    private void doLoadBeanDefination(InputStream inputStream) throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document doc = documentBuilder.parse(inputStream);
        //解析xml
        registerBeanDefination(doc);
        inputStream.close();
    }

    private void registerBeanDefination(Document doc) {
        Element root = doc.getDocumentElement();
        parseBeanDefination(root);
    }

    private void parseBeanDefination(Element root) {
        NodeList nodeList = root.getChildNodes();
        Node node;
        Element el;
        for (int i = 0; i < nodeList.getLength(); i++) {
            node = nodeList.item(i);
            if(node instanceof Element){
                el = (Element) node;
                processBeanDefination(el);
            }
        }
    }

    private void processBeanDefination(Element el) {
        String name = el.getAttribute("name");
        String className = el.getAttribute("class");
        BeanDefiniation beanDefiniation = new BeanDefiniation();
        beanDefiniation.setBeanClassName(className);
        registBeanDefinationProperty(el,beanDefiniation);
        getRegister().put(name, beanDefiniation);
    }

    private void registBeanDefinationProperty(Element el, BeanDefiniation beanDefiniation) {
        NodeList nl = el.getChildNodes();
        Node node;
        Element element;
        String proName;
        String proValue;
        for (int i = 0; i < nl.getLength(); i++) {
            node = nl.item(i);
            if (node instanceof Element){
                element = (Element) node;
                proName = element.getAttribute("name");
                proValue = element.getAttribute("value");
                beanDefiniation.getPropertyValues().addPropertyValue(new PropertyValue(proName, proValue));
            }
        }
    }
}
