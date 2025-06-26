package com.jimboyz.cims.view.tree;

import com.jimboyz.cims.AppProperties;
import com.jimboyz.cims.view.SwingUtil;
import javax.swing.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;

public class TreeComponents extends JTree {

    private DefaultTreeCellRenderer treeCellRenderer;
    private AppProperties appProperties;

    private DefaultMutableTreeNode root;
    private DefaultMutableTreeNode collegeNode;
    private DefaultMutableTreeNode highSchoolNode;
    private DefaultMutableTreeNode trsNode;
    private DefaultMutableTreeNode otherCourseNode;

    private DefaultMutableTreeNode bsitNode;
    private DefaultMutableTreeNode bsbaNode;
    private DefaultMutableTreeNode bscrimNode;
    private DefaultMutableTreeNode actNode;

    private DefaultMutableTreeNode juniorHighNode;
    private DefaultMutableTreeNode seniorHighNode;

    private DefaultMutableTreeNode dhmtNode;
    private DefaultMutableTreeNode hrmNode;

    public TreeComponents() {
        init();
    }

    private void init() {
        treeCellRenderer = new DefaultTreeCellRenderer();
        appProperties = new AppProperties();
        root = new DefaultMutableTreeNode();

        collegeNode = new DefaultMutableTreeNode(TreeNodeModel.College);
        highSchoolNode = new DefaultMutableTreeNode(TreeNodeModel.HIGHS_SCHOOL.getCourse());
        trsNode = new DefaultMutableTreeNode(TreeNodeModel.TRS);
        otherCourseNode = new DefaultMutableTreeNode(TreeNodeModel.OTHER_COURSE.getDepartment());

        bsitNode = new DefaultMutableTreeNode(TreeNodeModel.BSIT);
        bsbaNode = new DefaultMutableTreeNode(TreeNodeModel.BSBA);
        bscrimNode = new DefaultMutableTreeNode(TreeNodeModel.BSCRIM);
        actNode = new DefaultMutableTreeNode(TreeNodeModel.ACT);

        juniorHighNode = new DefaultMutableTreeNode(TreeNodeModel.JUNIOR_HIGH.getCourse());
        seniorHighNode = new DefaultMutableTreeNode(TreeNodeModel.SENIOR_HIGH.getCourse());

        dhmtNode = new DefaultMutableTreeNode(TreeNodeModel.DHMT);
        hrmNode = new DefaultMutableTreeNode(TreeNodeModel.HRM);

        collegeNode.add(bsitNode);
        collegeNode.add(bsbaNode);
        collegeNode.add(bscrimNode);
        collegeNode.add(actNode);

        highSchoolNode.add(juniorHighNode);
        highSchoolNode.add(seniorHighNode);

        trsNode.add(dhmtNode);
        trsNode.add(hrmNode);

        root.add(new DefaultMutableTreeNode(TreeNodeModel.ALL_RECORDS.getDepartment()));
        root.add(collegeNode);
        root.add(highSchoolNode);
        root.add(trsNode);
        root.add(otherCourseNode);

        treeCellRenderer.setClosedIcon(SwingUtil.newImageIcon("/com/jimboyz/cims/images/folder.gif"));
        treeCellRenderer.setOpenIcon(SwingUtil.newImageIcon("/com/jimboyz/cims/images/folder-open.png"));
        treeCellRenderer.setLeafIcon(SwingUtil.newImageIcon("/com/jimboyz/cims/images/folder.gif"));

        this.setModel(new DefaultTreeModel(root));
        this.setRootVisible(false);
        this.setFont(new Font("Dialog", Font.PLAIN, 12));
        this.setCellRenderer(treeCellRenderer);
        this.setShowsRootHandles(appProperties.isRootHandleShow);

        this.expandPath(new TreePath(collegeNode.getPath()));
        this.expandPath(new TreePath(highSchoolNode.getPath()));
        this.expandPath(new TreePath(trsNode.getPath()));

        this.setSelectionRow(0);
    }

    public void setTreeSelectionListener(TreeSelectionListener listener) {
        this.addTreeSelectionListener(listener);
    }
}
