package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.db.FeedDBManager;
import com.example.demo.entity.Account;
import com.example.demo.entity.Feed;
import com.example.demo.entity.Image;

@Repository
public class FeedDAO_mb {

	public List<Feed> findAllFeed(){
		return FeedDBManager.findAllFeed();
	}
	public List<String> imgFindByFno(int fno){
		return FeedDBManager.imgFindByFno(fno);
	}
	public Account findAccount (String aid) {
		return FeedDBManager.findAccount(aid);
	}
	public int insertFeed(Feed f) {
		return FeedDBManager.insertFeed(f);
	}
	public int insertFeedImg(Image im) {
		return FeedDBManager.insertFeedImg(im);
	}
	public int findFeedMax() {
		return FeedDBManager.findFeedMax();
	}
}