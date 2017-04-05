package com.pinellus.zootest.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="Domande")
@XmlRootElement
public class Domanda implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id", unique=true, nullable=false)
	private Long id;
	
	@Column(name="domanda", length=250)
	private String domanda;
	
	@Column(name="a", length=250)
	private String a;
	
	@Column(name="b", length=250)
	private String b;
	
	@Column(name="c", length=250)
	private String c;
	
	@Column(name="giusta", length=250)
	private String giusta;
	
	@Transient
	private int order;
	
	@XmlAttribute
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@XmlElement
	public String getDomanda() {
		return domanda;
	}

	public void setDomanda(String domanda) {
		this.domanda = domanda;
	}
	
	@XmlElement
	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}
	
	@XmlElement
	public String getB() {
		return b;
	}
	
	public void setB(String b) {
		this.b = b;
	}
	
	@XmlElement
	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}
	
	@XmlElement
	public String getGiusta() {
		return giusta;
	}

	public void setGiusta(String giusta) {
		this.giusta = giusta;
	}
	
	@XmlElement
	@Transient
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
	
	
	
}
