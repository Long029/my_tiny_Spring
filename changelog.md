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