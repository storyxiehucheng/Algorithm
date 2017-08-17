package com.example.story.binarytree;

public class BinarySearchTree<Anytype extends Comparable<? super Anytype>>{
	//定义二叉树的根节点
	private BinaryNode<Anytype> root;
	
	//1、定义二叉树节点
	private static class BinaryNode<Anytype>
	{
		//定义属性
		Anytype element;
		BinaryNode<Anytype> left;
		BinaryNode<Anytype> right;
		//定义构造函数
		BinaryNode(Anytype theElement)
		{
			element=theElement;
		}
		//重载构造函数
		BinaryNode(Anytype theElement,BinaryNode<Anytype> theLeft,BinaryNode<Anytype> theRight)
		{
			element=theElement;
			left=theLeft;
			right=theRight;
		}
	}
	
	//二叉搜索树的构造方法
	public BinarySearchTree()
	{
		root=null;//先将根节点设置为null
	}
	
	//清空二叉树
	public void makeEmpty()
	{
		root=null;
	}
	
	//判断二叉树是否为空
	public boolean isEmpty()
	{
		if(null==root) return true;
		if(null==root.left&&null==root.right) return true;
		else return false;
	}
		
	//找到二叉树的最小节点
	public Anytype findMin()
	{
		//判断二叉树是否为空
		if(isEmpty()) return null;
		return findMin(root).element;
	}
	
	//找到二叉树的最大节点
	public Anytype findMax()
	{
		//判断二叉树是否为空
		if(isEmpty()) return null;
		return findMax(root).element;
	}	
	
	//插入元素
	public void insert(Anytype x)
	{
		root=insert(x,root);
	}

	//移除元素
	public void remove(Anytype x)
	{
		root=remove(x,root);
	}

	//打印整个二叉树
	public void printTree()
	{
		if(isEmpty()) 
		{
			System.out.println("空树");
			return;
		}
		printTree(root);
	}
	
	//查看是否包含某个节点
	public boolean contains(Anytype x)
	{
		return contains(x,root);
	}
	
	/**循环遍历打印树中的数据(中序遍历方式)
	 * @param root2
	 */
	private void printTree(BinaryNode<Anytype> root2) {
		if(root2!=null)
		{
			printTree(root2.left);
			System.out.println(root2.element);
			printTree(root2.right);
		}
	}
	/**查找一个数是否在二叉树的中
	 * @param x 需要查找的元素
	 * @param root2 根节点
	 * @return
	 */
	private boolean contains(Anytype x, BinaryNode<Anytype> root2) {
		//1、先判断是节点是否为空，为空则直接返回false
		if(root2==null)
		{
			return false;
		}
		//2、利用先序遍历，遍历整个树，因为二叉查找树有顺序，可以根据结果来判断该查左边还是右边
		/** 第一次尝试的错误代码
		//先判断左节点
		if(null!=root2.left)
		{
			//
			if(x==root2.left.element) return true;
			else
			{
				return contains(x,root2.left);
			}
		}
		if(null!=root2.right)
		{
			if(x==root2.right.element) return true;
			else
			{
				return contains(x,root2.right);
			}
		}
		else
		{
			return false;
		}*/	
		//判断比较结果
		int compareResult=x.compareTo(root2.element);
		//如果x大于节点的数据，则往节点的右边继续遍历
		if(compareResult>0)
		{
			return contains(x,root2.right);
		}
		//如果x小于节点的数据，则往节点的左边继续遍历
		else if(compareResult<0)
		{
			return contains(x,root2.left);
		}
		//否则，表示等于节点的数据，则返回true，找到相等的节点数据
		else
			return true;
	}
	
