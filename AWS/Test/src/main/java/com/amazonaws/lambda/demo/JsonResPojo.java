package com.amazonaws.lambda.demo;

import java.util.LinkedList;

public class JsonResPojo {
	LinkedList<DataPojo> sqlrow;

	public JsonResPojo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JsonResPojo(LinkedList<DataPojo> sqlrow) {
		super();
		this.sqlrow = sqlrow;
	}

	public LinkedList<DataPojo> getSqlrow() {
		return sqlrow;
	}

	public void setSqlrow(LinkedList<DataPojo> sqlrow) {
		this.sqlrow = sqlrow;
	}
	
}
