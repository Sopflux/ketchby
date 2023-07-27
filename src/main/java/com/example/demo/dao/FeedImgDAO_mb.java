package com.example.demo.dao;



import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.example.demo.entity.Feed;
import com.example.demo.entity.FeedIMG;



@Repository
public class FeedImgDAO_mb {

	public List<FeedIMG> findAllFeedIMg(){
		FeedDAO_mb feedDao = new FeedDAO_mb();
		List<FeedIMG> list = new ArrayList<>();
		List<Feed> feed = feedDao.findAllFeed();
		
		for (Feed f : feed) {
			FeedIMG fm = new FeedIMG();
			fm.setFno(f.getFno());
			fm.setAid(f.getAid());
			fm.setFcontent(f.getFcontent());
			fm.setFlist(feedDao.imgFindByFno(f.getFno()));
			list.add(fm);
		}
		
		return list;
	}
}