	/**二叉搜索树的插入方法
	 * 思路：遍历树，找到一个节点如果比要插入的数小，且没有右节点，则右节点就是需要插入的数
	 * 反之，如果比要插入的数大，且没有左节点，则左节点就是要插入的数
	 * @param x 要插入的数
	 * @param root2 返回根节点
	 * @return
	 */
	private BinaryNode<Anytype> insert(Anytype x, BinaryNode<Anytype> root2) {
		//先遍历整个树，找到插入点
		//以x新建一个节点
		BinaryNode<Anytype> node=new BinaryNode<Anytype>(x,null,null);
		//如果根节点为null，则直接返回新插入的节点，作为根节点
		if(null==root2)
		{		
			return node;
		}
		//否则，比较节点的值，判断应该往左，还是往右
		int compareResult=x.compareTo(root2.element);
		//如果x大于该节点的值，则表示应该往右遍历
		if(compareResult>0)
		{	
			/**冗余，代码：因为递归开始会判断是否为null，并且返回新的节点，因此可以
			 * 直接使用递归，将返回值赋给root2.right即可，不必先判断下一节点是否为null
			//如果右节点为空，则右节点就是应该插入的节点
			if(null==root2.right)
			{
				root2.right=node;
				return root2;
			}
			//否则继续遍历右节点
			else
			{
				return insert(x,root2.right);
			}
			*/
			root2.right=insert(x,root2.right);
		}
		//如果x小于该节点的值，则表示需要往左节点继续遍历
		else if(compareResult<0)
		{
			/**冗余代码
			//如果左节点为空，则左节点就是该插入的点
			if(null==root2.left)
			{
				root2.left=node;
				return root2;
			}
			//否则继续往左遍历
			else
			{
				return insert(x,root2.left);
			}
			*/
			root2.left=insert(x,root2.left);
		}
		//如果相等，则表示节点中已经有要插入的节点，则不必插入，直接返回
		else
		{
			System.out.println("元素"+x+" 已经在节点中了，不必插入");
		}
		return root2;
	}
	
	/**删除树中的某个节点
	 * 思路：如果是叶子节点，则直接删除
	 * 如果有一个节点，则将下一个节点赋给其父节点
	 * 如果有两个节点：删除策略是用其右子树中的最小节点A，代替要删除的节点，
	 * 然后循环找到A节点的右子树中最小的节点来代替A，
	 * @param x
	 * @param root2
	 * @return
	 */
	private BinaryNode<Anytype> remove(Anytype x, BinaryNode<Anytype> root2) {
		//如果节点为空，则直接返回
		if(null==root2)
		{
			return root2;
		}
		//先查询要删除节点的位置
		int resultCompare=x.compareTo(root2.element);
		//如果要删除的节点比当前节点大，则往右遍历
		if(resultCompare>0)
		{
			//由于返回的是删除后的节点，返回值应该直接赋值给当前节点的右节点
			root2.right=remove(x,root2.right);
		}
		//如果要删除的节点比当前节点小，则往左遍历
		else if(resultCompare<0)
		{
			//将删除后的节点赋值给，当前节点的左节点
			root2.left=remove(x,root2.left);
		}
		//如果当前节点就是要删除的节点，则先判断给节点是否有左右孩子
		else if(null!=root2.left&&null!=root2.right)
		{
			//如果有两个孩子，则先找到右子树中最小的数，赋值给要删除的节点
			root2.element=findMin(root2.right).element;
			//刚刚赋值给当前节点的节点以同样的方式来删除
			//最后将返回的节点值，赋值给当前节点的右节点
			root2.right=remove(root2.element,root2.right);
		}
		//如果只有一个孩子，或者没有孩子，则返回自己的孩子给上一层，或者返回null
		else
			root2=(null!=root2.left)?root2.left:root2.right;
		return root2;
	}
	/**找到寻找二叉搜索树的最小节点
	 * 思路，一直往左节点寻找
	 * @param root2
	 * @return
	 */
	private BinaryNode<Anytype> findMin(BinaryNode<Anytype> root2) {
		if(null==root2)
		{
			return null;
		}
		if(null==root2.left)
		{
			return root2;
		}
		else
		{
			return findMin(root2.left);
		}
		/*
		 * 非递归的方法
		 * 
		if(null==root2) return null;
		while(null!=root2.left)
		{
			root2=root2.left;
		}
		return root2;
		*/
	}
	/**寻找最大节点，与寻找最小节点类似，只不过是往树的右边遍历，到最后一个节点
	 * @param root2
	 * @return
	 */
	private BinaryNode<Anytype> findMax(BinaryNode<Anytype> root2) {
		/*
		if(null==root2)
		{
			return null;
		}
		if(null==root2.right)
		{
			return root2;
		}
		else
		{
			return findMin(root2.right);
		}
		*/
		/*
		 * 非递归的方法
		 * */
		if(null==root2) return null;
		while(null!=root2.right)
		{
			root2=root2.right;
		}
		return root2;
		
	}
}
