package com.primb.rxtest.common.parse.xml.xml;



/**********************************************************************
 * 程序名：     TreeSearchRstItem.java
 * 功能描述：在XML转化成的内存树中的查找结果节点的数据结构。
 *           该类对应用层是透明的,不可见的。
 * 程序员：     
 * 创建日期：
 * 程序版本：  
 **********************************************************************/

class TreeSearchRstItem
{
	public XmlTreeNode dstNode;
	public XmlTreeNode child;
	public TreeSearchRstItem next; 
	
	public TreeSearchRstItem(XmlTreeNode tn, XmlTreeNode child)
	{
		this.dstNode = tn;
		this.child = child;
		next = null;
	}
	
	public void display()
	{
		if (null != child)
		{
			//dbp.print(dstNode.getName() + "\n", null);
		}
	}
	
	
}
