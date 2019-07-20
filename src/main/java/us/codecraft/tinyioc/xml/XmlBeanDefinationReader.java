package us.codecraft.tinyioc.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import us.codecraft.tinyioc.AbstractBeanDefinationReader;
import us.codecraft.tinyioc.BeanDefination;
import us.codecraft.tinyioc.BeanReference;
import us.codecraft.tinyioc.PropertyValue;
import us.codecraft.tinyioc.io.ResourceLoader;
import us.codecraft.tinyioc.util.StringUtils;

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
        BeanDefination beanDefination = new BeanDefination();
        beanDefination.setBeanClassName(className);
        registBeanDefinationProperty(el, beanDefination);
        getRegister().put(name, beanDefination);
    }

    private void registBeanDefinationProperty(Element el, BeanDefination beanDefination) {
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
                if(StringUtils.isNotEmpt(proValue)){
                    beanDefination.getPropertyValues().addPropertyValue(new PropertyValue(proName, proValue));
                }else{
                    String ref = element.getAttribute("ref");
                    if (ref == null || ref.length() == 0) {
                        throw new IllegalArgumentException("Configuration problem: <property> element for property '"
                                + proName + "' must specify a ref or value");
                    }
                    BeanReference beanReference = new BeanReference(proName);
                    beanDefination.getPropertyValues().addPropertyValue(new PropertyValue(proName, beanReference));
                }
            }
        }
    }
}
