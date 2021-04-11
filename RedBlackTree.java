import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class RedBlackTree 
{
	private RedBlackNode root;
	
	public RedBlackTree()
	{
		root = null;
	}
	
	public void add(Comparable val)
	{
		if(root == null)
			root = new RedBlackNode(val);
		else
			root = add(val, root);
		root.setColor(1);
	}
	
	public void add(RedBlackNode val)
	{
		add(val.getValue());
	}
	
	public RedBlackNode rotateLeft(RedBlackNode p, RedBlackNode c)
	{
		RedBlackNode temp = c.getLeft();
		c.setLeft(p);
		p.setRight(temp);
		return c;
	}
	
	public RedBlackNode rotateRight(RedBlackNode p, RedBlackNode c)
	{
		RedBlackNode temp = c.getRight();
		c.setRight(p);
		p.setLeft(temp);
		return c;
	}
	
	int i = 1;
	
	public RedBlackNode add(Comparable val, RedBlackNode node)
	{
		if(node.getValue() == null)
		{
			return new RedBlackNode(val);
		}
		
		int compareVal = val.compareTo(node.getValue());
		colorSwap(node);
		
		if(compareVal < 0)
			node.setLeft(add(val, node.getLeft()));
		else if(compareVal > 0)
			node.setRight(add(val, node.getRight()));
		
		if(leftleft(node))
		{
			node = rotateRight(node, node.getLeft());
			node.getRight().setColor(0);
			node.setColor(1);
			
		}
		else if(rightright(node))
		{
			node = rotateLeft(node, node.getRight());
			node.getLeft().setColor(0);
			node.setColor(1);
		}
		else if(leftright(node))
		{
			node.setLeft(rotateLeft(node.getLeft(), node.getLeft().getRight()));
			node = rotateRight(node, node.getLeft());
			node.getRight().setColor(0);
			node.setColor(1);
		}
		else if(rightleft(node))
		{
			node.setRight(rotateRight(node.getRight(), node.getRight().getLeft()));
			node = rotateLeft(node, node.getRight());
			node.getLeft().setColor(0);
			node.setColor(1);
		}
		return node;
	}
	
	public void colorSwap(RedBlackNode node)
	{
		if(node.getColorNum() == 1 && node.getRight().getColorNum() == 0 && node.getLeft().getColorNum() == 0)
		{
			node.setColor(0);
			node.getRight().setColor(1);
			node.getLeft().setColor(1);
		}
	}
	
	public boolean leftleft(RedBlackNode node)
	{
		if(node.getLeft().getValue() == null || node.getLeft().getLeft().getValue() == null)
			return false;
		else if(node.getColorNum() == 1 && node.getLeft().getColorNum() == 0 && node.getLeft().getLeft().getColorNum() == 0)
			return true;
		return false;
	}
	
	public boolean leftright(RedBlackNode node)
	{
		if(node.getLeft().getValue() == null || node.getLeft().getRight().getValue() == null)
			return false;
		else if(node.getColorNum() == 1 && node.getLeft().getColorNum() == 0 && node.getLeft().getRight().getColorNum() == 0)
			return true;
		return false;
	}
	
	public boolean rightright(RedBlackNode node)
	{
		if(node.getRight().getValue() == null || node.getRight().getRight().getValue() == null)
			return false;
		else if(node.getColorNum() == 1 && node.getRight().getColorNum() == 0 && node.getRight().getRight().getColorNum() == 0)
			return true;
		return false;
	}
	
	public boolean rightleft(RedBlackNode node)
	{
		if(node.getRight().getValue() == null || node.getRight().getLeft().getValue() == null)
			return false;
		else if(node.getColorNum() == 1 && node.getRight().getColorNum() == 0 && node.getRight().getLeft().getColorNum() == 0)
			return true;
		return false;
	}
	
	public String fullLevelOrder()
	{
		return fullLevelOrder(Math.min(getNumLevels(),6));
	}
	
	public String fullLevelOrder(int lol)
	{
		String str = "";
		for(int i = 0; i < lol; i++)
			str += printFullLevel(i, root) +  "\n";
		return str;
	}
	
	public int getNumLevels()
	{
		return getNumLevels(root);
	}
	
	public int getNumLevels(RedBlackNode node)
	{
		
        if(node.getValue() == null)
            return 0;

        return 1 + Math.max(getNumLevels(node.getLeft()), getNumLevels(node.getRight()));
	}
	
	public String printFullLevel(int level, RedBlackNode node)
	{
		if(node == null)
		{
			if(level > 0)
				return printFullLevel(level-1, null) + printFullLevel(level-1, null);
			return "null|";
		}
		if(level == 0)
			return node.getValue() + "|";
		return printFullLevel(level-1, node.getLeft()) + printFullLevel(level-1, node.getRight()); 
		
	}
	
	public void printFullTree(String levels, int level)
	{
		System.out.println(fullLevelOrder(level));
	}
	
	public Queue<Comparable> levelOrder()
	{
		Queue<RedBlackNode> queue = new LinkedList<RedBlackNode>();
		Queue<Comparable> q= new LinkedList<Comparable>();
		queue.add(root);
		while(!queue.isEmpty())
		{
			RedBlackNode tempNode = queue.poll();
			 q.add(tempNode.getValue()); 
			if(tempNode.getLeft().getValue() != null)
				queue.add(tempNode.getLeft());
			if(tempNode.getRight().getValue() != null)
				queue.add(tempNode.getRight());
		}
		return q;
	}
	
	
	public RedBlackNode getRoot()
	{
		return root;
	}
	
	public String toString()
	{
		ArrayList<RedBlackNode> strs = preOrderTraversal(root);
		String str = "";
		
		for(int i = 0; i < strs.size(); i++)
		{
			
			str += strs.get(i) + "\n";
		}
		
		return str;
	}
	
	public ArrayList<RedBlackNode> preOrderTraversal(RedBlackNode root) 
    {
        if(root.getValue() == null)
            return new ArrayList<>();
        ArrayList<RedBlackNode> list = new ArrayList<>();
        list.add(root);
        list.addAll(preOrderTraversal(root.getLeft()));
        list.addAll(preOrderTraversal(root.getRight()));
        return list;
    }
	
	public boolean contains(RedBlackNode node, Comparable val)
	{
		if(node == null || node.getValue() == null)
			return false;
		else
		{
			int compareVal = val.compareTo(node.getValue());
			if(compareVal > 0)
				return contains(node.getRight(), val);
			else if(compareVal < 0)
				return contains(node.getLeft(), val);
			return true;
		}
	}
	
	public RedBlackNode getNode(RedBlackNode iter, Comparable x)
    {
        if(iter == null || iter.getValue() == null)
            return null;
        int dir = x.compareTo(iter.getValue());
        if(dir < 0)
            return getNode(iter.getLeft(), x);
        else if(dir > 0)
            return getNode(iter.getRight(), x);
        return iter;
    }
	
	public RedBlackNode remove(Comparable x)
	{
		if(getNode(root, x) == null)
            return null;
        RedBlackNode temp = getNode(root, x);
        int color = temp.getColorNum();
        if(temp.getLeft().getValue() == null && temp.getRight().getValue() == null)
            color = temp.getColorNum();
        else if(temp.getLeft().getValue() == null)
            color = getLargest(temp.getRight()).getColorNum();
        else
            color = getLargest(temp.getLeft()).getColorNum();
        root = remove(root, x);
        root.setColor(1);
        RedBlackNode rtn = new RedBlackNode(x);
        rtn.setColor(color);
        return rtn;
	}
	
	public RedBlackNode remove(RedBlackNode node, Comparable val)
	{
        if (node == null || node.getValue() == null)  
        	return null; 
  
        if (val.compareTo(node.getValue()) < 0) 
            node.setLeft(remove(node.getLeft(), val));
        else if (val.compareTo(node.getValue()) > 0) 
            node.setRight(remove(node.getRight(), val));
       
        
        else
        { 
            // set double blacks here
            if (node.getLeft().getValue() == null) 
            {
            	if(node.getColorNum() == 1 && node.getRight().getColorNum() == 0)
            		node.getRight().setColor(1);
            	else if(node.getColorNum() == 1 && node.getRight().getColorNum() == 1)
            		node.getRight().setColor(2);
                return node.getRight();
            }
            else if (node.getRight().getValue() == null) 
            {
            	if(node.getColorNum() == 1 && node.getLeft().getColorNum() == 0)
            		node.getLeft().setColor(1);
            	else if(node.getColorNum() == 1 && node.getLeft().getColorNum() == 1)
            		node.getLeft().setColor(2);
                return node.getLeft(); 
            }
  
            // node with two children: Get the in order successor (smallest 
            // in the getRight() subtree) 
            RedBlackNode largest = getLargest(node.getLeft());
            node.setValue(largest.getValue()); 
  
            // Delete the inorder successor 
            node.setLeft(remove(node.getLeft(), node.getValue()));
        }
        
        if(node.getLeft().getColorNum() == 2)
        {
        	node.getLeft().setColor(1);
        	int color = node.getColorNum();
        	RedBlackNode s = node.getRight();
        	if(s.getColorNum() == 0)
        	{
        		node = rotateLeft(node, node.getRight());
        		node.setColor(1);
        		node.getLeft().getRight().setColor(0);
        	}
        	else if(s.getRight().getColorNum() == 0)
        	{
        		node = rotateLeft(node, s);
        		node.setColor(color);
        		node.getLeft().setColor(1);
        		node.getRight().setColor(1);
        	}
        	else if(s.getLeft().getColorNum() == 0)
        	{
        		node.setRight(rotateRight(s, s.getLeft()));
        		node.getRight().setColor(1);
        		node.getRight().getRight().setColor(0);
        		node = rotateLeft(node, node.getRight());
        		node.setColor(color);
        		node.getLeft().setColor(1);
        		node.getRight().setColor(1);
        	}
        	else
        	{
        		s.setColor(0);
        		node.setColor(s.getColorNum() + 1);
        		node.getLeft().setColor(1);
        	}
        }
        else if(node.getRight().getColorNum() == 2)
        {
        	node.getRight().setColor(1);
        	int color = node.getColorNum();
        	RedBlackNode s = node.getLeft();
        	if(s.getColorNum() == 0)
        	{
        		node = rotateRight(node, node.getLeft());
        		node.setColor(1);
        		//node.getRight().setColor(1);
        		node.getRight().getLeft().setColor(0);
        	}
        	else if(s.getRight().getColorNum() == 0)
        	{
        		node = rotateRight(node, s);
        		node.setColor(color);
        		node.getRight().setColor(1);
        		node.getLeft().setColor(1);
        	}
        	else if(s.getLeft().getColorNum() == 0)
        	{
        		node.setLeft(rotateLeft(s, s.getRight()));
        		node.getLeft().setColor(1);
        		node.getLeft().getLeft().setColor(0);
        		node = rotateRight(node, node.getLeft());
        		node.setColor(color);
        		node.getRight().setColor(1);
        		node.getLeft().setColor(1);
        	}
        	else
        	{
        		s.setColor(0);
        		node.setColor(s.getColorNum() + 1);
        		node.getRight().setColor(1);
        	}
        }
        
        return node;
	}
	
	public RedBlackNode getLargest(RedBlackNode node)
	{
		RedBlackNode largest = node;
		if(node == null || node.getValue() == null)
			return null;
		while(largest.getRight().getValue() != null)
			largest = largest.getRight();
		return largest;
	}
	
	
}
