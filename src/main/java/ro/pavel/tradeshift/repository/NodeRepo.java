package ro.pavel.tradeshift.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.pavel.tradeshift.enity.Node;

import java.util.List;


public interface NodeRepo extends JpaRepository<Node, Long> {


	@Query(nativeQuery = true, value = "WITH RECURSIVE category_path (id, description, parent, path, lvl) AS" +
			"(" +
			"  SELECT id, description, parent,' ' path, 0 lvl" +
			"    FROM Node" +
			"    WHERE id = :nodeId" +
			"  UNION ALL" +
			"  SELECT c.id, c.description, c.parent,cp.path, cp.lvl + 1" +
			"    FROM category_path AS cp JOIN Node AS c" +
			"      ON cp.parent = c.id" +
			")" +
			"SELECT category_path.parent as parent, category_path.id as id, category_path.description as description," +
			" category_path.lvl as lvl, category_path.path as path FROM category_path")
	List<Object[]> getTopNodes(@Param("nodeId") Long nodeId);

	@Query(nativeQuery = true, value = "WITH RECURSIVE category_path (id,  parent) AS" +
			"(" +
			"  SELECT id,  parent    FROM Node" +
			"    WHERE id = :nodeId" +
			"  UNION ALL" +
			"  SELECT c.id, c.parent" +
			"    FROM category_path AS cp JOIN Node AS c" +
			"      ON cp.parent = c.id" +
			")" +
			"SELECT  category_path.id as id FROM category_path")
	List<Long> getTopNodesIDs(@Param("nodeId") Long nodeId);

	@Query(nativeQuery = true, value = "WITH RECURSIVE category_path (id, description, parent, path, lvl) AS" +
			"(" +
			"  SELECT id, description, :nodeId parent,description as path, :defaultLvl lvl" +
			"    FROM Node" +
			"    WHERE parent = :nodeId" +
			"  UNION ALL" +
			"  SELECT c.id, c.description,c.parent parent, CONCAT(cp.path, ' > ', c.description), cp.lvl + 1" +
			"    FROM category_path AS cp JOIN Node AS c" +
			"      ON cp.id = c.parent" +
			")" +
			"SELECT category_path.parent as parent, category_path.id as nodeId, category_path.description as description," +
			" category_path.lvl as lvl, category_path.path as path FROM category_path")
	List<Object[]> getSubTree(@Param("nodeId") Long nodeId, @Param("defaultLvl") int defaultLvl);


}
