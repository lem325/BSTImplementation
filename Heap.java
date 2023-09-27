import java.util.ArrayList;
public class Heap<E extends Comparable<E>>{
    private ArrayList<E> list;
     /**
    Heap initialization method
    @param none
    Makes root null and size 0
     */
    public Heap(){
        list = new ArrayList<>();
    }
 /**
    size method
    @param none
    returns size of heap
     */
    public int size(){
        return list.size();
    }
 /**
    isEmpty method
    @param none
    returns t/f if size is 0 or nonzero
     */
    public boolean isEmpty(){
        return list.isEmpty();
    }
      /**
    clear method
    @param none
    clears list
     */
    public void clear(){
        list.clear();
    }
//really simplr toString override
    public String toString(){
        return list.toString();
    }

    /**
    contains method
    @return int 
    @param item E
    returns iterations to find if an item is contained in heap
     */
    public int contains(E value){//O(log n) - O(n)
        int iterations=0;
        for(int i=0; i<list.size(); i++){
            iterations++;
            if(list.get(i).equals(value)){
                return iterations;
            }
        }
        return iterations;
    }

/**
    Add method
    @return int 
    @param value E
    returns iterations to for adding to heap
     */
    public int add(E value){ 
        int iterations=0;
        list.add(value);
        int currentIndex = list.size()-1;

        while(currentIndex>0){
            iterations++;
            int parentIndex = (currentIndex-1)/2;
            E current = list.get(currentIndex);
            E parent = list.get(parentIndex);
            if(current.compareTo(parent)>0){
                list.set(currentIndex, parent);
                list.set(parentIndex, current);
            }
            else{
                break;
                }
                currentIndex = parentIndex;
        }
        return iterations;
    }
/**
    Remove method
    @return int 
    @param none
    returns iterations to remove item from heap
     */
    public int remove(){
        int iterations=0;
        if(list.size()==0){
            return iterations;
        }
        E removedItem = list.get(0);
        list.set(0, list.get(list.size()-1));
        list.remove(list.size()-1);
        int currentIndex =0;
        while(currentIndex < list.size()){
            iterations++;
            int left = 2 * currentIndex + 1;
            int right = 2 * currentIndex + 2;
            if(left>= list.size()){
                break;//no leftchild
            }
            int maxIndex = left;
            E max = list.get(maxIndex);
            if(right < list.size()){
                if(max.compareTo(list.get(right))<0){
                    maxIndex = right;
                }
            }
            E current = list.get(currentIndex);
            max = list.get(maxIndex);
            if(current.compareTo(max)<0){
                list.set(maxIndex, current);
                list.set(currentIndex, max);
                currentIndex = maxIndex;
            }
            else{
                break;
            }
        
        }
        return iterations;
    }
/**
 height method
 @return int
 @param none
 */
    public int height(){//O(n)
        return height(0);
    }
/**
 height method
 @return int
 @param node int
 */
    public int height(int node){//index of the node O(n)
        if(node < list.size()){
            int lHeight = height(2 * node+1);
            int rHeight = height(2*node+2);
            return 1 + Math.max(lHeight, rHeight);
        }
        return 0;
    }
/**
 isBalanced method
 @return int
 @param none
 */
    public boolean isBalanced(){//O(n^2)
        return isBalanced(0);
    }
/**
 is balanced method
 @return int
 @param node TreeNode
 */
    public boolean isBalanced(int node){//O(n^2)
        if(node >= list.size()){
            return true;
        }
        int lHeight=height(2 * node + 1); //O(n)
        int rHeight=height(2 * node +2); //O(n)
        int diff = Math.abs(lHeight - rHeight);
        if(diff >1){
            return false;
        }
        
        return isBalanced(2* node+1) && isBalanced(2*node+2);
    }

}