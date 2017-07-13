package com.dp.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DebugProxy implements InvocationHandler {
	private Object obj;

	public static Object newInstance(Object obj) {
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(),
				obj.getClass().getInterfaces(), new DebugProxy(obj));
	}

	private DebugProxy(Object obj) {
		// Greet接口的实现：GreetImpl
		this.obj = obj;
	}

	@Override
	public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
		Object result;
		try {
			// 自定义的处理
			System.out.println("--before method " + m.getName());
			// 调用GreetImpl中方法
			result = m.invoke(obj, args);
		} catch (InvocationTargetException e) {
			throw e.getTargetException();
		} catch (Exception e) {
			throw new RuntimeException("unexpected invocation exception: " + e.getMessage());
		} finally {
			System.out.println("--after method " + m.getName());
		}
		return result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Greet tmp = new GreetImpl();

		Greet greet = (Greet) DebugProxy.newInstance(tmp);
		// 生成的greet和tmp有相同的hashCode

		greet.sayHello("walter");
		greet.goodBye();
	}
}