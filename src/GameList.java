import java.text.SimpleDateFormat;

/**
 * Class to represent a single linked-list of Database.Game objects.
 * Created for Data Structures & Algorithms, UPEI
 * @author James Baumeister
 * @version 1.0
 */
public class GameList {

    public Game head;

    public GameList(Game head) {
    	this.head = head;
    }

    public String toString() {
    	if (head == null) 
    		return "Empty game list";
    	
    	Game currNode = head;
        StringBuilder strBuilder = new StringBuilder();
        while (currNode != null) {
        	if (currNode.getNext() == null) 
        		strBuilder.append(currNode.toString());
			else 
				strBuilder.append(currNode.toString()+"\n");
            currNode = currNode.getNext();
        }
        return strBuilder.toString();

    }

    public void addGame(Game game) {
    	if (game == null) 
    		throw new IllegalArgumentException();
    	
    	 Game current = head;
         while(current.getNext() != null) {
             current = current.getNext();
         }
         current.setNext(game);
         head = current;
    }

    public Game getGame(String name) {
    	if (name == null) 
    		throw new IllegalArgumentException();
    	
    	  Game current = head;  
          int i = 1;  
          boolean state = false;
          if(head == null) {  
              return null;  
          }  
          else {  
              while(current != null) {  
                  if(current.getName() == name) {  
                      state = true;  
                      break;  
                  }  
                  i++;  
                  current = current.getNext();  
              }  
          } 
          
          
          if(state)  
        	  return current; 
          
         else
        	 return null;
        	 
    }
    
     String printList(){
        Game currNode = head;
        StringBuilder strBuilder = new StringBuilder();
        while (currNode != null) {
            strBuilder.append(currNode.toString()+"\n");
            currNode = currNode.getNext();
        }
        return strBuilder.toString();
    }

     public void removeGame(String game) {
    	 if (game == null) 
    		 throw new IllegalArgumentException();
//         try {
//             if (game == null) {
//                 throw new IllegalArgumentException();
//                 
//                 Game ptr = head;
//                 Game preptr = head;
//                 if(head.data == game) {
//                     ptr = head->next;
//                     delete(head);
//                     head = ptr;
//                 }
//                 else {
//                     while(ptr->data != game) {
//                         preptr = ptr;
//                         ptr = ptr->next;
//                     }
//                     preptr.setNext(ptr.getNext());
//                     delete(ptr);
//                 }
//             }
//         }
//         catch(IllegalArgumentException e)
//         {
//             System.out.println("No Such Game Found");
//         }
         
     }

    public void removeGame(Game game) {
    	
    }

    private void removeNext(Game game) {

    }

}
