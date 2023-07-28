package com.example.demo.entity;


import jakarta.persistence.Id;
import lombok.Data;


@Data
public class AdminClass {
	
	@Id
	private int clno;
	
	private int scano;
	private String cltitle;
	private String climg;
	private String clcontent;
	private String cllevel;
	private int clpeople;
	private int clprice;
	private String claddr;
	private String cldate;
	private String clsdate;
	private String cledate;
	private String cltype;
	private String scaname;
	private String aid;
	public int getClno() {
		return clno;
	}
	public void setClno(int clno) {
		this.clno = clno;
	}
	public int getScano() {
		return scano;
	}
	public void setScano(int scano) {
		this.scano = scano;
	}
	public String getCltitle() {
		return cltitle;
	}
	public void setCltitle(String cltitle) {
		this.cltitle = cltitle;
	}
	public String getClimg() {
		return climg;
	}
	public void setClimg(String climg) {
		this.climg = climg;
	}
	public String getClcontent() {
		return clcontent;
	}
	public void setClcontent(String clcontent) {
		this.clcontent = clcontent;
	}
	public String getCllevel() {
		return cllevel;
	}
	public void setCllevel(String cllevel) {
		this.cllevel = cllevel;
	}
	public int getClpeople() {
		return clpeople;
	}
	public void setClpeople(int clpeople) {
		this.clpeople = clpeople;
	}
	public int getClprice() {
		return clprice;
	}
	public void setClprice(int clprice) {
		this.clprice = clprice;
	}
	public String getCladdr() {
		return claddr;
	}
	public void setCladdr(String claddr) {
		this.claddr = claddr;
	}
	public String getCldate() {
		return cldate;
	}
	public void setCldate(String cldate) {
		this.cldate = cldate;
	}
	public String getClsdate() {
		return clsdate;
	}
	public void setClsdate(String clsdate) {
		this.clsdate = clsdate;
	}
	public String getCledate() {
		return cledate;
	}
	public void setCledate(String cledate) {
		this.cledate = cledate;
	}
	public String getCltype() {
		return cltype;
	}
	public void setCltype(String cltype) {
		this.cltype = cltype;
	}
	public String getScaname() {
		return scaname;
	}
	public void setScaname(String scaname) {
		this.scaname = scaname;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	
	
	
}
