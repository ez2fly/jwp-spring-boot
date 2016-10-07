package next.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import next.model.Fido;

public interface FidoRepository extends JpaRepository<Fido, Long> {
	
	List<Fido> findAll();
	
	Fido findByCredId(String credId);

}
