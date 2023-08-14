package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.db.FavorDBManager;
import com.example.demo.db.FeedDBManager;
import com.example.demo.entity.Account;
import com.example.demo.entity.Bcategory;
import com.example.demo.entity.Blocation;
import com.example.demo.entity.Favorite;
import com.example.demo.entity.Feed;
import com.example.demo.entity.Feedcommentary;
import com.example.demo.entity.Image;
import com.example.demo.entity.Likes;
import com.example.demo.entity.Place;
import com.example.demo.entity.Scategory;
import com.example.demo.entity.Slocation;

@Repository
public class FavorDAO_mb {

	public List<Scategory> findScategory(int bcano){
		return FavorDBManager.findScategory(bcano);
	}
	public List<Slocation> findSlocation(int blocno){
		return FavorDBManager.findSlocation(blocno);
	}
	public List<Bcategory> findBcategory(){
		return FavorDBManager.findBcategory();
	}
	public List<Blocation> findBlocation(){
		return FavorDBManager.findBlocation();
	}
	public int insertPlace(Place p){
		return FavorDBManager.insertPlace(p);
	}
	public int insertFavorite(Favorite f){
		return FavorDBManager.insertFavorite(f);
	}
	public List<Favorite> findHobbyById(String aid){
		return FavorDBManager.findHobbyById(aid);
	}
	public List<Place> findPlaceById(String aid){
		return FavorDBManager.findPlaceById(aid);
	}	
	public int updateFavorite(int scano, int fno){
		return FavorDBManager.updateFavorite(scano, fno);
	}	
	public int updatePlace(int slocno, int pno){
		return FavorDBManager.updateFavorite(slocno, pno);
	}	
}