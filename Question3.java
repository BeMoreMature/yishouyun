package yishouyun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Question3 {
	private static int max=0;		// store the max sum weight
	private static VNode[] head;	// store the head elements
	private  static List<List> res= new ArrayList<List>();
	private class ENode{			// adjacent table node
		int index;		// the index of char in the head table
		ENode nextEdge;
		int w;
	}
	private class VNode{		// head table node
		char data;
		ENode firstEdge;
		int w;
	}
	// construct the head table with index and weight
	// construct the adjacent table according to the edges
	public void buildGraph(char points[][], char edges[][]) {
		int vlen=points.length;
		int elen=edges.length;
			
		head=new VNode[vlen];
		// construct the head table
		for(int i=0;i<head.length;i++) {
			head[i]=new VNode();
			head[i].data=points[i][0];
			head[i].w=Character.getNumericValue(points[i][1]);
			head[i].firstEdge=null;
		}
		// construct adjacent table
		for(int i=0;i<elen;i++) {
			char start=edges[i][0];
			char end=edges[i][1];
			
			int sp=getPosition(start);
			int ep=getPosition(end);
			
			ENode enode=new ENode();
			enode.index=ep;
			enode.w=head[ep].w;
			if(head[sp].firstEdge == null) {
				head[sp].firstEdge =enode;	// if null, add new node
			}
			else {
				linkLast(head[sp].firstEdge,enode);	// if not, add new node to the end
			}
		}
	}
	public void DFS(VNode node, char[][]points) {
		
		ENode enode=node.firstEdge;
		// iterate all entrance of the root node
		while(enode!=null) {
			// for each new entrance, record the path and count again
				LinkedList<Character> path=new LinkedList<Character>();
				int count=0;
				count+=node.w;
				path.add(node.data);
				
				helper(enode,path,count);
				enode=enode.nextEdge;
			
		}
	}
	public static void helper(ENode node,List path,int count) {
		if(node==null) {
			if(count>max) {		// if current path weight more than max, 
								// keep the path and max into record
				res.add(path);
				max=count;
			}
			
			return;
		}
		for(int i=0;i<head.length;i++) {
			if(node.index==i) {		// recursive calculate the path and count
				path.add(head[i].data);
				count+=head[i].w;
				helper(head[i].firstEdge,path,count);
			}
		}
	}
	// get index of the char
	private int getPosition(char ch) {		
        for(int i=0; i<head.length; i++)
            if(head[i].data==ch)
                return i;
        return -1;
    }
	// add the node to the end 
	private void linkLast(ENode list, ENode node) {
        ENode p = list;

        while(p.nextEdge!=null)
            p = p.nextEdge;
        p.nextEdge = node;
    }
	 public void print() {
	        System.out.printf("List Graph:\n");
	        for (int i = 0; i < head.length; i++) {
	            System.out.printf("%c: ",head[i].data);
	            ENode node = head[i].firstEdge;
	            while (node != null) {
	                System.out.printf("%d(%c) ", node.w, head[node.index].data);
	                node = node.nextEdge;
	            }
	            System.out.printf("\n");
	        }
	    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char [][]points=new char[][] {
			{'A','1'},
			{'B','2'},
			{'C','2'}
		};
		
		char [][]edges=new char[][] {
			{'A','B'},
			{'B','C'},
			{'A','C'}
		};
		Question3 q=new Question3();
		q.buildGraph(points,edges);
		q.print();
		
		q.DFS(q.head[0],points);

		System.out.println(res.get(res.size()-1));
		System.out.println("sum of weigth is "+max);
	}

}
