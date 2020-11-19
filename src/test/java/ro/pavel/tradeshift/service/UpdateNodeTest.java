package ro.pavel.tradeshift.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.MockitoJUnitRunner;
import ro.pavel.tradeshift.dto.NodeDt;
import ro.pavel.tradeshift.enity.Node;
import ro.pavel.tradeshift.repository.NodeRepo;

import java.math.BigInteger;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * unit test for {@link UpdateNode}
 */
@RunWith(MockitoJUnitRunner.class)
public class UpdateNodeTest {
	@InjectMocks
	private UpdateNode updateNode;

	@Mock
	private NodeRepo nodeRepo;
	private NodeDt nodeDt;

	@Before
	public void setUp() {
		nodeDt = new NodeDt();
		nodeDt.setId(BigInteger.ONE);
		nodeDt.setParent(BigInteger.TEN);
	}

	@Test(expected = IllegalArgumentException.class)
	public void updateNOK() {
		// given
		when(nodeRepo.getTopNodesIDs(1L)).thenReturn(Collections.singletonList(10L));
		// when
		updateNode.update(nodeDt);
		// then
		verify(nodeRepo, never()).save(any(Node.class));
	}

	@Test
	public void updateOK() {
		// given
		final Node node = new Node();

		when(nodeRepo.getTopNodesIDs(1L)).thenReturn(Collections.singletonList(3L));
		when(nodeRepo.getOne(1L)).thenReturn(node);
		// when
		updateNode.update(nodeDt);
		// then
		verify(nodeRepo, times(1)).save(node);
	}
}
