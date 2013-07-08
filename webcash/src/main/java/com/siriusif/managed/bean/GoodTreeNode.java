package com.siriusif.managed.bean;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.siriusif.model.Good;

public class GoodTreeNode extends DefaultTreeNode {

	private Good good;

	public GoodTreeNode(Good good, TreeNode root) {
		super(good, root);
		this.good = good;
	}

	public Object getData() {
		return good.getName() + " - " + good.getPrice();
	}

}
