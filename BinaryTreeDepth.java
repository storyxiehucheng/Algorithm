package com.example.story.binarytree;

/**
 * @author story2
 *寻找二叉树的深度问题
 */
public class BinaryTreeDepth {

	public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

	/**寻找一个二叉树的最小深度
	 * @param root
	 */
	public int FindShortestPath(TreeNode root)
	{
		//如果节点为空，则返回0
		if(null==root)
		{
			return 0;
		}
		//如果左节点为空，则遍历右节点深度，注意加1是加上当前节点
		else if(null==root.left)
		{
			return 1+FindShortestPath(root.right);
		}
		//如果右节点为空，则遍历左节点，注意加1
		else if(null==root.right)
		{
			return 1+FindShortestPath(root.left);
		}
		//如果有两个孩子节点，则分别遍历求出深度，取深度小的那个，加1是加上当前节点
		else
			return 1+Math.min(FindShortestPath(root.left), FindShortestPath(root.right));
	}
	
	/**寻找树的高度，即最大的节点深度
	 * @param root
	 * @return
	 */
	public int FindTreeHight(TreeNode root)
	{
		if(null==root)
		{
			return 0;
		}
		//返回左右树中最大的节点深度
		else
			return 1+Math.max(FindTreeHight(root.left), FindTreeHight(root.right));
	}
}
