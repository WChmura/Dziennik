package Models;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Proxy implements InvocationHandler {
    private Object obj;

    public static Object newInstance(Object obj) {
        //System.out.println("próba utworzenia proxy dla klasy\n\n" + obj.getClass().getName() + "\n\ni interfejsu\n\n" + obj.getClass().getInterfaces());
        return java.lang.reflect.Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new Proxy(obj));
    }

    private Proxy(Object obj) {
        this.obj = obj;
    }

    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
        Object result;
        try {
            long start = System.nanoTime();
            result = m.invoke(obj, args);
            long end = System.nanoTime();
            System.out.println((String.format("%s took %d ns", m.getName(), (end-start))));
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        } catch (Exception e) {
            throw new RuntimeException("unexpected invocation exception: " + e.getMessage());
        }
        return result;
    }
}