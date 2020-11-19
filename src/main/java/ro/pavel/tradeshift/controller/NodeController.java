package ro.pavel.tradeshift.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.pavel.tradeshift.dto.NodeDt;
import ro.pavel.tradeshift.service.GetNodes;
import ro.pavel.tradeshift.service.UpdateNode;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class NodeController {

	@Autowired
	private GetNodes getNodes;

	@Autowired
	private UpdateNode updateNode;

	@GetMapping
	public List<NodeDt> getNodes(@RequestParam int nodeId) {
		return getNodes.getNodes(nodeId);
	}

	@PutMapping
	public void updateNode(@RequestBody NodeDt nodeDt) {
		updateNode.update(nodeDt);
	}
}
