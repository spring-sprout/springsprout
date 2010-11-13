package springsprout.modules.right;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springsprout.domain.Right;

@Service
@Transactional
public class RightService {
	
	@Autowired private RightRepository repository;

	public void add(Right right) {
		repository.add(right);
	}

	public List<Right> getRightList() {
		return repository.getRightList();
	}

	public Right getRightById(int id) {
		return repository.getById(id);
	}

	public void delete(int id) {
		repository.deleteById(id);
	}

	public void update(Right right) {
		repository.update(right);
	}

}
