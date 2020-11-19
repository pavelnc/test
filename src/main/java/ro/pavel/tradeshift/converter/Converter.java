package ro.pavel.tradeshift.converter;

import ro.pavel.tradeshift.dto.NodeDt;

public class Converter {

	public static NodeDt convertToDto(Object[] objects) {
		final NodeDt nodeDt = new NodeDt();
		nodeDt.setLvl((Number) objects[3]);
		setNodeValues(objects, nodeDt);
		return nodeDt;
	}

	public static NodeDt convertToDto(Object[] objects,int size) {
		final NodeDt nodeDt = new NodeDt();
		nodeDt.setLvl(size - ((Number) objects[3]).intValue());
		setNodeValues(objects, nodeDt);
		return nodeDt;
	}

	private static void setNodeValues(Object[] objects, NodeDt nodeDt) {
		if (objects[0] != null) {

			nodeDt.setParent((Number) objects[0]);
		}
		nodeDt.setId((Number) objects[1]);
		nodeDt.setDescription((String) objects[2]);
		nodeDt.setPath((String) objects[4]);
	}
}
