import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.List;





/**
 * Uses a binary search tree to store a database of
 * PlayStation users. Nodes are ordered by user unique key (see the
 * User class for more detail).
 * Created for Data Structures & Algorithms, UPEI
 * @author James Baumeister
 * @version 1.0
 */

public class BinaryTree {

    public User root = null;
    
    /**
     * Check if a user exists in the tree
     * @param current the root of subtree to search from
     * @param user to find in the tree
     * @return true if user exists in the tree
     */
    private boolean exists(User current, User user) {
    	if(user == null || current == null)
    		return false;
    	
    	if (user.getKey() == current.getKey()) {
			return true;
		}
    	else {
    		if(user.getKey() < current.getKey())
    			return exists(current.getLeft(), user);
    		else
				return exists(current.getRight(), user);
		}
    }

    /**
     * Making new friends is great. This method should add your new
     * bestie to your database (tree). Remember that they should be
     * added according to their key.
     * @param friend The friend to be added
     * @return true if  successfully added, false for all error cases
     * @throws IllegalArgumentException if friend is null
     */
    private boolean addToTree(User current, User friend) {
    	if(friend.getKey() < current.getKey()) {
    		if(current.getLeft() == null) {
				current.setLeft(friend);
				return true;
			}
			else 
				return addToTree(current.getLeft(), friend);
		}
		else {
			if(current.getRight() == null) {
				current.setRight(friend);
				return true;
			}
			else 
				return addToTree(current.getRight(), friend);
		}
    }
    public boolean beFriend(User friend) throws IllegalArgumentException {
    	if(friend == null)
    		throw new IllegalArgumentException();
    	if(root == null) {
			root = friend;
			return true;
    	}
    	if(exists(root, friend))
    		return false;
    	else
			return addToTree(root, friend);
    }

    /**
     * Sometimes friendships don't work out. In those cases it's best
     * to remove that "friend" altogether. This method should remove
     * all trace of that "friend" in the database (tree).
     * @param friend the "friend" to remove
     * @return true if successfully removed, false for all error cases
     * @throws IllegalArgumentException if "friend" is null
     */
    private User getRightMost(User current) {
        while (current.getRight() != null) {
            current = current.getRight();
        }
        return current;
    }
    private User removeFromTree(User current, User friend) {
    	if (current == null)
            return null;
 
        if (friend.getKey() < current.getKey())
        	current.setLeft(removeFromTree(current.getLeft(), friend));
        
        else if (friend.getKey() > current.getKey())
            current.setRight(removeFromTree(current.getRight(), friend));
 
        else {
            if (current.getLeft() == null)
                return current.getRight();
            else if (current.getRight() == null)
                return current.getLeft();
 

            User user = getRightMost(current.getLeft());
            current.setKey(user.getKey());
            current.setUsername(user.getUsername());
            current.setDob(user.getDob());
            current.setLevel(user.getLevel());
            current.setGames(user.getGames());
            current.setTrophies(user.getTrophies());
            current.setLeft(removeFromTree(current.getLeft(), friend));
        }
 
        return current;
    }
    public boolean deFriend(User friend) throws IllegalArgumentException {
    	if(friend == null)
    		throw new IllegalArgumentException();

    	if(exists(root, friend))
    		return removeFromTree(root, friend) != null;
    	else
			return false;
    }

    /**
     * In your quest to be the very best you need to know how many
     * of your friends are ranked higher than you. This method should
     * return the number of higher ranked users that the provided reference
     * user, or zero if there are none (woot!).
     * @param reference The starting point in the search
     * @return Number of higher ranked users or -1 if user not found
     * @throws IllegalArgumentException if reference is null
     */
    static ArrayList<Double> inorder = new ArrayList<>();

	void inOrder(User current) {
		if (current.getLeft() != null) {
			inOrder(current.getLeft());
		}
		
		inorder.add(current.getKey());
		
		if (current.getRight()!=null) {
			inOrder(current.getRight());
		}
		
	}

    public int countBetterPlayers(User reference) throws IllegalArgumentException {

    	 if (reference == null)
    		 throw new IllegalArgumentException();
    	 
    	 if(!exists(root, reference))
     		 return -1;
    	 
    	int c=0;

    	inOrder(root);
    	
 		for (int i = 0; i < inorder.size(); i++) {
 			if (inorder.get(i) == reference.getKey()) 
 				c = inorder.size()-i-1;
 		}
 		
 		return c;

    }

