package structures;

import core.Drug;

public class MinHeap {
    private Drug[] heap;
    private int size;
    private int capacity;

    public MinHeap(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.capacity = capacity;
        this.heap = new Drug[capacity];
        this.size = 0;
    }

    public void insert(Drug drug) {
        if (drug == null) {
            System.out.println("Cannot insert null drug!");
            return;
        }
        
        if (size >= capacity) {
            System.out.println("Heap is full! Cannot insert more drugs.");
            return;
        }
        
        // Insert at the end
        heap[size] = drug;
        int current = size;
        size++;
        
        // Bubble up algorithm (heapify up)
        bubbleUp(current);
    }

    public Drug extractMin() {
        if (size == 0) {
            System.out.println("Heap is empty!");
            return null;
        }
        
        // Store the minimum element
        Drug min = heap[0];
        
        // Move last element to root
        heap[0] = heap[size - 1];
        heap[size - 1] = null; // Clear the last position
        size--;
        
        // Restore heap property if heap is not empty
        if (size > 0) {
            heapifyDown(0);
        }
        
        return min;
    }

    public Drug peekMin() {
        if (size == 0) {
            return null;
        }
        return heap[0];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isFull() {
        return size == capacity;
    }

    // Helper method for bubble up
    private void bubbleUp(int index) {
        while (index > 0) {
            int parentIndex = parent(index);
            
            // If current node's stock is greater than or equal to parent, stop
            if (heap[index].stock >= heap[parentIndex].stock) {
                break;
            }
            
            // Swap with parent
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    // Helper method for heapify down
    private void heapifyDown(int index) {
        while (hasLeftChild(index)) {
            int smallerChildIndex = leftChild(index);
            
            // Check if right child exists and is smaller than left child
            if (hasRightChild(index) && 
                heap[rightChild(index)].stock < heap[leftChild(index)].stock) {
                smallerChildIndex = rightChild(index);
            }
            
            // If current element is smaller than or equal to smallest child, stop
            if (heap[index].stock <= heap[smallerChildIndex].stock) {
                break;
            }
            
            // Swap with smaller child
            swap(index, smallerChildIndex);
            index = smallerChildIndex;
        }
    }

    // Index calculation methods
    private int parent(int index) { 
        return (index - 1) / 2; 
    }
    
    private int leftChild(int index) { 
        return 2 * index + 1; 
    }
    
    private int rightChild(int index) { 
        return 2 * index + 2; 
    }

    // Helper methods to check if children exist
    private boolean hasLeftChild(int index) {
        return leftChild(index) < size;
    }

    private boolean hasRightChild(int index) {
        return rightChild(index) < size;
    }

    // Swap method with bounds checking
    private void swap(int i, int j) {
        if (i < 0 || i >= size || j < 0 || j >= size) {
            System.out.println("Invalid swap indices: " + i + ", " + j);
            return;
        }
        
        Drug temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // Method to print heap for debugging
    public void printHeap() {
        System.out.println("Heap contents (size: " + size + "):");
        for (int i = 0; i < size; i++) {
            if (heap[i] != null) {
                System.out.println(i + ": " + heap[i].name + " (Stock: " + heap[i].stock + ")");
            }
        }
    }

    // Method to validate heap property (for debugging)
    public boolean isValidMinHeap() {
        for (int i = 0; i < size; i++) {
            if (hasLeftChild(i) && heap[i].stock > heap[leftChild(i)].stock) {
                return false;
            }
            if (hasRightChild(i) && heap[i].stock > heap[rightChild(i)].stock) {
                return false;
            }
        }
        return true;
    }
}
