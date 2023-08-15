package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.db.FeedDBManager;
@Repository
public class ImageDAO_mb {

	public List<String> findFeedImage(String aid) {
		return FeedDBManager.findFeedImage(aid);
	}

}
