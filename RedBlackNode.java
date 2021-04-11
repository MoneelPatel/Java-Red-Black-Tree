
public class RedBlackNode 
{
	private Comparable value;
	private RedBlackNode left;
	private RedBlackNode right;
	private int color;
	private final String[] colorString = {"Red", "Black", "DOUBLE BLACK"};
	
	public RedBlackNode(Comparable val)
	{
		value = val;
		left = new RedBlackNode(null, null, null);
		right = new RedBlackNode(null, null, null);
		color = 0;
	}
	
	public RedBlackNode(Comparable val, RedBlackNode l, RedBlackNode r)
	{
		if(val == null)
			color = 1;
		else
			color = 0;
		value = val;
		left = l;
		right = r;
	}
	
	public Comparable getValue()
	{
		return value;
	}
	
	public RedBlackNode getLeft()
	{
		return left;
	}
	
	public RedBlackNode getRight()
	{
		return right;
	}
	
	public void setRight(RedBlackNode node)
	{
		right = node;
	}
	
	public void setLeft(RedBlackNode node)
	{
		left = node;
	}
	
	public int getColorNum()
	{
		return color;
	}
	
	public void setColor(int col)
	{
		color = col;
	}
	
	public String getColor()
	{
		return colorString[color];
	}
	
	public void setValue(Comparable val)
	{
		value = val;
	}
	
	
	public String toString()
	{
		return "Value:" + value + ", Left:" + left.getValue() + ", Right:" + right.getValue() + ", Color:" + getColor();
	}
}
