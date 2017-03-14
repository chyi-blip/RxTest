package com.primb.rxtest.common.parse.xml.xml;

/**********************************************************************
 * 程序名：    XmlParseStack.java
 * 功能描述：工具类,辅助XML解析的栈结构。该类对应用层是透明的,不可见的。
 * 程序员：    
 * 创建日期：
 * 程序版本：1.0.0     
 **********************************************************************/
class StackNode
{
	public XmlTreeNode tn;
	public StackNode next;
	
	public StackNode(XmlTreeNode tn)
	{
		this.tn = tn;
		next = null;
	}
}

class XmlParseStack
{
	private StackNode top;
	
	public XmlParseStack()
	{
		top = null;
	}
	
	public boolean isEmpty()
	{
		if (null == top)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void push(XmlTreeNode node)
	{
		StackNode newNode =  new StackNode(node);
		
		if (null == top)
		{
			top = newNode;
		}
		else
		{
			newNode.next = top;
			top = newNode;
		}
	}
	
	public XmlTreeNode pop()
	{
		XmlTreeNode tmp;
		
		if (isEmpty())
		{
			return null;
		}
		else
		{
			tmp = top.tn;
			top = top.next;
			return tmp;
		}
		
	}
	
	public XmlTreeNode peek()
	{
		if (isEmpty())
		{
			return null;
		}
		else
		{
			return top.tn;
		}
	}
	
	public void destroy()
	{
		top = null;
	}
}