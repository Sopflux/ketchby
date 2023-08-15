package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManagerClub;
import com.example.demo.entity.Club;
import com.example.demo.entity.ClubDetail;
import com.example.demo.entity.ClubMain;
import com.example.demo.entity.Clubapplication;
import com.example.demo.entity.Scategory;
import com.example.demo.entity.Slocation;


@Repository
public class ClubDAO {
    public List<ClubMain> getClubList(HashMap<String, Object> map) {
        return DBManagerClub.getClubList(map);
    }
    public int getNextCbNo() {
    	return DBManagerClub.getNextCbNo();
    }
    public int insert(Club c) {
    	return DBManagerClub.insert(c);
    }
    public List<Scategory> findscategory(int bcano) {
    	return DBManagerClub.findscategory(bcano);
    }
    public List<Slocation> findslocation(int blocno) {
    	return DBManagerClub.findslocation(blocno);
    }
    public ClubDetail findClubInfo(int cbno) {
    	return DBManagerClub.findClubInfo(cbno);
    }
    public List<ClubDetail> findRecommandClub(HashMap<String, Object> map){
    	return DBManagerClub.findRecommandClub(map);
    }
    public int insert(Clubapplication ca) {
    	return DBManagerClub.insert(ca);
    }
    public int getNextCbapno() {
    	return DBManagerClub.getNextCbapno();
    }
    public int update(Club c) {
    	return DBManagerClub.update(c);
    }
    public int delete(int cbno) {
    	return DBManagerClub.delete(cbno);
    }
    public int findApplyCount(HashMap<String, Object> map) {
    	return DBManagerClub.findApplyCount(map);
    }
    
}
