package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.db.FeedDBManager;
import com.example.demo.entity.Account;
import com.example.demo.entity.Feed;
import com.example.demo.entity.Feedcommentary;
import com.example.demo.entity.Image;
import com.example.demo.entity.Likes;

@Repository
public class FeedDAO_mb {

	public List<Feed> findAllFeed(){
		return FeedDBManager.findAllFeed();
	}
	public List<Feed> findMyFeed(String aid){
		return FeedDBManager.findMyFeed(aid);
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
	
	public int userCntLike(int fno, String aid) {
		return FeedDBManager.userCntLike(fno, aid);
	}
	public int cntLike(int fno) {
		return FeedDBManager.cntLike(fno);
	}
	public int insertLike(Likes l) {
		return FeedDBManager.insertLike(l);
	}
	public int deleteLike(int fno, String aid) {
		return FeedDBManager.deleteLike(fno, aid);
 	}
	public int cntComment(int fno) {
		return FeedDBManager.cntComment(fno);
	}
	public List<Feedcommentary> findComment(int fno){
		return FeedDBManager.findComment(fno);
	}
	public int insertComment(Feedcommentary fco) {
		return FeedDBManager.insertComment(fco);
	}
}