package FavorDAO_mb;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
@Repository
public class ImageDAO_mb {

	public List<String> findFeedImage(String aid) {
		return DBManager.findFeedImage(aid);
	}

}
