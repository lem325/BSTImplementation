import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class AnimalTree{
    public static void main(String []args){
        ArrayList<String> animalAL = new ArrayList<>();
        BST<String> animalBST = new BST<>();
        Heap<String> animalHeap = new Heap<>();
        System.out.println("Testing add: ");
        readFile(animalAL, animalBST, animalHeap, "animals.txt");
        testContains(animalAL, animalBST, animalHeap);
        testRemove(animalAL, animalBST, animalHeap);

        System.out.println("Height(BST): " + animalBST.height());
        System.out.println("Height(Heap): " + animalHeap.height());
        System.out.println("isBalanced(BST)? " + animalBST.isBalanced());
        System.out.println("isBalanced(Heap)? " + animalHeap.isBalanced());

//When I ran this, I got the BST height as 17 and the Heap height as 9
// I got that BST was not balanced, while heap was balanced

//The balance corresponds to if there are nodes with height differences greater than 1, and with the BST height being 17 it makes sense that it is unbalanced.

    }

    public static void readFile(ArrayList<String> al, BST<String> bst, Heap<String> heap, String filename){
        File file = new File(filename);
        try{
            Scanner read = new Scanner(file);
            int count =0;
            int totalIterBST = 0;
            int  totalIterHeap =0;

            while(read.hasNextLine()){
                String line = read.nextLine();
                al.add(line);
                int bstIter = bst.add(line);
                int heapIter = heap.add(line);
                if(count % 24 ==0){
                    System.out.printf("%-20s\t%-5d\t%-5d\n", line, bstIter, heapIter);
                }
                count++;
            }
            System.out.printf("%-20s\t%-5d\t%-5d\n", "Average", totalIterBST/al.size(), totalIterHeap/al.size());
            read.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found.");
            System.exit(0);
        }
    }

    public static void testContains(ArrayList<String> al, BST<String> bst, Heap<String> heap){
        int totalBST=0, totalHeap=0;
        System.out.println("Testing contains: ");
        System.out.printf("%-20s\t%-5s\t%-5s\n", "Animal Name", "BST", "Heap");
        for(int i=0; i<20; i++){
            int randomIndex = (int)(Math.random()*al.size());
            String animalName = al.get(randomIndex);
            int bstIter = bst.contains(animalName);
            int heapIter = heap.contains(animalName);
            totalBST += bstIter;
            totalHeap += heapIter;
            System.out.printf("%-20s\t%-5d\t%-5d\n", animalName, bstIter, heapIter);
        }
        System.out.printf("%-20s\t%-5d\t%-5d\n", "Average", totalBST/20, totalHeap/20);

    }

    public static void testRemove(ArrayList<String> al, BST<String> bst, Heap<String> heap){
        int totalBST=0, totalHeap=0;
        System.out.println("Testing remove: ");
        System.out.printf("%-20s\t%-5s\t%-5s\n", "Animal Name", "BST", "Heap");
        for(int i=0; i<20; i++){
            int randomIndex = (int)(Math.random()*al.size());
            String animalName = al.get(randomIndex);
            int bstIter = bst.remove(animalName);
            int heapIter = heap.remove();
            totalBST += bstIter;
            totalHeap += heapIter;
            System.out.printf("%-20s\t%-5d\t%-5d\n", animalName, bstIter, heapIter);
        }
        System.out.printf("%-20s\t%-5d\t%-5d\n", "Average", totalBST/20, totalHeap/20);
    }

}