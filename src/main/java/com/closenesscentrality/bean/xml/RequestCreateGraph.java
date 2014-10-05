package com.closenesscentrality.bean.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RequestCreateGraph {

	private int maxNodes;
	
	@XmlElement(name="maxnodes")
	public int getMaxNodes() {
		return maxNodes;
	}

	public void setMaxNodes(int maxNodes) {
		this.maxNodes = maxNodes;
	}
}
