package com.primb.rxtest.common.parse.xml.xml;

import java.util.ArrayList;

/**********************************************************************
 * 程序名：    XmlTreeNode.java
 * 功能描述：XML报文在内存中的多叉树数据结构的节点。
 *          该类对应用层是透明的,不可见的。
 * 程序员：    
 * 创建日期：
 * 程序版本：1.0.0     
 **********************************************************************/
public class XmlTreeNode {
	private String name;
	private String value;
	private XmlTreeNode child;
	private XmlTreeNode parent;
	private XmlTreeNode sibling;

	private ArrayList<XmlTreeNode> mChilds;

	public XmlTreeNode() {
		mChilds = new ArrayList<XmlTreeNode>();
		this.name = null;
		// this.value = null;
		this.value = "";
		this.child = null;
		this.parent = null;
		this.sibling = null;
	}

	public XmlTreeNode(String name) {
		mChilds = new ArrayList<XmlTreeNode>();
		this.name = name;
		this.value = "";
		this.child = null;
		this.parent = null;
		this.sibling = null;
	}

	public XmlTreeNode(String name, String value) {
		mChilds = new ArrayList<XmlTreeNode>();
		this.name = name;
		this.value = value;
		this.child = null;
		this.parent = null;
		this.sibling = null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public XmlTreeNode getChild() {
		return child;
	}

	public void setChild(XmlTreeNode child) {
		this.child = child;
	}

	public XmlTreeNode getParent() {
		return parent;
	}

	public void setParent(XmlTreeNode parent) {
		this.parent = parent;
	}

	public XmlTreeNode getSibling() {
		return sibling;

	}

	public void setSibling(XmlTreeNode sibling) {
		this.sibling = sibling;
	}

	public void addChilds(XmlTreeNode child) {
		mChilds.add(child);
	}

	public void dispaly() {
		// dbp.print("[ " + this.name + ": " + this.value+ "]\n", null);
	}

	public ArrayList<XmlTreeNode> getChilds() {
		return mChilds;
	}

}