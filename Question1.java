package yishouyun;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ValueNode;

public class Question1 {
	public static Map<String, String> jsonMap = new HashMap<String, String>();
	  private static void addKeys(String currentPath, JsonNode jsonNode) {
		// for object, use recursive methods to get Prefix of name and value
		  if (jsonNode.isObject()) {	
	      ObjectNode objectNode = (ObjectNode) jsonNode;
	      Iterator<Map.Entry<String, JsonNode>> iter = objectNode.fields();
	      String pathPrefix = currentPath.isEmpty() ? "" : currentPath + ".";

	      while (iter.hasNext()) {
	        Map.Entry<String, JsonNode> entry = iter.next();
	        addKeys(pathPrefix + entry.getKey(), entry.getValue());
	      }
	    }
		  // for array and value, just put into map
		  else if (jsonNode.isArray()) {
	      ArrayNode arrayNode = (ArrayNode) jsonNode;
//	       nList is the attribute name of object
//	       n is the number of attribute
	      LinkedList nList=new LinkedList();
	      int n=0;
	      if(arrayNode.get(0).isObject()) {
	    	  for(int i=0;i< arrayNode.size();i++) {
	    		  Iterator<Map.Entry<String, JsonNode>> iter2 = arrayNode.get(i).fields();
		    	  while (iter2.hasNext()) {
		  	        Map.Entry<String, JsonNode> entry = iter2.next();
		  	        if(i==0) {
		  	        	nList.add(entry.getKey());
		  	        	n++;
		  	        }
		  	        
		  	      }
	    	  }
	    	  
//	    	  For each attribute, I build a list to store all value for this attribute
//	    	  Then, put attribute name and list value to map
	    	  for(int j=0;j<n;j++) {
	    		  ArrayList buff=new ArrayList();
	    		  for(int i=0;i< arrayNode.size();i++) {
		    		  Iterator<Map.Entry<String, JsonNode>> iter3 = arrayNode.get(i).fields();
			    	  while (iter3.hasNext()) {
			  	        Map.Entry<String, JsonNode> entry = iter3.next();
			  	        if(nList.get(j).equals(entry.getKey())) {
			  	        	buff.add(arrayNode.get(i).get(entry.getKey()));
			  	        }
			  	        
			  	        
			  	      }
		    	  }
//	    		  printList(buff);
	    		  jsonMap.put(currentPath+"."+nList.get(j), buff.toString());
	    	  }  
	      }else {
	    	  jsonMap.put(currentPath, arrayNode.toString());
	      }
	    } else if (jsonNode.isValueNode()) {
	      ValueNode valueNode = (ValueNode) jsonNode;
	      jsonMap.put(currentPath, valueNode.asText());
	    }
	  }
//	  print map
	  public static void printMap(Map <String, String> map) {
		  for(Map.Entry<String, String> entry:map.entrySet()) {
			  System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
		  }
		
	  }
//	  print list
	  public static void printList(List list) {
		  Iterator iter=list.iterator();
		  while(iter.hasNext()) {
			  System.out.println(iter.next());
		  }
	  }
//	   create jsonNode tree from string
	  public static void createJsonNode(String str) {
		  	JSONParser parser=new JSONParser();
			ObjectMapper mapper=new ObjectMapper();
			JsonNode root=null;
			try {
				root = mapper.readTree(str);	//build root of jsonNode
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			addKeys("", root);
			
			
	  }
	  // print all jsonNode
	  public static void traverseJson(JsonNode jsonNode) {  
		    Iterator<Entry<String, JsonNode>> jsonNodes = jsonNode.fields();  
		    while (jsonNodes.hasNext()) {  
		        Entry<String, JsonNode> node = jsonNodes.next();  
		        System.out.println(node.getKey());  
		        System.out.println(node.getValue().toString());  
		    }  
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "{ \"a\" : 1, \"b\" : { \"c\" : 2, \"d\": [3, 4] }}";
		String str2 = "{\"data\":{\"hasnext\":0,\"info\":[{\"id\":\"288206077664983\",\"timestamp\":1371052476},{\"id\":\"186983078111768\",\"timestamp\":1370944068},{\"id\":\"297031120529307\",\"timestamp\":1370751789},{\"id\":\"273831022294863\",\"timestamp\":1369994812}],\"timestamp\":1374562897,\"totalnum\":422},\"errcode\":0,\"msg\":\"ok\",\"ret\":0,\"seqid\":5903702688915195270}"; 
//		you can also try str2
		createJsonNode(str);
		printMap(jsonMap);
	}

}
