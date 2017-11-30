package com.hualu.main.java.util.converter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GenericConverter {

	/**
	 * @author YKK 
	 * 要求源对象和目标对象拥有名称相同、类型不同的属性值
	 * @param s
	 *            source object, usually form
	 * @param t
	 *            target object, usually entity
	 * @return new target object
	 * @throws ParseException
	 */
	public static Object convert(Object s, Object t) throws ParseException {
		Field[] fields = t.getClass().getDeclaredFields();
		try {
			for(int i = 0; i < fields.length; i++) { // 遍历所有属性
				String name = fields[i].getName(); // 获取属性的名字
				name = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
				String type = fields[i].getGenericType().toString(); // 获取属性的类型
				Method m = s.getClass().getMethod("get" + name);
				String value = (String) m.invoke(s); // 调用getter方法获取属性值
				if(type.equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
					if (value != null && !"".equals(value)) {
						m = t.getClass().getMethod("set" + name, String.class);
						m.invoke(t, value);
					}
				} else if(type.equals("class java.lang.Integer")) {
					if (value != null && !"".equals(value)) {
						Integer integer = Integer.parseInt(value);
						m = t.getClass().getMethod("set" + name, Integer.class);
						m.invoke(t, integer);
					}
				} else if(type.equals("class java.sql.Timestamp")) {
					if (value != null && !"".equals(value)) {
						Timestamp timestamp = Timestamp.valueOf(value);
						m = t.getClass().getMethod("set" + name, Timestamp.class);
						m.invoke(t, timestamp);
					}
				} else if(type.equals("class java.util.Date")) {
					if (value != null && !"".equals(value)) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Date date = sdf.parse(value);
						m = t.getClass().getMethod("set" + name, Date.class);
						m.invoke(t, date);
					}
				}
			}
			return t;
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
}
