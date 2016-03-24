package com.delas.common.tools.object;

import java.awt.Point;
import java.awt.Polygon;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassUtil {

	private static final Map<Class<?>, Class<?>> primitiveWrapperMap = new HashMap<Class<?>, Class<?>>();
	
	static {
		primitiveWrapperMap.put(Boolean.TYPE, Boolean.class);
		primitiveWrapperMap.put(Byte.TYPE, Byte.class);
		primitiveWrapperMap.put(Character.TYPE, Character.class);
		primitiveWrapperMap.put(Short.TYPE, Short.class);
		primitiveWrapperMap.put(Integer.TYPE, Integer.class);
		primitiveWrapperMap.put(Long.TYPE, Long.class);
		primitiveWrapperMap.put(Double.TYPE, Double.class);
		primitiveWrapperMap.put(Float.TYPE, Float.class);
		primitiveWrapperMap.put(Void.TYPE, Void.TYPE);
	}

	private static final Map<Class<?>, Class<?>> wrapperPrimitiveMap = new HashMap<Class<?>, Class<?>>();

	static {
		for (Class<?> primitiveClass : primitiveWrapperMap.keySet()) {
			Class<?> wrapperClass = primitiveWrapperMap.get(primitiveClass);
			if (!primitiveClass.equals(wrapperClass)) {
				wrapperPrimitiveMap.put(wrapperClass, primitiveClass);
			}
		}
	}

	public static synchronized String toString(Object obj) {
		return toString(obj, 1);
	}

	public static synchronized String toString(Object obj, int n) {
		return toString(obj, n, 0, new ArrayList<Integer>());
	}

	private static synchronized String toString(Object obj, int deepness, int tab, List<Integer> lasts) {
		if (obj == null) {
			return "null";

		} else if ((isPrimitiveOrWrapper(obj.getClass()) || obj.getClass().equals(String.class))) {
			return obj.toString();

		}

		StringBuilder str = new StringBuilder();
		StringBuilder strTab = new StringBuilder();
		for (int i = 0; i < tab; i++) {
			if (lasts.contains(i)) {
				strTab.append("\t");
				continue;
			}

			strTab.append(" |").append("\t");
		}
		str.append(obj.getClass().getCanonicalName());

		if (deepness == 0) {
			return str.toString();
		}

		if (obj.getClass().isArray()) {
			str.append(System.lineSeparator());

			str.append(strTab).append("{").append(System.lineSeparator());
			for (int i = 0; i < Array.getLength(obj); i++) {

				Object val = Array.get(obj, i);
				str.append(strTab).append(" +- ").append(toString(val, deepness - 1, tab + 1, lasts));
				if (i < Array.getLength(obj))
					str.append(",").append(System.lineSeparator());
			}
			str.append(strTab).append("}");
			return str.toString();

		}

		List<Field> declaredFields = getAllFields(obj.getClass()); 
		if (declaredFields == null || declaredFields.isEmpty()) {
			return str.toString();
		}

		str.append(System.lineSeparator()).append(strTab).append(" |").append(System.lineSeparator());
		try {

			for (int i = 0; i < declaredFields.size(); i++) {
				boolean isLastField = (i == declaredFields.size() - 1);
				int index = 0;
				if (isLastField) {
					lasts.add(tab);
					index = lasts.indexOf(tab);
				}
				Field field = declaredFields.get(i);
				field.setAccessible(true);
				str.append(strTab).append(" +- ").append(field.getName()).append(" (");
				
				Object fieldValue = field.get(obj);
				str.append(field.getType().getSimpleName()).append(") : ").append(toString(fieldValue, deepness - 1, tab + 1, lasts));
				if (!isLastField) {
					str.append(System.lineSeparator());
				} else {
					lasts.remove(index);
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return str.toString();
	}
	
	private static synchronized List<Field> getAllFields(Class<?> cl){
		List<Field> fields = new ArrayList<Field>();
		
		fields.addAll(Arrays.asList(cl.getDeclaredFields()));
		
		Class<?> superCl =	cl.getSuperclass();
		if(!superCl.equals(Object.class)){
			fields.addAll(getAllFields(superCl));
		}
		
		return fields;
	}
	
	private static synchronized boolean isPrimitiveOrWrapper(Class<?> clazz) {
		return clazz.isPrimitive() || wrapperPrimitiveMap.containsKey(clazz);
	}

	public static void main(String[] args) {
		System.out.println(toString(new ClassUtil()));
		
		
		System.out.println(toString(new Point(12,24))); 
		
		Polygon pol = new Polygon(new int[] { 10, 20, 30 }, new int[] { 20, 30, 40 }, 3);
		pol.getBounds();
		System.out.println(toString(pol, 2));
        }
}
