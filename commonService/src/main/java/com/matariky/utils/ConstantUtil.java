package com.matariky.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.matariky.constant.PermissionConstant;

public class ConstantUtil {
	public static List<Object> getAllConstantValuesByCategory(Class clazz, String category){
		List<Object> contantList =new ArrayList<Object>();
		Field[] fields = clazz.getDeclaredFields();
		Object obj;
		try {
			obj = clazz.newInstance();
			for (Field field : fields) {
				if(field.getName().startsWith(category)) {
					contantList.add(field.get(obj));
				}
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return contantList;
	}
	
	public static void main(String[] args) {
		List<Object> constants=ConstantUtil.getAllConstantValuesByCategory(PermissionConstant.class, "RESOURCE_TYPE");
		for(Object o :constants) {
			System.out.println(o);
		}
	}
}
