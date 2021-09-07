package Algorithms1;

//======================================Node===============================================
class Node1 {
	private int data;
	private Node1 next;

	public Node1(int d) {
		data = d;
		next = null;
	}
	public void setNext(Node1 n) {
		this.next = n;
	}
	public Node1 getNext() {
		return this.next;
	}

	public int getData() {
		return this.data;
	}
	public String toString() {
		return "" + this.data;
	}
}
//==================================CircleLinkedList=======================================

class CircleLinkedList {
	private Node1 head;
	private Node1 tail;
	private int size;

	public CircleLinkedList() { // Constructor : Build empty list
		head = null;
		tail = null;
		size = 0;
	}

	public Node1 getHead(){
		return this.head;
	}

	public void add(int n) {
		if (head == null) { // list is empty - add first node
			head = new Node1(n);
			tail = head;
		} else { // list is not empty - add next node
			Node1 oldTail = tail;
			tail = new Node1(n);
			oldTail.setNext(tail);
		}
		size++;
	}


	public void addLoop(int index) { // index - first loop's node
		Node1 current = head;
		while (current.getData() != index) {
			if (current.getNext() == null)
				return;
			current = current.getNext();
		}
		tail.setNext(current);
	}

	public String toString() {
		Node1 current = head;
		String result = "";
		int count = 0;
		while(current != null) {
			result += "[" + current.getData() + "]";
			current = current.getNext();
			count++;
			if (count % 10 == 0 || count % 16 == 0)
				result = result + " ";
			if (count == 20) // maybe linked list has a loop
				break;
		}
		return result;
	}

	public int getSize() {
		return size;
	}

	//		public static void main(String[] args){
	//			CircleLinkedList linearLL = new CircleLinkedList();
	//			for (int i = 1; i < 10; i++) // list size = 10
	//				linearLL.add(i);
	//			System.out.println("Linear Linked List size = "
	//					+ linearLL.getSize());
	//			System.out.println(linearLL.toString());
	//
	//			//****************************************************
	//
	//			CircleLinkedList circleLL = new CircleLinkedList();
	//			for (int i = 1; i < 10; i++) // list size = 10 circleLL.add(i);
	//				circleLL.add(i);
	//
	//			circleLL.addLoop(4); // first loop node = 4
	//			System.out.println("\nLoop Linked List size = " + circleLL.getSize());
	//			System.out.println(circleLL.toString());
	//		}
}

//=====================================Algorithms=========================================
public class TortoiseAndHareFloyd {

	//	    Node1 slow;
	//	    Node1 fast;
	CircleLinkedList dc;

	public TortoiseAndHareFloyd(CircleLinkedList dc){
		this.dc = dc;
		//	        this.slow = dc.;

	}

	//To verify the existence of the cycle (loop).
	public boolean hasCircle(Node1 first){
		//	        //Option 1
		//	        Node1 fast ,slow;
		//	        fast = slow = first;
		//
		//	        while(true){
		//	            if(slow.getNext() == null) return false;
		//	            if(fast.getNext() == null) return false;
		//
		//	            slow = slow.getNext();
		//	            fast = fast.getNext().getNext();
		//
		//	            if(slow == null || fast == null) return false;
		//	            if(slow.equals(fast)) return true;
		//	        }

		////Option 2
		int size = dc.getSize();
		Node1 temp = first;

		for(int i=1 ; i < size ; i++){
			if(first.getNext() == null) return false;
			first = first.getNext();
		}

		for(int i=1 ; i < size-1 ; i++){
			if(temp.equals(first.getNext())) return true;
			temp = temp.getNext();
		}

		return false;

	}

	//To find the meeting point.
	public Node1 meetingPoint(Node1 first){
		Node1 slow;
		Node1 fast;
		slow = fast = first;

		while(true){
			slow = slow.getNext();
			fast = fast.getNext().getNext();

			if(slow.equals(fast)) return slow;
		}
	}

	//To find the cycle (loop) starting point.
	public Node1 startCircle(Node1 first){
		Node1 slow = first;
		Node1 fast = meetingPoint(first);

		while(true){
			slow = slow.getNext();
			fast = fast.getNext();

			if(slow == fast) return slow;
		}

	}

	//To find the length of the cycle (loop).
	public int lengthCircle(){
		Node1 first = this.dc.getHead();
		Node1 start = startCircle(first);
		Node1 temp = start;
		int count=1;

		while(start.getNext() != temp){
			count++;
			start = start.getNext();
		}
		return count;
	}
	//=======================================Void==========================================

	public static void main(String[] args){
		CircleLinkedList cl1 = new CircleLinkedList();
		for(int i = 0 ; i < 5 ; i++){
			cl1.add(i);
		}
		cl1.addLoop(2);
		System.out.println(cl1);

		TortoiseAndHareFloyd test1 = new TortoiseAndHareFloyd(cl1);
		boolean ans = test1.hasCircle(cl1.getHead());
		System.out.println("hasCircle: "+ans);

		Node1 ans1 = test1.meetingPoint(cl1.getHead());
		System.out.println("meetingPoint: "+ans1.getData());

		Node1 ans2 = test1.startCircle(cl1.getHead());
		System.out.println("startCircle: "+ans2.getData());

		int ans3 = test1.lengthCircle();
		System.out.println("lengthCircle: "+ans3);
		System.out.println("blabla");
	}
}

