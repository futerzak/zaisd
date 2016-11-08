package lab01;

public class List {
	int N = 0;
	Node first = null;
	Node last = null;
		
	public boolean isEmpty(){
		return first == null;
	}
	
	public int size(){
		return N;
	}
	
	public void add(int node, int weight){
		Node newNode = new Node();
		newNode.nodeID = node;
		newNode.edgeWeidht = weight;
		N++;
		if(isEmpty()){
			newNode.next = null;
			newNode.prev = null;
			first = newNode;
			last = newNode;
		}else{
			last.next = newNode;
			newNode.prev = last;
			newNode.next = null;
			last = newNode;
		}
	}
	
	public void delete(int node){
		Node current = first;
		while(current != null){
			if(current.nodeID == node){
				N--;
				if(current == first){
					first = current.next;
					current.next.prev = null;
				}else if(current == last){
					last = current.prev;
					current.prev.next = null;
				}else{
					current.prev.next = current.next;
					current.next.prev = current.prev;
				}
				current = null;
			}else{
				current = current.next;
			}
		}
	}
	
	public Node findNode(int node){
		Node current = first;
		while(current != null){
			if(current.nodeID == node){
				return current;
			}else{
				current = current.next;
			}					
		}
		return current;
	}

	public Node get(int i) {
		Node current = first;
		for(int n = 0; n < i; n++){
			current = current.next;
		}
		return current;
	}
	
	
}