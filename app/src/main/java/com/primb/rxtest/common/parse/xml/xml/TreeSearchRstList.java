package com.primb.rxtest.common.parse.xml.xml;



/**************************************************************************
 * 程序名：     TreeSearchRstList.java
 * 功能描述：在XML转化成的内存树中的查找结果集。该类对应用层是透明的,不可见的。
 * 程序员：     
 * 创建日期：
 * 程序版本：  
 *************************************************************************/

class TreeSearchRstList
{
	TreeSearchRstItem head;
	
	public TreeSearchRstList()
	{
		head = null;
	}
	
	public boolean isEmpty()
	{
		if (null == head)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public int length()
	{
		int len = 0;
		TreeSearchRstItem pItem =  head;
		
		while(null != pItem)
		{
			len++;
			pItem = pItem.next;
		}
		
		return len;
	}
	
	public void addLast(XmlTreeNode tn)
	{
		TreeSearchRstItem ptr = head;
		TreeSearchRstItem item = new TreeSearchRstItem(tn, tn.getChild());
		
		 if (isEmpty())
		 {
			 this.head = item;
		 }
		 else
		 {
			 while (null != ptr.next)
			 {
				 ptr = ptr.next;
			 }
			 ptr.next = item;
		 }
	}
	
	public  XmlTreeNode getTreeNode(int index)
	{
		int i;
		TreeSearchRstItem pItem = head;
		
		if (index >= length())
		{
			return null;
		}
		
		for (i=0; i<index; i++)
		{
			pItem = pItem.next;
		}
		
		return pItem.dstNode;
	}
	
	public void setEmpty()
	{
		head= null;
	}
}


