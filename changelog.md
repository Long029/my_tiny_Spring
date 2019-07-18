最简单的容器是直接将Bean生成, set到BeanDefiniation中.

此次的修改是, 将Bean的生成改为通过BeanClassName获得Class对象
接着使用反射机制, newInstance()方法生成一个bean的实例.

将BeanFactory抽象为接口.

新增AbstractBeanFactory, 作为BeanFactory接口的实现
在实现中, 重写registeBeanDefiniation()方法 和 getBeanDefiniation()方法
使用反射获取Bean的实例方法, 被AbstractBeanFactory的子类实现.

新增AutowireCapableBeanFactory, 是AbstractBeanFactory的子类, 实现使用Class生成一个bean实例的方法.

修改了BeanDefiniation类, 增加属性BeanClass和BeanClassName
在设置BeanClassName时会获取Class, 并赋值给BeanClass.

-------------

refactor
将项目结构进行一些修改

------------------step3
第三次提交, 增加对bean属性的支持

一个bean中的私有属性可以被注入.

新增PropertyValue类, 是bean类的属性和value
新增PropertyValues类, 是bean的所有类属性

修改了BeanDefiniation, 支持设置PropertyValues

修改了AbstractBeanFactory, 使其在获得bean的实例后可以将PropertyValues中的propertyValue注入到bean的属性
修改了HelloWorldService, 使其具有一个私有属性
修改了BeanFactoryTest, 使其将PropertyValues注入, 测试

-------------------step4
新增BeanDefiniationReader接口
新增AbstractBeanDefiniationReader类, 有注册列表和读取xml的Resource
新增XmlBeanDefinationReader类, 实现接口并继承AbstractBeanFactoryReader, 主要功能为读取xml文件, 并根据文件内容生成
BeanDefination, 注册
新增测试类

-----------------step5
这是一个大版本, ApplicationContext