package com.example.demo.entity;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
public class MainPage {
   
   //클래스용
   private int clno;
   private int scano;
   private String cltitle;
   private String claddr;
   private String climg;
   private String cllevel;
   private int clprice;
   private String scaname;
   private String bcaname;
   private String aid;
   private String cltype;
   private int avgRstar;
   private int cntRno;
   
   
   //커뮤니티용
   private int rownum;
   private int bno;
   private String btitle;
   private String bcontent;
   private String bdate;
   private String nick;
   private String img;

   
   
   //소모임용
   private int cbno;
   private String cbtitle;
   private int cbpeople;
   private String cbimg;
   private String cbclsdate;
   private int slocno;
   private int ap_cnt;
   
   
   //검색용
   private String keyword;
   
   
   
   
   
   public String getKeyword() {
      return keyword;
   }
   public void setKeyword(String keyword) {
      this.keyword = keyword;
   }
   public String getCltype() {
      return cltype;
   }
   public void setCltype(String cltype) {
      this.cltype = cltype;
   }
   public int getRownum() {
      return rownum;
   }
   public void setRownum(int rownum) {
      this.rownum = rownum;
   }
   public int getAp_cnt() {
      return ap_cnt;
   }
   public void setAp_cnt(int ap_cnt) {
      this.ap_cnt = ap_cnt;
   }
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
   public String getCladdr() {
      return claddr;
   }
   public void setCladdr(String claddr) {
      this.claddr = claddr;
   }
   public String getClimg() {
      return climg;
   }
   public void setClimg(String climg) {
      this.climg = climg;
   }
   public String getCllevel() {
      return cllevel;
   }
   public void setCllevel(String cllevel) {
      this.cllevel = cllevel;
   }
   public int getClprice() {
      return clprice;
   }
   public void setClprice(int clprice) {
      this.clprice = clprice;
   }
   public String getScaname() {
      return scaname;
   }
   public void setScaname(String scaname) {
      this.scaname = scaname;
   }
   public String getBcaname() {
      return bcaname;
   }
   public void setBcaname(String bcaname) {
      this.bcaname = bcaname;
   }
   public String getAid() {
      return aid;
   }
   public void setAid(String aid) {
      this.aid = aid;
   }
   
   public int getBno() {
      return bno;
   }
   public void setBno(int bno) {
      this.bno = bno;
   }
   public String getBtitle() {
      return btitle;
   }
   public void setBtitle(String btitle) {
      this.btitle = btitle;
   }
   public String getBcontent() {
      return bcontent;
   }
   public void setBcontent(String bcontent) {
      this.bcontent = bcontent;
   }
   public String getBdate() {
      return bdate;
   }
   public void setBdate(String bdate) {
      this.bdate = bdate;
   }
   public String getNick() {
      return nick;
   }
   public void setNick(String nick) {
      this.nick = nick;
   }
   public String getImg() {
      return img;
   }
   public void setImg(String img) {
      this.img = img;
   }
   public int getCbno() {
      return cbno;
   }
   public void setCbno(int cbno) {
      this.cbno = cbno;
   }
   public String getCbtitle() {
      return cbtitle;
   }
   public void setCbtitle(String cbtitle) {
      this.cbtitle = cbtitle;
   }
   public int getCbpeople() {
      return cbpeople;
   }
   public void setCbpeople(int cbpeople) {
      this.cbpeople = cbpeople;
   }
   public String getCbimg() {
      return cbimg;
   }
   public void setCbimg(String cbimg) {
      this.cbimg = cbimg;
   }
   public String getCbclsdate() {
      return cbclsdate;
   }
   public void setCbclsdate(String cbclsdate) {
      this.cbclsdate = cbclsdate;
   }
   public int getSlocno() {
      return slocno;
   }
   public void setSlocno(int slocno) {
      this.slocno = slocno;
   }
   
   
   
}