public class List<E extends Data<E>> implements ListInterface<E>{
	
	private int numberItems;
	private Node lastItem;
	private Node currentItem;
	private Node firstItem;
	
	List(){
		numberItems = 0;
		firstItem = null;
		lastItem = null;
		currentItem = null;	
	}
	
	public boolean isEmpty(){
		if (numberItems != 0){
			return false;
		}else{
			return true;
		}
	}
	
	@Override
	public List<E> init() {
		numberItems = 0;
		firstItem = null;
		lastItem = null;
		currentItem = null;
		return this;
	}
	
	
	@Override
	public int size() {
		return numberItems;
	}
	
	
	@Override
	public ListInterface<E> insert(E d) {

		if (numberItems == 0){
			firstItem = lastItem = currentItem = new Node(d, null, null);
			numberItems = numberItems + 1;
			
		} else if ((firstItem.data.compareTo(d)) >= 0) {
            currentItem = new Node(d, null, firstItem);
            firstItem = currentItem;
            currentItem.next.prior = currentItem;
            numberItems = numberItems + 1;
            
        } else if (lastItem.data.compareTo(d) <= 0){
            currentItem = new Node (d, lastItem, null);
            lastItem = currentItem;
            currentItem.prior.next = currentItem;
            numberItems = numberItems + 1;
            
        } else {
        	goToFirst();
        	while(currentItem.next.data.compareTo(d) <= 0){
        		goToNext();
        	}
            currentItem = new Node(d, currentItem, currentItem.next);
            currentItem.next.prior = currentItem;
            currentItem.prior.next = currentItem;
            numberItems = numberItems +1;
        }   
        return this;
	}
	
	
	public E retrieve() {
		if(numberItems == 0){
			return null;
		}else{
			return currentItem.data.clone();
		}
	}
	@Override
	public ListInterface<E> remove() {
		if(numberItems == 0){
			init();
		}
		else if(numberItems == 1){
			init();
		}else if (currentItem == firstItem){
			currentItem.next.prior = null;
			currentItem = currentItem.next;
			firstItem = currentItem;
			numberItems = numberItems -1;
		}else if (currentItem == lastItem){
			currentItem.prior.next = null;
			currentItem = currentItem.prior;
			lastItem = currentItem;
			numberItems = numberItems -1;
		}else{
			currentItem.prior.next = currentItem.next;
			currentItem.next.prior = currentItem.prior;
			currentItem = currentItem.next;
			numberItems = numberItems -1;
		}
		return this;
	}
	
	@Override
	public boolean find(E d) {
		currentItem = firstItem;
		if(numberItems == 0){
			currentItem = new Node(null, null, null);
		}
		if(numberItems > 0){
			for(int i = 0; i < this.size(); i++){
				if(d.compareTo(currentItem.data) == 0){
					return true;
				}
				else if (d.compareTo(currentItem.data) > 0){
					currentItem = currentItem.next;
				}
				else if (d.compareTo(currentItem.data) < 0){
					return false;
				}
				else{
					return false;
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean goToFirst() {
		if(numberItems == 0){
			return false;
		} else if(firstItem == currentItem){
			return true;
		} else {
			currentItem = firstItem;
			return true;
		}
	}
	@Override
	
	public boolean goToLast() {
		if(numberItems == 0){
			return false;
		} else if(lastItem == currentItem){
			return true;
		} else {
			currentItem = lastItem;
			return true;
		}
	}
	@Override
	public boolean goToNext() {
		if(numberItems == 0){
			return false;
		} else if(currentItem == lastItem){
			return false;
		} else {
			currentItem = currentItem.next;
			return true;
		}
	}
	@Override
	public boolean goToPrevious() {
		if(numberItems == 0){
			return false;
		} else if(currentItem == firstItem){
			return false;
		} else {
			currentItem = currentItem.prior;
			return true;
		}
	}
	
	@Override
	public ListInterface<E> clone() {
		return null;
	}
	
	private class Node {

	    E data;
	    Node prior,
	         next;

	    public Node(E d) {
	        this(d, null, null);
	    }

	    public Node(E data, Node prior, Node next) {
	        this.data = data == null ? null : data;
	        this.prior = prior;
	        this.next = next;
	    }

	}
	
}