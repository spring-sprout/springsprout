package springsprout.modules.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springsprout.domain.Role;

import java.util.List;

@Service
@Transactional
public class RoleService {

	@Autowired private RoleRepository roleRepository;

	public void delete(int id) {
		roleRepository.deleteById(id);
	}

	public void update(Role role) {
		roleRepository.update(role);
	}

    public List<Role> getAll() {
        return roleRepository.getAll();
    }

    public Role getRoleById(int id) {
        return roleRepository.getById(id);
    }

    public void add(Role role) {
        roleRepository.add(role);
    }
}
