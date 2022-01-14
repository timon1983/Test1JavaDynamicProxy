import org.junit.Test;

import java.lang.reflect.Proxy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MethodInterception {

    @Test
    public void annotationValue() {
        MainPage mainPage = createPage(MainPage.class);
        assertNotNull(mainPage);
        assertEquals(mainPage.buttonSearch(), ".//*[@test-attr='button_search']");
        assertEquals(mainPage.textInputSearch(), ".//*[@test-attr='input_search']");
    }

    private MainPage createPage(Class clazz) {
        ClassLoader classLoader = MyInvocationHandler.class.getClassLoader();
        Class[] interfaces =  new Class[] { clazz };
        return (MainPage) Proxy.newProxyInstance(classLoader, interfaces, new MyInvocationHandler());
    }
}
