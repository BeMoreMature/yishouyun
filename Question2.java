package yishouyun;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class index{	//customer class for store the index
	public int row;
	public String colunmn;
	public index(int r, String c) {
		this.row=r;
		this.colunmn=c;
	}
	public int getRow() {
		return this.row;
	}
	public String getColumn() {
		return this.colunmn;
	}
	public void setRow(int r) {
		this.row=r;
	}
	public void setColumn(String c) {
		this.colunmn=c;
	}
}
public class Question2 {
	  public static String store(Map <index,String> map) {
		  Set set=new HashSet();
		  String res="";
		  //  get unique row number
		  for(Map.Entry<index, String> entry:map.entrySet()) {
			  set.add(entry.getKey().getRow());
		  }
		  for(int i=0;i<set.size();i++) {
			  int rr=(int) set.toArray()[i];	//  row number
			  //  for each row, if some elements in map have the same row number,
			  //  we add it to the same row and end in ";"
			  for(Map.Entry<index, String> entry:map.entrySet()) {
				  if(entry.getKey().getRow()==rr) {
					  res+=entry.getKey().getColumn()+"="+entry.getValue()+";";
					 
				  }
			  }
			  //  for each row, end in "\n"
			  res = res.substring(0, res.length()-1);
			  res+="\n";
		  }
		  return res;
	  }
	  public static Map<index,String> load(String str) {
		  String []s=str.split("\n");
		  Map <index,String> map=new HashMap<index,String>();
		  for(int i=0;i<s.length;i++) {
			  //  for each row, check if the number of k-v pairs is more than one
			  //  if true, split it by ";", then split k-v by "="
			  if(s[i].contains(";")){
				  String []buff=s[i].split(";");
				  for(int j=0;j<buff.length;j++) {
					  String kv[]=buff[j].split("=");
					  map.put(new index(i,kv[0]), kv[1]);
				  }
			  }
			  // if false, directly split it by "="
			  else {
				  String kv[]=s[i].split("=");
				  map.put(new index(i,kv[0]), kv[1]);
			  }
		  }
		  return map;
	  }
		
	  //  print map
	  public static void printMap(Map <index, String> map) {
		  for(Map.Entry<index, String> entry:map.entrySet()) {
			  System.out.println("Row = " + entry.getKey().getRow() +", Key = " +entry.getKey().getColumn()+ ", Value = " + entry.getValue()); 
		  }
		
	  }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str="key1=value1;key2=value2\nkeyA=valueA\n";
		Map <index,String> map=new HashMap<index,String>();
		
		map.put(new index(0,"key1"), "value1");
		map.put(new index(0,"key2"), "value2");
		map.put(new index(1,"keyA"), "valueA");
		
		
		//	result of store the HashMap to string
		String text=store(map);
		System.out.println(text);
		//	result of load string to HashMap
		Map<index,String> a=load(str);
		printMap(a);
	}

}
