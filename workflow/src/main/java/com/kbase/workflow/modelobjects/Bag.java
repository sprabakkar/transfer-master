package gov.loc.cts.workflow.modelobjects;

import java.io.Serializable;

public class Bag implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String projectId;
private String bagId;
private String bagKey;
private String version;
public String getProjectId() {
	return projectId;
}
public void setProjectId(String projectId) {
	this.projectId = projectId;
}
public Bag(String projectId, String bagId, String bagKey, String version) {
	super();
	this.projectId = projectId;
	this.bagId = bagId;
	this.bagKey = bagKey;
	this.version = version;
}
public String getBagId() {
	return bagId;
}
public void setBagId(String bagId) {
	this.bagId = bagId;
}
public String getBagKey() {
	return bagKey;
}
public void setBagKey(String bagKey) {
	this.bagKey = bagKey;
}
public String getVersion() {
	return version;
}
public void setVersion(String version) {
	this.version = version;
}
public static long getSerialversionuid() {
	return serialVersionUID;
}


}
