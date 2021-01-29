package arvasis.sensor.studio.tree;

public class TreeNode {

	private Object image;
	private String processString;
	private String nodeName;
	private boolean isEmpty=false;
	
	public TreeNode() {
		this.nodeName="Empty Node";
		this.isEmpty=true;
		
	}
	public TreeNode(String nodeName, Object image, String processString) {
		this.nodeName = nodeName;
		this.image = image;
		this.processString = processString;
	}
	public TreeNode(String nodeName,String processName) {
		this.nodeName=nodeName;
		this.processString=processName;
	}
	public TreeNode(String nodeName, Object image) {
		this.nodeName = nodeName;
		this.image = image;
	}

	public TreeNode(String nodeName) {
		this.nodeName = nodeName;
	}

	public Object getImage() {
		return image;
	}

	public void setImage(Object image) {
		this.image = image;
	}

	public String getProcessString() {
		return processString;
	}

	public void setProcessString(String processString) {
		this.processString = processString;
	}

	@Override
	public String toString() {
		return nodeName;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public boolean isEmpty() {
		return isEmpty;
	}
	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

}
