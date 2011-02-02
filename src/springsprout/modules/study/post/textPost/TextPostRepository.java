package springsprout.modules.study.post.textPost;

import springsprout.common.dao.GenericDao;
import springsprout.domain.study.board.TextPost;

import java.util.List;

public interface TextPostRepository extends GenericDao<TextPost> {
	
	/**
	 * 목록에서 보여줄 리스트를 가져 온다
	 * @return
	 */
	List<TextPost> getRootPostList();
	
	/**
	 * 페이징 합시다.
	 * @param start
	 * @param end
	 * @return
	 */
	List<TextPost> getRootPostList(int start, int end, int studyId);
	
	/**
	 * 제목으로 검색하기, 일단 제목 만.
	 * @param title
	 * @return
	 */
	List<TextPost> findPostByTitle(String title);
	
	/**
	 * 선택한 글의 자식글들을 가져 온다.
	 * @param textPost 선택한 글
	 * @return
	 */
	List<TextPost> getBranchPostsOf( TextPost textPost);
	
	int getCount();
	
	int getRootPostCount();

    int getRootPostCount(int studyId);
}
