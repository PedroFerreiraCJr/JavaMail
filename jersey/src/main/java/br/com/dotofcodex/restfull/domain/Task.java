package br.com.dotofcodex.restfull.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Task {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Task [name=").append(name).append("]");
		return builder.toString();
	}

}