    /**
     * Bragging rights are well earned, but it's good to be sure that you're actually
     * better than those over whom you're lording your achievements. This method
     * should find all those friends who have a lower level than you, or zero if
     * there are none (you suck).
     * @param reference The starting point in the search
     * @return Number of lower ranked users
     * @throws IllegalArgumentException if reference is null
     * 
     * 
     */
    static ArrayList<Double> inorder2 = new ArrayList<>();

    void inOrder2(User current) {
		if (current.getLeft() != null) {
			inOrder2(current.getLeft());
		}
		
		inorder2.add(current.getKey());
		
		if (current.getRight()!=null) {
			inOrder2(current.getRight());
		}
		
	}

    public int countWorsePlayers(User reference) throws IllegalArgumentException {
   	 if (reference == null)
   		 throw new IllegalArgumentException();
   	 
   	 if(!exists(root, reference))
    		 return -1;
   	 
   	int c=0;
   	inorder2.clear();
	inOrder2(root);
	
		for (int i = 0; i < inorder2.size(); i++) {
			if (inorder2.get(i) == reference.getKey()) 
				c = i;
		}

		return c;

    }

    /**
     * The best player may not necessarily be measured by who has the highest level.
     * Platinum trophies are the holy grail, so it would be good to know who has the
     * most. This method should return the user with the highest number of platinum trophies.
     * @return the user with the most platinum trophies, or null if there are none
     */

  
    static ArrayList<User> trophiesUsers = new ArrayList<>();

    void inOrderTrophies(User current) {
		if (current.getLeft() != null) {
			inOrderTrophies(current.getLeft());
		}
		
		trophiesUsers.add(current);
		
		if (current.getRight()!=null) {
			inOrderTrophies(current.getRight());
		}
		
	}
    public User mostPlatinums() {
    	 if (root == null)
    		 throw new IllegalArgumentException();
   
    	 inOrderTrophies(root);
    	 Map<String, Integer> hm = new HashMap<String, Integer>();
    	 
    	 for (int i = 0; i < trophiesUsers.size(); i++) 
			hm.put(trophiesUsers.get(i).getUsername(), trophiesUsers.get(i).getPlat());
    	 
    	 int maxValueInMap=(Collections.max(hm.values())); 
    	 String str = null;
         for (Entry<String, Integer> entry : hm.entrySet()) {  
             if (entry.getValue()==maxValueInMap) {
                 str = entry.getKey();
                 break;
             }
         }
         
		for (int i = 0; i < trophiesUsers.size(); i++) {
			if (trophiesUsers.get(i).getUsername()==str) 
				return trophiesUsers.get(i);
			
		}
    	
        return null;
    }

    /**
     * You or one of your friends bought a new game! This method should add it to their
     * GameList.
     * @param username The user who has bought the game
     * @param game The game to be added
     */
    
    
    public void addGame(String username, Game game) throws IllegalArgumentException {
        if (username == null || game == null) {
                throw new IllegalArgumentException();
        }
        preOrderGame(root, username, game);
}

    private void preOrderGame(User subTree, String username, Game game) {
        if (subTree != null) {
                if (subTree.getUsername().equals(username)) {
                        subTree.getGames().addGame(game);
                }
                preOrderGame(subTree.getLeft(), username, game);
                preOrderGame(subTree.getRight(), username, game);
        }
}

    /**
     * You or one of your friends achieved a new trophy! This method should add it to
     * their trophies.
     * @param username The user who has earned a new trophy
     * @param trophy The trophy to be added
     */
    public void addTrophy(String username, Trophy trophy) throws IllegalArgumentException {
        if (username == null || trophy == null) {
                throw new IllegalArgumentException();
        }
        preOrderTrophy(root, username, trophy);
}

    private void preOrderTrophy(User subTree, String username, Trophy trophy) {
        if (subTree != null) {
                if (subTree.getUsername().equals(username)) {
                        subTree.getTrophies().add(trophy);
                }
                preOrderTrophy(subTree.getLeft(), username, trophy);
                preOrderTrophy(subTree.getRight(), username, trophy);
        }
}

