package ro.pavel.tradeshift.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ro.pavel.tradeshift.dto.NodeDt;
import ro.pavel.tradeshift.service.GetNodes;
import ro.pavel.tradeshift.service.UpdateNode;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * unit test for {@link NodeController}
 */
@RunWith(SpringRunner.class)
@WebMvcTest(NodeController.class)
public class NodeControllerTest {

	@MockBean
	private GetNodes getNodes;

	@MockBean
	private UpdateNode updateNode;

	@Autowired
	private MockMvc mockMvc;

	public static final ObjectMapper MAPPER = new ObjectMapper();

	@Test
	public void getNodes() throws Exception {
		// given
		final List<NodeDt> nodeDts = Collections.singletonList(new NodeDt());
		when(getNodes.getNodes(1L)).thenReturn(nodeDts);
		// when
		mockMvc.perform(get("?nodeId=1"))
				.andExpect(status().isOk())
				.andExpect(content().string(MAPPER.writeValueAsString(nodeDts)));
		// then

	}

	@Test
	public void updateNode() throws Exception {
		// given
		final NodeDt nodeDt = new NodeDt();
		MockHttpServletRequestBuilder builder =
				MockMvcRequestBuilders.put("")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
						.characterEncoding("UTF-8")
						.content(MAPPER.writeValueAsString(nodeDt));
		// when
		this.mockMvc.perform(builder)
				.andExpect(MockMvcResultMatchers.status()
						.isOk());
		// then
	}
}
