package com.siriusif.managed.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.siriusif.model.Good;
import com.siriusif.model.Group;
import com.siriusif.service.model.GroupDao;

import static com.siriusif.model.helpers.TestHelper.money;

@ManagedBean(name = "groupTree")
public class GroupTree {

	private TreeNode root;

	@ManagedProperty(value = "#{groupDao}")
	private GroupDao groupDao;

	private List<Group> groups;

	public GroupTree() {
	}

	public TreeNode getRoot() {
		root = new DefaultTreeNode("Root", null);

		groups = groupDao.list();
		for (Group group : groups) {
			if (group.getParentGroup() == null) {
				TreeNode nodeGroup = new GroupTreeNode(group, root);
				loadSubGroups(group, nodeGroup);
				loadGoods(group, nodeGroup);
			}
		}
		return root;
	}

	private void loadSubGroups(Group group, TreeNode nodeGroup) {
		groups = group.getSubGroups();
		for (Group subGroup : groups) {
			TreeNode nodeSubGroup = new GroupTreeNode(subGroup, nodeGroup);
			loadSubGroups(subGroup, nodeSubGroup);
			loadGoods(subGroup, nodeSubGroup);
		}
	}

	private void loadGoods(Group group, TreeNode nodeGroup) {
		List<Good> goods = group.getGoods();
		for (Good good : goods) {
			TreeNode nodeGood = new GoodTreeNode(good, nodeGroup);
		}
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public GroupDao getGroupDao() {
		return groupDao;
	}

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}
}
