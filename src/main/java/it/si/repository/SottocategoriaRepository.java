package it.si.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.si.model.Sottocategoria;

@Repository
public interface SottocategoriaRepository extends JpaRepository<Sottocategoria, Integer>{

	@Query("SELECT s FROM Sottocategoria s JOIN s.area a WHERE a.id = :idArea")
	public List<Sottocategoria> findAllByArea(@Param("idArea") int idArea);
	
	@Query("SELECT s FROM Sottocategoria s JOIN s.area a ORDER BY a.nomeArea ASC")
	List<Sottocategoria> findAllSortByArea();

	@Query("UPDATE Sottocategoria SET budgetSpeso = budgetSpeso + :totale WHERE id = :idSotto")
	public void settaBudgetSpeso(@Param("totale") double totale,@Param("idSotto") Integer idSotto);

}
