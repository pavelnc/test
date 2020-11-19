package ro.pavel.tradeshift.dto;

public class NodeDt {
	private Number id;
	private String description;
	private Number parent;
	private String path;
	private Number lvl;


	public Number getId() {
		return id;
	}

	public void setId(Number id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Number getParent() {
		return parent;
	}

	public void setParent(Number parent) {
		this.parent = parent;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Number getLvl() {
		return lvl;
	}

	public void setLvl(Number lvl) {
		this.lvl = lvl;
	}
}
