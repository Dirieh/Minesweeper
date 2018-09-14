//Name: Dirieh Mahdi Ali
//ID: 300017745
//ITI-1121 Section: C
public class GenericArrayStack<E> implements Stack<E> {
   
   // ADD YOUR INSTANCE VARIABLES HERE
    private E[] elems;
    private int top;

   // Constructor
    @SuppressWarnings("unchecked")
    public GenericArrayStack( int capacity ) {
        
    // ADD YOU CODE HERE

        elems = (E[])(new Object[capacity]);
        top = 0;


    }

    // Returns true if this ArrayStack is empty
    public boolean isEmpty() {
        
    // ADD YOU CODE HERE
        return top == 0;


    }

    public void push( E elem ) {
        
    // ADD YOU CODE HERE
        elems[top] = elem;
        top++;


    }
    public E pop() {
        
    // ADD YOU CODE HERE
        if (top == -1){
            return null;
        }
        top--;
        E saved = (E)elems[top];
        elems[top] = null;
        return saved;

    }

    public E peek() {
        
    // ADD YOU CODE HERE
        return elems[top - 1];
    }
}
