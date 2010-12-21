package springsprout.modules.study.post.imagePost;

import java.util.List;

import springsprout.domain.study.board.ImagePost;
import springsprout.modules.study.post.PostService;

public interface ImagePostService extends PostService<ImagePost> {

	public List<ImagePost> getListBySelecteId(int id, int limit, int studyId);
}
