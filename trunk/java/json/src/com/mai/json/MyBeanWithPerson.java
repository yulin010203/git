package com.mai.json;

import java.util.List;
import java.util.Map;

public class MyBeanWithPerson {

	private List<Person> list = null;
	private Map<String,Person> map = null;
	
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	
	
}
