package com.primb.rxtest.common.parse.xml.xml;

/**********************************************************************
 * 程序名：     XmlMemTree.java
 * 功能描述：XML报文在内存中的数据结构,以多叉树结构实现。
 *          该类对应用层是透明的,不可见的。
 * 程序员：   
 * 创建日期：
 * 程序版本：1.0.2     
 * 修改历史: 1. 20110822加入searchHistory成员变量,
 *           以提高对同名节点的连续搜索效率
 *           2.20110918修改了程序,使程序可以解析节点间含有换行或者
 *             空格的xml报文。
 *          
 **********************************************************************/

import android.text.TextUtils;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class XmlMemTree {
	private XmlTreeNode root;
	private TreeSearchRstList srl;
	private boolean state = false;

	private String searchHistory;

	public XmlMemTree(String xmlStr) {
		if(TextUtils.isEmpty(xmlStr)){
			return;
		}
		boolean isText = false;

		int eventType = 0;
		XmlTreeNode newNode = null;
		XmlParseStack stack = new XmlParseStack();
		root = null;
		srl = new TreeSearchRstList();
		searchHistory = null;
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(new StringReader(xmlStr));
		} catch (XmlPullParserException e) {
			e.printStackTrace();
			return;
		}

		try {
			eventType = parser.getEventType();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
			return;
		}

		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_TAG:
				isText = true;
				String name = parser.getName();

				newNode = new XmlTreeNode(name);
				if (root == null) {
					root = newNode;
				} else {
					if (null == stack.peek().getChild()) {
						stack.peek().setChild(newNode);
						newNode.setParent(stack.peek());
						stack.peek().addChilds(newNode);
					} else {
						XmlTreeNode tmp = stack.peek().getChild();
						while (null != tmp.getSibling()) {
							tmp = tmp.getSibling();
						}
						tmp.setSibling(newNode);
						newNode.setParent(stack.peek());
						stack.peek().addChilds(newNode);
					}
				}
				stack.push(newNode);
				break;
			case XmlPullParser.TEXT:
				String value = parser.getText();

				if (isText == true) {
					newNode.setValue(value);
				}
				break;
			case XmlPullParser.END_TAG:
				isText = false;
				stack.pop();
				break;
			default:
				break;
			}
			try {
				eventType = parser.next();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
				return;
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		}

		state = true;
	}

	public boolean getState() {
		return state;
	}

	public XmlTreeNode getRoot() {
		return root;
	}

	public void TreeDisplay(XmlTreeNode root) {
		if (null == root) {
			return;
		}
		root.dispaly();
		TreeDisplay(root.getChild());
		TreeDisplay(root.getSibling());
	}

	private void searchNode(XmlTreeNode root, String name) {
		if (null == root) {
			return;
		}
		addNode(name, root);
		ArrayList<XmlTreeNode> nodes = root.getChilds();
		foreachChilds(name, nodes);
	}

	private void foreachChilds(String name, ArrayList<XmlTreeNode> nodes) {
		for (XmlTreeNode node : nodes) {
			addNode(name, node);
			foreachChilds(name, node.getChilds());
		}
	}

	private void addNode(String name, XmlTreeNode node) {
		if (name.equals(node.getName())) {
			srl.addLast(node);
		}
	}

	public XmlTreeNode search(String name, int index) {
		if (null == name) {
			return null;
		}

		if ((null == searchHistory) || (!name.equals(searchHistory))) {
			srl.setEmpty();
			searchNode(this.root, name);
			searchHistory = name;
		}

		return srl.getTreeNode(index);
	}

	public int lenght(String name) {
		if (null == name) {
			return 0;
		}

		if ((null == searchHistory) || (!name.equals(searchHistory))) {
			srl.setEmpty();
			searchNode(this.root, name);
			searchHistory = name;
		}
		return srl.length();
	}

	public void destroy() {
		srl.setEmpty();
		this.root = null;
		searchHistory = null;
	}
}
