package com.closenesscentrality.bean.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.closenesscentrality.bean.Node;

@XmlRootElement
public class ResponseGraphCentrality {

	private String status;
	private List<Node> nodes;

	public List<Node> getNodes() {
		return nodes;
	}

	@XmlElement(name="node")
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
