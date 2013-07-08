package com.siriusif.managed.bean;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.siriusif.model.Group;

public class GroupTreeNode extends DefaultTreeNode {

	private Group group;

	public GroupTreeNode(Group group, TreeNode root) {
		super(group, root);
		this.group = group;
	}

	public Object getData() {
		return group.getName();
	}

}
