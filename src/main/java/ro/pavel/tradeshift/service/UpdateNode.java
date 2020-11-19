package ro.pavel.tradeshift.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.pavel.tradeshift.dto.NodeDt;
import ro.pavel.tradeshift.enity.Node;
import ro.pavel.tradeshift.repository.NodeRepo;

@Service
@Transactional
public class UpdateNode {

	@Autowired
	private NodeRepo nodeRepo;

	public void update(NodeDt nodeDt) {
		boolean match = nodeRepo.getTopNodesIDs(nodeDt.getId().longValue()).stream().anyMatch(number -> number.longValue() == nodeDt.getParent().longValue());
		if (match) {
			throw new IllegalArgumentException(" Node can not be updated");
		}
		final Node node = nodeRepo.getOne(nodeDt.getId().longValue());
		node.setParent(nodeDt.getParent().longValue());
		nodeRepo.save(node);
	}
}
