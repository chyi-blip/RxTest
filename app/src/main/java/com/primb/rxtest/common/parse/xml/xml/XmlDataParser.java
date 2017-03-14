package com.primb.rxtest.common.parse.xml.xml;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**********************************************************************
 * 程序名：     XmlDataParser.java
 * 功能描述：XML报文解析器,能将原始的XML报文转成各种格式输出,为应用层提供API。
 * 程序员：     
 * 创建日期：
 * 程序版本：   
 **********************************************************************/
public class XmlDataParser {
	private XmlMemTree tree;

	/* private UIDataList list */

	public XmlDataParser() {
		this.tree = null;
	}

	public boolean putRawData(String XMLStr) {
		tree = new XmlMemTree(XMLStr);	
			if (false == tree.getState()) {
				return false;
			}		
		return true;
	}

	public List<HashMap<String, Object>> getFormatList(String TagName) {
		List<HashMap<String, Object>> FmtLst = new ArrayList<HashMap<String, Object>>();
		XmlTreeNode NodePtr, childPtr;
		if (false == tree.getState()) {
			return null;
		}

		NodePtr = tree.search(TagName, 0);
		if (null == NodePtr) {
			return null;
		}
		NodePtr = NodePtr.getChild();
		while (null != NodePtr) {
			childPtr = NodePtr.getChild();
			HashMap<String, Object> hp = new HashMap<String, Object>();
			while (null != childPtr) {
				hp.put(childPtr.getName(), childPtr.getValue());
				childPtr = childPtr.getSibling();
			}
			FmtLst.add(hp);

			NodePtr = NodePtr.getSibling();
		}

		return FmtLst;
	}

	/* add 09/02/2011 */
	public List<HashMap<String, String>> getFormatList(String TagName, int index) {
		List<HashMap<String, String>> FmtLst = new ArrayList<HashMap<String, String>>();
		XmlTreeNode NodePtr, childPtr;

		if (false == tree.getState()) {
			return null;
		}

		NodePtr = tree.search(TagName, index);
		if (null == NodePtr) {
			return null;
		}
		NodePtr = NodePtr.getChild();
		while (null != NodePtr) {
			childPtr = NodePtr.getChild();
			HashMap<String, String> hp = new HashMap<String, String>();
			while (null != childPtr) {
				hp.put(childPtr.getName(), childPtr.getValue());
				childPtr = childPtr.getSibling();
			}
			FmtLst.add(hp);

			NodePtr = NodePtr.getSibling();
		}

		return FmtLst;
	}
	public List<String> getFormatListSingle(String TagName){
		List<String> FmtLst=new ArrayList<String>();
		XmlTreeNode NodePtr, childPtr;
		if (false == tree.getState()) {
			return null;
		}

		NodePtr = tree.search(TagName, 0);
		if (null == NodePtr) {
			return null;
		}
		NodePtr = NodePtr.getChild();
		while (null != NodePtr) {
			String nodeValue=NodePtr.getValue().toString();
			childPtr = NodePtr.getChild();
			
			HashMap<String, String> hp = new HashMap<String, String>();
			while (null != childPtr) {
				hp.put(childPtr.getName(), childPtr.getValue());
				childPtr = childPtr.getSibling();
			}
			FmtLst.add(nodeValue);

			NodePtr = NodePtr.getSibling();
		}
		
		
		
		return FmtLst;
	}

	public List<HashMap<String, String>> getFormatListSepcial(String TagName, String childTagName, int index) {
		List<HashMap<String, String>> FmtLst = new ArrayList<HashMap<String, String>>();
		XmlTreeNode NodePtr, childPtr;

		if (false == tree.getState()) {
			return null;
		}
		NodePtr = tree.search(TagName, index);
		if (null == NodePtr) {
			return null;
		}
		NodePtr = NodePtr.getChild();
		while (null != NodePtr) {
			if (NodePtr.getName().equals(childTagName)) {
				childPtr = NodePtr.getChild();
				HashMap<String, String> hp = new HashMap<String, String>();
				while (null != childPtr) {
					hp.put(childPtr.getName(), childPtr.getValue());
					childPtr = childPtr.getSibling();
				}
				FmtLst.add(hp);
			}
			NodePtr = NodePtr.getSibling();

		}
		return FmtLst;
	}
	

	// 留着 later思考

	public void search(String TagName, int index) {
		tree.search(TagName, index);
	}

	public String getValue(String TagName) {
		XmlTreeNode tn;

		if (false == tree.getState()) {
			return null;
		}

		tn = tree.search(TagName, 0);

		if (null == tn) {
			return null;
		}

		return tn.getValue();
	}

	public String getValue(String TagName, int index) {
		XmlTreeNode tn;

		if (false == tree.getState()) {
			return null;
		}

		tn = tree.search(TagName, index);
		if (null == tn) {
			return null;
		}

		return tn.getValue();
	}

	public int getLength(String TagName) {
		if (false == tree.getState()) {
			return -1;
		}

		return tree.lenght(TagName);
	}

	public void destroy() {
		tree.destroy();
	}

}
