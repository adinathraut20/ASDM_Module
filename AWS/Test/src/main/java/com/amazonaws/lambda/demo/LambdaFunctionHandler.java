package com.amazonaws.lambda.demo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


public class LambdaFunctionHandler implements RequestHandler<QueryParser, JsonResPojo> {

	

	public JsonResPojo handleReadRequest(QueryParser request, Context context) {

		// context.getLogger().log("Input: " + input);

		System.out.println("Message from Event Input  : " + request.getQuery());

		LinkedList<DataPojo> result = JdbcConnector.runTestQuery(JdbcConnector.getCon(), request.getQuery());
		System.out.println("result" + result);

		return new JsonResPojo(result);
	}
	
	public Response handleCreateRequest(QueryParser request, Context context) {

		// context.getLogger().log("Input: " + input);

		System.out.println("Message from Event Input  : " + request.getQuery());

		int result = JdbcConnector.insertQuery(JdbcConnector.getCon(), request.getQuery());
		System.out.println("result" + result);
		  Map<String, String> headers = new HashMap<>();
		  headers.put("Access-Control-Allow-Origin", "*");
		  headers.put("Content-Type", "application/json");
		  headers.put("Access-Control-Allow-Headers" , "Content-Type");
		  headers.put("Access-Control-Allow-Methods", "OPTIONS,POST,GET");
		  return new Response(200, headers, "" + result);
	}
	
	public JsonResPojo handleUpdateRequest(QueryParser request, Context context) {

		// context.getLogger().log("Input: " + input);

		System.out.println("Message from Event Input  : " + request.getQuery());

		LinkedList<DataPojo> result = JdbcConnector.updateQuery(JdbcConnector.getCon(), request.getQuery());
		System.out.println("result" + result);

		return new JsonResPojo(result);
	}
	
	public JsonResPojo handleDeleteRequest(QueryParser request, Context context) {

		// context.getLogger().log("Input: " + input);

		System.out.println("Message from Event Input  : " + request.getQuery());

		LinkedList<DataPojo> result = JdbcConnector.deleteQuery(JdbcConnector.getCon(), request.getQuery());
		System.out.println("result" + result);

		return new JsonResPojo(result);
	}

	@Override
	public JsonResPojo handleRequest(QueryParser input, Context context) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
