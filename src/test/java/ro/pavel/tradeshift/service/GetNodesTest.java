package ro.pavel.tradeshift.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.MockitoJUnitRunner;
import ro.pavel.tradeshift.repository.NodeRepo;

import java.math.BigInteger;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * unit test for {@link GetNodes}
 */
@RunWith(MockitoJUnitRunner.class)
public class GetNodesTest {
	@InjectMocks
	private GetNodes getNodes;

	@Mock
	private NodeRepo nodeRepo;

	@Test
	public void getNodes() {
		// given
		final long nodeId = 1L;

		// when
		when(nodeRepo.getTopNodes(nodeId)).thenReturn(Collections.singletonList(new Object[]{BigInteger.ONE, BigInteger.ONE, "description", BigInteger.ZERO, "path"}));
		when(nodeRepo.getSubTree(nodeId, 2)).thenReturn(Collections.singletonList(new Object[]{BigInteger.ONE, BigInteger.ONE, "description", BigInteger.ONE, "path"}));
		// then
		assertEquals(2, getNodes.getNodes(nodeId).size());
	}
}
