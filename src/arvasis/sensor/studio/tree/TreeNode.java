package arvasis.sensor.studio.tree;

public class TreeNode {

	private Object image;
	private String processString;
	private String nodeName;
	private boolean isEmpty=false;
	private boolean isConditionOrLoop=false;
	private boolean isConditionOrLoopHead=false;
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
	public boolean isCondition() {
		return isConditionOrLoop;
	}
	public void setCondition(boolean isCondition) {
		this.isConditionOrLoop = isCondition;
	}
	public boolean isConditionOrLoopHead() {
		return isConditionOrLoopHead;
	}
	public void setConditionOrLoopHead(boolean isConditionOrLoopHead) {
		this.isConditionOrLoopHead = isConditionOrLoopHead;
	}

}
