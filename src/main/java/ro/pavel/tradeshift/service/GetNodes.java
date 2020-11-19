package ro.pavel.tradeshift.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.pavel.tradeshift.dto.NodeDt;
import ro.pavel.tradeshift.repository.NodeRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetNodes {

	@Autowired
	private NodeRepo nodeRepo;

	public List<NodeDt> getNodes(long nodeId) {
		final List<NodeDt> nodeDts;
		final List<Object[]> topNodes = nodeRepo.getTopNodes(nodeId);
		nodeDts = topNodes.stream().map(objects1 -> new NodeDt(objects1, topNodes.size())).collect(Collectors.toList());
		nodeDts.addAll(nodeRepo.getSubTree(nodeId, nodeDts.size() + 2).stream().map(objects -> new NodeDt(objects)).collect(Collectors.toList()));
		return nodeDts;
	}
}
