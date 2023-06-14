package models;

import java.util.ArrayList;
import java.util.List;

public class Group {

	private int id;
	private String groupName;
	private List<Student> members;
	private int groupCapacity;
	private boolean hasSlots;
	
	public Group()
	{
//		this.members = new ArrayList<>();
	}
	
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupName() {
		return groupName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Student> getMembers() {
		return members;
	}

	public void setMembers(List<Student> members) {
		this.members = members;
	}

	public int getGroupCapacity() {
		return groupCapacity;
	}

	public void setGroupCapacity(int groupCapacity) {
		this.groupCapacity = groupCapacity;
	}

	public boolean isHasSlots() {
		return hasSlots;
	}

	public void setHasSlots(boolean hasSlots) {
		this.hasSlots = hasSlots;
	}

	
	
}
