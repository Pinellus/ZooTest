package com.pinellus.zootest.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TestZoo {
	
	public TestZoo() {
		
	}
	
	public TestZoo(String name, int numTest, int numDom, String path, int testNum, List<Domanda> domande) {
		this.name = name;
		this.numTest = numTest;
		this.numDom = numDom;
		this.path= path;
		this.domande = domande;
		this.testNum = testNum;
	}

	private String name;
	private int numTest;
	private int numDom;
	private String path;
	private int testNum;
	
	private List<Domanda> domande;
	
	@XmlAttribute
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@XmlElement
	public int getNumTest() {
		return numTest;
	}

	public void setNumTest(int numTest) {
		this.numTest = numTest;
	}
	
	@XmlElement
	public int getNumDom() {
		return numDom;
	}

	public void setNumDom(int numDom) {
		this.numDom = numDom;
	}
	
	@XmlElement
	public List<Domanda> getDomande() {
		return domande;
	}

	public void setDomande(List<Domanda> domande) {
		this.domande = domande;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getTestNum() {
		return testNum;
	}

	public void setTestNum(int testNum) {
		this.testNum = testNum;
	}
	
	
	
}
