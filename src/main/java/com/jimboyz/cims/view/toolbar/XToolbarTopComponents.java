package com.jimboyz.cims.view.toolbar;

import com.jimboyz.cims.AppProperties;
import com.jimboyz.cims.view.SwingUtil;
import com.jimboyz.cims.view.TableSelectionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.Serial;

public class XToolbarTopComponents extends JToolBar implements TableSelectionListener {
	
	@Serial
	private static final long serialVersionUID = 1L;

	AppProperties properties;

	private ToolButton dashboard;
	private ToolButton addPatients;
	private ToolButton editPatients;
	private ToolButton deletePatients;
	private ToolButton viewPatients;
	private ToolButton refreshTable;
	private ToolButton editMedicalRecords;

	public XToolbarTopComponents() {

		properties = new AppProperties();

		this.setOrientation(JToolBar.HORIZONTAL);
		this.setFloatable(properties.isFloatable);
		this.setRollover(properties.isRollOver);
		this.setMargin(new Insets(3, 0, 3, 0));
		this.setPreferredSize(new Dimension(25, 25));

		init();
	}

	private void init() {
		dashboard = new ToolButton(XToolbarTopComponents.this, SwingUtil.newImageIcon("/com/jimboyz/cims/images/user.png"), "dashboard");
		addPatients = new ToolButton(XToolbarTopComponents.this, SwingUtil.newImageIcon("/com/jimboyz/cims/images/addPatients.png"), "addPatients");
		editPatients = new ToolButton(XToolbarTopComponents.this, SwingUtil.newImageIcon("/com/jimboyz/cims/images/edit.png"), "editPatients");
		deletePatients = new ToolButton(XToolbarTopComponents.this, SwingUtil.newImageIcon("/com/jimboyz/cims/images/trash.png"), "deletePatients");
		viewPatients = new ToolButton(XToolbarTopComponents.this, SwingUtil.newImageIcon("/com/jimboyz/cims/images/folder_open.png"), "viewPatients");
		refreshTable = new ToolButton(XToolbarTopComponents.this, SwingUtil.newImageIcon("/com/jimboyz/cims/images/table_refresh.png"), "refresh");
		editMedicalRecords = new ToolButton(XToolbarTopComponents.this, SwingUtil.newImageIcon("/com/jimboyz/cims/images/edit.png"), "editMedicalRecords");

		addPatients.setToolTipText("Add Patients");
		editPatients.setToolTipText("Edit");
		deletePatients.setToolTipText("Delete");
		viewPatients.setToolTipText("Open");
		refreshTable.setToolTipText("Refresh Table");
		editMedicalRecords.setToolTipText("Edit Medical Data");

		this.add(dashboard);
		this.addSeparator();
		this.add(addPatients);
		this.addSeparator();
		this.add(editPatients).setEnabled(false);
		this.add(deletePatients).setEnabled(false);
		this.add(viewPatients).setEnabled(false);
		this.addSeparator(new Dimension(20, 25));
		this.add(editMedicalRecords).setEnabled(false);
		this.addSeparator(new Dimension(20, 25));
		this.add(refreshTable);
	}

	public void showDashboardPage(ActionListener e) {
		dashboard.addActionListener(e);
	}

	public void showAddPatientsPage(ActionListener e) {
		addPatients.addActionListener(e);
	}

	public void showEditPatientsPage(ActionListener e) {
		editPatients.addActionListener(e);
	}

	public void deletePatients(ActionListener e) {
		deletePatients.addActionListener(e);
	}

	public void showEditMedicalDataPage(ActionListener e) {
		editMedicalRecords.addActionListener(e);
	}

//	public void showDeletePatientsPage(ActionListener e) {
//		deletePatients.addActionListener(e);
//	}

	public void showViewPatientsPage(ActionListener e) {
		viewPatients.addActionListener(e);
	}

	public void refreshTable(ActionListener e) {
		refreshTable.addActionListener(e);
	}

	@Override
	public void tableSelectionChanged(JTable table) {
        setToolComponentsEnabled(table.getSelectedRow() != -1);
	}

	public void setToolComponentsEnabled(boolean enable) {
		editPatients.setEnabled(enable);
		deletePatients.setEnabled(enable);
		viewPatients.setEnabled(enable);
		editMedicalRecords.setEnabled(enable);
	}
}
