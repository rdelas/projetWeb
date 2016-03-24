package com.delas.common.tools.list;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ListUtil {

	public static final String toStringAsciiArray(List<?> list, Map<String, String> fields) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		StringBuilder str = new StringBuilder();
		
		String separator = "+";
		String leftAlignFormat = "|";
		
		Set<String> keys = fields.keySet();
		int[] sizes = new int[keys.size()];
		
		Iterator<String> iterator = keys.iterator();
		
		int it = 0;
		while(iterator.hasNext()){
			String key = iterator.next();
			sizes[it] = key.length();
			it++;
		}
		
		List<Object[]> data = new ArrayList<Object[]>();
		
		for (Object o : list) {
			Class<?> c = o.getClass();
			int i = 0;
			String[] values = new String[keys.size()];
			for (String key : keys) {
				Field field = c.getDeclaredField(fields.get(key));
				field.setAccessible(true);
				values[i] = field.get(o).toString();
				int fieldLength = values[i].length();
				
				if(fieldLength > sizes[i]){
					sizes[i] = fieldLength;
				}
				i++;
			}
			data.add(values);
		}
		
		
		for (int size : sizes) {			
			separator+="-";
			
			leftAlignFormat += " %-" + size + "s |";
			for (int i = 0; i < size; i++) {
				separator+="-";
			}
			separator+="-+";
		}
		separator+="%n";
		leftAlignFormat += "%n";
		
		str.append(String.format(separator));
		str.append(String.format(leftAlignFormat, keys.toArray()));
		str.append(String.format(separator));
		for (Object[] strings : data) {
			str.append(String.format(leftAlignFormat, strings));
		}
		
		str.append(String.format(separator));

		return str.toString();
	}

}
