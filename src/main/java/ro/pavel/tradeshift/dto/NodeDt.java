package ro.pavel.tradeshift.dto;

import java.math.BigInteger;

public class NodeDt {
	private BigInteger id;
	private String description;
	private BigInteger parent;
	private String path;
	private BigInteger lvl;

	public NodeDt() {
	}

	public NodeDt(Object[] objects) {

		lvl = (BigInteger) objects[3];
		setNodeValues(objects);
	}

	private void setNodeValues(Object[] objects) {
		if (objects[0] != null) {
			if (objects[0] instanceof Integer) {
				parent = BigInteger.valueOf((Integer) objects[0]);
			} else {
				parent = (BigInteger) objects[0];
			}
		}
		if (objects[1] instanceof Integer) {
			id = BigInteger.valueOf((Integer) objects[1]);
		} else {
			id = (BigInteger) objects[1];
		}
		description = (String) objects[2];

		path = (String) objects[4];
	}

	public NodeDt(Object[] objects, int size) {
		lvl = BigInteger.valueOf(size - ((BigInteger) objects[3]).intValue());
		setNodeValues(objects);
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigInteger getParent() {
		return parent;
	}

	public void setParent(BigInteger parent) {
		this.parent = parent;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public BigInteger getLvl() {
		return lvl;
	}

	public void setLvl(BigInteger lvl) {
		this.lvl = lvl;
	}
}
