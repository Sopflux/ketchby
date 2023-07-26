package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.entity.Feed;

@Repository
public class FeedDAO_mb {

	public List<Feed> findAllFeed(){
		return DBManager.findAllFeed();
	}
	public List<String> imgFindByFno(){
		return DBManager.imgFindByFno();
	}
}