    /**
     * You or one of your friends has gained one level! This is great news, except that
     * it may have ruined your tree structure! A node move may be in order.
     * @param username The user whose level has increased
     */
    public void levelUp(String username) throws IllegalArgumentException {
        if (username == null) {
                throw new IllegalArgumentException();
        }
        User user = null ;
        user = preOrderUsername(root, user, username);
        deFriend(user);
        if (user != null) {
                user.setLevel(user.getLevel() + 1);
        }
        beFriend(user);
    }

    private User preOrderUsername(User root2, User user, String username) {
        if (root2 != null) {
                if (root2.getUsername().equals(username)) {
                        user = root2;
                        return user;
                }
                user = preOrderUsername(root2.getLeft(), user, username);
                user = preOrderUsername(root2.getRight(), user, username);
        }
        return user;
    }


    /**
     * As your friends list grows, adding with regular binary tree rules will
     * result in an unbalanced and inefficient tree. One approach to fix this
     * is to implement an add method that uses AVL balancing. This method should
     * work in the same way as beFriend, but maintain a balanced tree according to
     * AVL rules.
     * @param friend The friend to be added
     * @return true if  successfully added, false for all error cases
     * @throws IllegalArgumentException if friend is null
     */
    
    
    public boolean addAVL(User friend) throws IllegalArgumentException {
        if (friend == null) {
                throw new IllegalArgumentException();
        }
        root = insert(friend, root);
        return true;
}

    private User insert(User friend, User root) {
        if (root == null) {
                root = friend;
        } else if (friend.getKey() - root.getKey() < 0) {
                root.setLeft(insert(friend, root.getLeft()));

                if (height(root.getLeft()) - height(root.getRight()) == 2) {
                        if ((friend.getKey() - root.getKey()) < 0) {
                                root = singleRotateLeft(root);
                        } else {
                                root = doubleRotateWithLeft(root);
                        }
                }
        } else if (friend.getKey() - root.getKey() > 0) {
                root.setRight(insert(friend, root.getRight()));

                if (height(root.getRight()) - height(root.getLeft()) == 2) {
                        if (friend.getKey() - root.getRight().getKey() < 0) {
                                root = doubleRotateWithRight(root);
                        } else {
                                root = singleRotateRight(root);
                        }
                }
        }

        	root.height = Math.max( height( root.getLeft() ), height( root.getRight() ) ) + 1;
        return root;
    	}

    private User doubleRotateWithRight(User x) {
        x.setRight(singleRotateLeft(x.getRight()));
        return singleRotateRight(x);
    }

    private User doubleRotateWithLeft(User x) {
        x.setLeft(singleRotateRight(x.getLeft()));
        return singleRotateLeft(x);
    }

    private User singleRotateRight(User w) {
        User x = w.getRight();

        w.setRight(x.getLeft());
        x.setLeft(w);

        x.height=Math.max(height(x.getLeft()),w.height)+1;
        w.height=Math.max(height(w.getLeft()),height(w.getRight()))+1;
        return x;
    }

    private User singleRotateLeft(User x) {
        User w = x.getLeft();
        x.setLeft(w.getRight());
        w.setRight(x);
        
        x.height=Math.max(height(x.getLeft()),height(x.getRight()))+1;
        w.height=Math.max(height(w.getLeft()),x.height)+1;
        return w;
    }

    public int height(User p){
    	return p == null ? -1 : p.height;
    }

    public int getMaxDepth(User root, User user) {
        if (root == null||root==user)
                return 0;
        else {
                int left = 0;
                int right =0;
                if(user.getKey()<root.getKey()) {
                        left = getMaxDepth(root.getLeft(),user);
                } else {
                        right = getMaxDepth(root.getRight(),user);
                }
                return 1 + Math.max(left, right);
        }
    }

    /**
     * A nice, neat print-out of your friends would look great in the secret scrap-book
     * that you keep hidden underneath your pillow. This method should print out the
     * details of each user, traversing the tree in order.
     * @return A string version of the tree
     */
	private void preOrderString(User root2, List<User> userList) {
    if (root2 != null) {
            preOrderString(root2.getLeft(), userList);
            userList.add(root2);
            preOrderString(root2.getRight(), userList);
    }
	}
	
    public String toString() {
    	 List<User> userList = new LinkedList<>();
         preOrderString(root, userList);

         String str = "";
         for (User user : userList) {
                 str += user.toString() + "\n";
         }
         return str.substring(0, str.length() - 1);
    }
}
