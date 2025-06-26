package com.jimboyz.cims.view;

import java.awt.*;
import java.util.Date;
import javax.swing.*;

import com.jimboyz.cims.AppProperties;
import com.jimboyz.cims.MySimpleDateFormat;
import com.toedter.calendar.JDateChooser;
import net.miginfocom.swing.*;
/*
 * Created by Jimboy M. Sarona on Mon Mar 24 14:54:16 PST 2025
 */
/**
 * @author jimboy Ni ChOy!!!
 */
public class MedicalDataPage extends JPanel {

    private JCheckBox chkYesHypertension;
	private JCheckBox chkNoHypertension;
    private JCheckBox chkYesDiabetes;
	private JCheckBox chkNoDiabetes;
    private JCheckBox chkYesBronchial;
	private JCheckBox chkNoBronchial;
    private JCheckBox chkYesHeartDisease;
	private JCheckBox chkNoHeartDisease;
    private JCheckBox chkYesThyroid;
	private JCheckBox chkNoThyroid;
    private JCheckBox chkYesCopd;
	private JCheckBox chkNoCopd;
    private JCheckBox chkYesPud;
	private JCheckBox chkNoPud;
	private JPanel panelContainer2;
    private JTextField txtHeight;
    private JTextField txtWeight;
    private JTextField txtPulseRate;
    private JTextField txtBp;
    private JTextField txtBloodType;
    private JScrollPane scrollPane1;
	private JTextArea taRemarks;
	private JTextArea taFamilyHistory;
    private JTextField txtDevNormal;
	private JTextField txtPositiveDev;
	private JTextField txtRemarksDev;
    private JTextField txtGeneralAppearanceNormal;
	private JTextField txtPositiveGeneralAppearance;
	private JTextField txtRemarksGeneralAppearance;
    private JTextField txtEarNormal;
	private JTextField txtPositiveEar;
	private JTextField txtRemarksEar;
    private JTextField txtHeadNormal;
	private JTextField txtPositiveHead;
	private JTextField txtRemarksHead;
    private JTextField txtNormalChest;
	private JTextField txtPositiveChest;
	private JTextField txtRemarksChest;
    private JTextField txtNormalHeart;
	private JTextField txtPositiveHeart;
	private JTextField txtRemarksHeart;
    private JTextField txtNormalAbdomen;
	private JTextField txtPositiveAbdomen;
	private JTextField txtRemarksAbdomen;
    private JTextField txtNormalGenitals;
	private JTextField txtPositiveGenitals;
	private JTextField txtRemarksGenitals;
    private JTextField txtNormalExtremities;
	private JTextField txtPositiveExtremities;
	private JTextField txtRemarksExtremities;
    private JTextField txtNormalNeuralgic;
	private JTextField txtPositiveNeuralgic;
	private JTextField txtRemarksNeuralgic;
    private JScrollPane scrollPane2;
	private JTextArea taDiagnosis;
	private JScrollPane scrollPane3;
	private JTextArea taRecommendation;
    private JTextField txtPhysician;
    private JDateChooser dateChooser;
    private JTextField txtLicense;
    private JTextField txtTime;
    private JTextField txtMedicalExaminer;
	private final ButtonGroup hypertensionGroup;
	private final ButtonGroup diabetesGroup;
	private final ButtonGroup bronchialGroup;
	private final ButtonGroup heartDiseaseGroup;
	private final ButtonGroup thyroidGroup;
	private final ButtonGroup copdGroup;
	private final ButtonGroup pudGroup;
	private final AppProperties appProperties;

	public MedicalDataPage() {
		appProperties = new AppProperties();
		hypertensionGroup = new ButtonGroup();
		diabetesGroup = new ButtonGroup();
		bronchialGroup = new ButtonGroup();
		heartDiseaseGroup = new ButtonGroup();
		thyroidGroup = new ButtonGroup();
		copdGroup = new ButtonGroup();
		pudGroup = new ButtonGroup();
		initComponents();
	}

	private void initComponents() {

		JLabel lblPreMedIllness = SwingUtil.label("Pre Medical Illness", new Font(appProperties.addMedicalPageFontFamily, appProperties.addMedicalPageFontStyle, 25), appProperties.addMedicalPageFontColor);
		JLabel lblYes = SwingUtil.label("YES", lblPreMedIllness.getFont(), lblPreMedIllness.getForeground(), SwingConstants.CENTER);
		JLabel lblNo = SwingUtil.label("NO", lblPreMedIllness.getFont(), lblPreMedIllness.getForeground(), SwingConstants.CENTER);

		JLabel lblHypertension = SwingUtil.label("1. HYPERTENSION", new Font(appProperties.addMedicalPageFontFamily, appProperties.addMedicalPageFontStyle, appProperties.addMedicalPageFontSize), appProperties.addMedicalPageFontColor);
		chkYesHypertension = new JCheckBox();
		chkNoHypertension = new JCheckBox();
		JLabel lblDiabetes = SwingUtil.label("2. DIABETES", lblHypertension.getFont(), lblHypertension.getForeground());
		chkYesDiabetes = new JCheckBox();
		chkNoDiabetes = new JCheckBox();
		JLabel lblBronchial = SwingUtil.label("3. BRONCHIAL ASTHMA/ALLERGIES", lblHypertension.getFont(), lblHypertension.getForeground());
		chkYesBronchial = new JCheckBox();
		chkNoBronchial = new JCheckBox();
		JLabel lblHeartDisease = SwingUtil.label("4. HEART DISEASE", lblHypertension.getFont(), lblHypertension.getForeground());
		chkYesHeartDisease = new JCheckBox();
		chkNoHeartDisease = new JCheckBox();
		JLabel lblThyroid = SwingUtil.label("5. THYROID/ENDOCRINE PROBLEMS", lblHypertension.getFont(), lblHypertension.getForeground());
		chkYesThyroid = new JCheckBox();
		chkNoThyroid = new JCheckBox();
		JLabel lblCopd = SwingUtil.label("6. COPD/PTB/OTHER LUNG PROBLEMS", lblHypertension.getFont(), lblHypertension.getForeground());
		chkYesCopd = new JCheckBox();
		chkNoCopd = new JCheckBox();
		JLabel lblPud = SwingUtil.label("7. PUD/GASTRITIS/GI PROBLEMS", lblHypertension.getFont(), lblHypertension.getForeground());
		chkYesPud = new JCheckBox();
		chkNoPud = new JCheckBox();
		panelContainer2 = new JPanel();
		JLabel lblHeight = SwingUtil.label("Height:", lblHypertension.getFont(), lblHypertension.getForeground());
		txtHeight = SwingUtil.textField("txt-height", new Font(appProperties.addMedicalPageFontFamily, appProperties.addMedicalPageFontStyle, appProperties.addMedicalPageFontSize), appProperties.addMedicalPageFontColor);
		JLabel lblWeight = SwingUtil.label("Weight:", lblHypertension.getFont(), lblHypertension.getForeground());
		txtWeight = SwingUtil.textField("txt-weight", txtHeight.getFont(), txtHeight.getForeground());
		JLabel lblPulseRate = SwingUtil.label("Pulse Rate:", lblHypertension.getFont(), lblHypertension.getForeground());
		txtPulseRate = SwingUtil.textField("txt-pulse-rate", txtHeight.getFont(), txtHeight.getForeground());
		JLabel lblBp = SwingUtil.label("BP.:", lblHypertension.getFont(), lblHypertension.getForeground());
		txtBp = SwingUtil.textField("txt-bp", txtHeight.getFont(), txtHeight.getForeground());
		JLabel lblBloodType = SwingUtil.label("Blood Type:", lblHypertension.getFont(), lblHypertension.getForeground());
		txtBloodType =SwingUtil.textField("txt-blood-type", txtHeight.getFont(), txtHeight.getForeground());
		JLabel lblRemarks = SwingUtil.label("REMARKS:", lblHypertension.getFont(), lblHypertension.getForeground());
		JLabel lblFamHistory = SwingUtil.label("FAMILY HISTORY:", lblHypertension.getFont(), lblHypertension.getForeground());
		scrollPane1 = new JScrollPane();
		taRemarks = new JTextArea();
		taFamilyHistory = new JTextArea();

		taRemarks.setFont(txtHeight.getFont());
		taFamilyHistory.setFont(txtHeight.getFont());
		taRemarks.setForeground(txtHeight.getForeground());
		taFamilyHistory.setForeground(txtHeight.getForeground());

		JLabel lblFindings = SwingUtil.label("FINDINGS", lblHypertension.getFont(), lblHypertension.getForeground(), SwingConstants.CENTER);
		JLabel lblNormal = SwingUtil.label("NORMAL", lblHypertension.getFont(), lblHypertension.getForeground(), SwingConstants.CENTER);
		JLabel lblPositive = SwingUtil.label("POSITIVE", lblHypertension.getFont(), lblHypertension.getForeground(), SwingConstants.CENTER);
		JLabel lblRemarks02 = SwingUtil.label("REMARKS", lblHypertension.getFont(), lblHypertension.getForeground(), SwingConstants.CENTER);
		JLabel lblDev = SwingUtil.label("A. Development Nutrition Body Built", lblHypertension.getFont(), lblHypertension.getForeground());
		txtDevNormal = SwingUtil.textField("txt-dev-normal", txtHeight.getFont(), txtHeight.getForeground());
		txtPositiveDev = SwingUtil.textField("txt-positive-dev", txtHeight.getFont(), txtHeight.getForeground());
		txtRemarksDev = SwingUtil.textField("txt-remarks-dev", txtHeight.getFont(), txtHeight.getForeground());
		JLabel lblGen = SwingUtil.label("B. General Appearance", lblHypertension.getFont(), lblHypertension.getForeground());
		txtGeneralAppearanceNormal = SwingUtil.textField("txt-weight", txtHeight.getFont(), txtHeight.getForeground());
		txtPositiveGeneralAppearance = SwingUtil.textField("txt-weight", txtHeight.getFont(), txtHeight.getForeground());
		txtRemarksGeneralAppearance = SwingUtil.textField("txt-weight", txtHeight.getFont(), txtHeight.getForeground());
		JLabel lblEar = SwingUtil.label("C. Ear, Nose, & Throat", lblHypertension.getFont(), lblHypertension.getForeground());
		txtEarNormal = SwingUtil.textField("txt-ear-normal", txtHeight.getFont(), txtHeight.getForeground());
		txtPositiveEar = SwingUtil.textField("txt-ear-positive", txtHeight.getFont(), txtHeight.getForeground());
		txtRemarksEar = SwingUtil.textField("txt-ear-remarks", txtHeight.getFont(), txtHeight.getForeground());
		JLabel lblHead = SwingUtil.label("D. Head & Eyes", lblHypertension.getFont(), lblHypertension.getForeground());
		txtHeadNormal = SwingUtil.textField("txt-normal-head", txtHeight.getFont(), txtHeight.getForeground());
		txtPositiveHead = SwingUtil.textField("txt-positive-head", txtHeight.getFont(), txtHeight.getForeground());
		txtRemarksHead = SwingUtil.textField("txt-remarks-head", txtHeight.getFont(), txtHeight.getForeground());
		JLabel lblChest = SwingUtil.label("E. Chest & Lungs", lblHypertension.getFont(), lblHypertension.getForeground());
		txtNormalChest = SwingUtil.textField("txt-normal-chest", txtHeight.getFont(), txtHeight.getForeground());
		txtPositiveChest = SwingUtil.textField("txt-positive-chest", txtHeight.getFont(), txtHeight.getForeground());
		txtRemarksChest = SwingUtil.textField("txt-remarks-chest", txtHeight.getFont(), txtHeight.getForeground());
		JLabel lblHeart = SwingUtil.label("F. Heart", lblHypertension.getFont(), lblHypertension.getForeground());
		txtNormalHeart = SwingUtil.textField("txt-normal-heart", txtHeight.getFont(), txtHeight.getForeground());
		txtPositiveHeart = SwingUtil.textField("txt-positive-heart", txtHeight.getFont(), txtHeight.getForeground());
		txtRemarksHeart = SwingUtil.textField("txt-remarks-heart", txtHeight.getFont(), txtHeight.getForeground());
		JLabel lblAbdomen = SwingUtil.label("G. Abdomen", lblHypertension.getFont(), lblHypertension.getForeground());
		txtNormalAbdomen = SwingUtil.textField("txt-normal-abdomen", txtHeight.getFont(), txtHeight.getForeground());
		txtPositiveAbdomen = SwingUtil.textField("txt-positive-abdomen", txtHeight.getFont(), txtHeight.getForeground());
		txtRemarksAbdomen = SwingUtil.textField("txt-remarks-abdomen", txtHeight.getFont(), txtHeight.getForeground());
		JLabel lblGenitals = SwingUtil.label("H. Genitals", lblHypertension.getFont(), lblHypertension.getForeground());
		txtNormalGenitals = SwingUtil.textField("txt-normal-genitals", txtHeight.getFont(), txtHeight.getForeground());
		txtPositiveGenitals = SwingUtil.textField("txt-positive-genitals", txtHeight.getFont(), txtHeight.getForeground());
		txtRemarksGenitals = SwingUtil.textField("txt-remarks-genitals", txtHeight.getFont(), txtHeight.getForeground());
		JLabel lblExtremities = SwingUtil.label("I. Extremities", lblHypertension.getFont(), lblHypertension.getForeground());
		txtNormalExtremities = SwingUtil.textField("txt-normal-extremities", txtHeight.getFont(), txtHeight.getForeground());
		txtPositiveExtremities = SwingUtil.textField("txt-positive-extremities", txtHeight.getFont(), txtHeight.getForeground());
		txtRemarksExtremities = SwingUtil.textField("txt-remarks-extremities", txtHeight.getFont(), txtHeight.getForeground());
		JLabel lblNeuralgic = SwingUtil.label("J. Neuralgic", lblHypertension.getFont(), lblHypertension.getForeground());
		txtNormalNeuralgic = SwingUtil.textField("txt-normal-neuralgic", txtHeight.getFont(), txtHeight.getForeground());
		txtPositiveNeuralgic = SwingUtil.textField("txt-positive-neuralgic", txtHeight.getFont(), txtHeight.getForeground());
		txtRemarksNeuralgic = SwingUtil.textField("txt-remarks-neuralgic", txtHeight.getFont(), txtHeight.getForeground());
		JLabel lblDiagnosis = SwingUtil.label("DIAGNOSIS:", lblHypertension.getFont(), lblHypertension.getForeground());
		JLabel lblRecommendation = SwingUtil.label("RECOMMENDATION:", lblHypertension.getFont(), lblHypertension.getForeground());
		scrollPane2 = new JScrollPane();
		taDiagnosis = new JTextArea();
		scrollPane3 = new JScrollPane();
		taRecommendation = new JTextArea();

		taDiagnosis.setFont(txtHeight.getFont());
		taRecommendation.setFont(txtHeight.getFont());
		taDiagnosis.setForeground(txtHeight.getForeground());
		taRecommendation.setForeground(txtHeight.getForeground());

		JLabel lblPhysician = SwingUtil.label("Physician:", lblHypertension.getFont(), lblHypertension.getForeground());
		txtPhysician = SwingUtil.textField("txt-physician", txtHeight.getFont(), txtHeight.getForeground());
		JLabel lblDate = SwingUtil.label("Date:", lblHypertension.getFont(), lblHypertension.getForeground());
		dateChooser = SwingUtil.dateChooser(new Date(), new Font(appProperties.dateChooserFont, appProperties.dateChooserFontStyle, appProperties.dateChooserFontSize), appProperties.dateFormatString);
		JLabel lblLicenseNo = SwingUtil.label("License No.:", lblHypertension.getFont(), lblHypertension.getForeground());
		txtLicense = SwingUtil.textField("txt-license", txtHeight.getFont(), txtHeight.getForeground());
		JLabel lblTime = SwingUtil.label("Time:", lblHypertension.getFont(), lblHypertension.getForeground());
		txtTime = SwingUtil.textField("txt-time", txtHeight.getFont(), txtHeight.getForeground());
		JLabel lblMedicalExaminer = SwingUtil.label("Medical Examiner LCR No. S3 No.:", lblHypertension.getFont(), lblHypertension.getForeground());
		txtMedicalExaminer = SwingUtil.textField("txt-medical-examiner", txtHeight.getFont(), txtHeight.getForeground());

		txtTime.setText(MySimpleDateFormat.getInstance().format(new Date(), appProperties.timeMedFormatString));

		//======== CheckBox ========
		chkYesHypertension.setCursor(new Cursor(Cursor.HAND_CURSOR));
		chkNoHypertension.setCursor(chkYesHypertension.getCursor());
		chkYesDiabetes.setCursor(chkYesHypertension.getCursor());
		chkNoDiabetes.setCursor(chkYesHypertension.getCursor());
		chkYesBronchial.setCursor(chkYesHypertension.getCursor());
		chkNoBronchial.setCursor(chkYesHypertension.getCursor());
		chkYesHeartDisease.setCursor(chkYesHypertension.getCursor());
		chkNoHeartDisease.setCursor(chkYesHypertension.getCursor());
		chkYesThyroid.setCursor(chkYesHypertension.getCursor());
		chkNoThyroid.setCursor(chkYesHypertension.getCursor());
		chkYesCopd.setCursor(chkYesHypertension.getCursor());
		chkNoCopd.setCursor(chkYesHypertension.getCursor());
		chkYesPud.setCursor(chkYesHypertension.getCursor());
		chkNoPud.setCursor(chkYesHypertension.getCursor());

		//======== Add to a ButtonGroup ========
		hypertensionGroup.add(chkYesHypertension);
		hypertensionGroup.add(chkNoHypertension);
		diabetesGroup.add(chkYesDiabetes);
		diabetesGroup.add(chkNoDiabetes);
		bronchialGroup.add(chkYesBronchial);
		bronchialGroup.add(chkNoBronchial);
		heartDiseaseGroup.add(chkYesHeartDisease);
		heartDiseaseGroup.add(chkNoHeartDisease);
		thyroidGroup.add(chkYesThyroid);
		thyroidGroup.add(chkNoThyroid);
		copdGroup.add(chkYesCopd);
		copdGroup.add(chkNoCopd);
		pudGroup.add(chkYesPud);
		pudGroup.add(chkNoPud);

		//======== this ========
		setLayout(new MigLayout(
			"hidemode 3",
			// columns
			"[fill]" +
			"[fill]" +
			"[fill]" +
			"[fill]" +
			"[fill]" +
			"[fill]" +
			"[fill]" +
			"[fill]",
			// rows
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]"));

		//---- lblPreMedIllness ----
		add(lblPreMedIllness, "cell 0 0 2 1");

		//---- lblYes ----
		add(lblYes, "cell 4 0, wmax 200, gapx 150");

		//---- lblNo ----
		add(lblNo, "cell 5 0, wmax 200, gapx 150");

		//---- lblHypertension ----
		add(lblHypertension, "cell 0 1");

		//---- chkYesHypertension ----
		chkYesHypertension.setHorizontalAlignment(SwingConstants.CENTER);
		add(chkYesHypertension, "cell 4 1, wmax 200, gapx 150");

		//---- chkNoHypertension ----
		chkNoHypertension.setHorizontalAlignment(SwingConstants.CENTER);
		add(chkNoHypertension, "cell 5 1, wmax 200, gapx 150");

		//---- lblDiabetes ----
		add(lblDiabetes, "cell 0 2");

		//---- chkYesDiabetes ----
		chkYesDiabetes.setHorizontalAlignment(SwingConstants.CENTER);
		add(chkYesDiabetes, "cell 4 2, wmax 200, gapx 150");

		//---- chkNoDiabetes ----
		chkNoDiabetes.setHorizontalAlignment(SwingConstants.CENTER);
		add(chkNoDiabetes, "cell 5 2, wmax 200, gapx 150");

		//---- lblBronchial ----
		add(lblBronchial, "cell 0 3");

		//---- chkYesBronchial ----
		chkYesBronchial.setHorizontalAlignment(SwingConstants.CENTER);
		add(chkYesBronchial, "cell 4 3, wmax 200, gapx 150");

		//---- chkNoBronchial ----
		chkNoBronchial.setHorizontalAlignment(SwingConstants.CENTER);
		add(chkNoBronchial, "cell 5 3, wmax 200, gapx 150");

		//---- lblHeartDisease ----
		add(lblHeartDisease, "cell 0 4");

		//---- chkYesHeartDisease ----
		chkYesHeartDisease.setHorizontalAlignment(SwingConstants.CENTER);
		add(chkYesHeartDisease, "cell 4 4, wmax 200, gapx 150");

		//---- chkNoHeartDisease ----
		chkNoHeartDisease.setHorizontalAlignment(SwingConstants.CENTER);
		add(chkNoHeartDisease, "cell 5 4, wmax 200, gapx 150");

		//---- lblThyroid ----
		add(lblThyroid, "cell 0 5");

		//---- chkYesThyroid ----
		chkYesThyroid.setHorizontalAlignment(SwingConstants.CENTER);
		add(chkYesThyroid, "cell 4 5, wmax 200, gapx 150");

		//---- chkNoThyroid ----
		chkNoThyroid.setHorizontalAlignment(SwingConstants.CENTER);
		add(chkNoThyroid, "cell 5 5, wmax 200, gapx 150");

		//---- lblCopd ----
		add(lblCopd, "cell 0 6");

		//---- chkYesCopd ----
		chkYesCopd.setHorizontalAlignment(SwingConstants.CENTER);
		add(chkYesCopd, "cell 4 6, wmax 200, gapx 150");

		//---- chkNoCopd ----
		chkNoCopd.setHorizontalAlignment(SwingConstants.CENTER);
		add(chkNoCopd, "cell 5 6, wmax 200, gapx 150");

		//---- lblPud ----
		add(lblPud, "cell 0 7");

		//---- chkYesPud ----
		chkYesPud.setHorizontalAlignment(SwingConstants.CENTER);
		add(chkYesPud, "cell 4 7, wmax 200, gapx 150");

		//---- chkNoPud ----
		chkNoPud.setHorizontalAlignment(SwingConstants.CENTER);
		add(chkNoPud, "cell 5 7, wmax 200, gapx 150");

		//======== panelContainer2 ========
		{
			panelContainer2.setLayout(new MigLayout(
				"hidemode 3",
				// columns
				"[fill]" +
				"[fill]" +
				"[fill]" +
				"[fill]" +
				"[fill]" +
				"[fill]" +
				"[fill]" +
				"[fill]" +
				"[fill]" +
				"[fill]",
				// rows
				"[]" +
				"[]" +
				"[]" +
				"[]" +
				"[]"));

			//---- lblHeight ----
			panelContainer2.add(lblHeight, "cell 0 0");
			panelContainer2.add(txtHeight, "cell 1 0, width 100:100:100");

			//---- lblWeight ----
			panelContainer2.add(lblWeight, "cell 2 0");
			panelContainer2.add(txtWeight, "cell 3 0, width 100:100:100");

			//---- lblPulseRate ----
			panelContainer2.add(lblPulseRate, "cell 4 0");
			panelContainer2.add(txtPulseRate, "cell 5 0, width 100:100:100");

			//---- lblBp ----
			panelContainer2.add(lblBp, "cell 6 0");
			panelContainer2.add(txtBp, "cell 7 0, width 100:100:100");

			//---- lblBloodType ----
			panelContainer2.add(lblBloodType, "cell 8 0");
			panelContainer2.add(txtBloodType, "cell 9 0, width 100:100:100");

			//---- lblRemarks ----
			panelContainer2.add(lblRemarks, "cell 0 1");

			//---- lblFamHistory ----
			panelContainer2.add(lblFamHistory, "cell 5 1 2 1");

			//======== scrollPane1 ========
			{
				scrollPane1.setViewportView(taRemarks);
			}
			panelContainer2.add(scrollPane1, "cell 0 2 5 1, height 100:100:100");
			panelContainer2.add(new JScrollPane(taFamilyHistory), "cell 5 2 5 1, height 100:100:100");
		}
		add(panelContainer2, "cell 0 9 7 1");

		//---- lblFindings ----
		add(lblFindings, "cell 0 10");

		//---- lblNormal ----
		add(lblNormal, "cell 4 10, width 200:200:200");

		//---- lblPositive ----
		add(lblPositive, "cell 5 10, width 200:200:200");

		//---- lblRemarks02 ----
		add(lblRemarks02, "cell 6 10, width 200:200:200");

		//---- lblDev ----
		add(lblDev, "cell 0 11");
		add(txtDevNormal, "cell 4 11, width 200:200:200");
		add(txtPositiveDev, "cell 5 11, width 200:200:200");
		add(txtRemarksDev, "cell 6 11, width 200:200:200");

		//---- lblGen ----
		add(lblGen, "cell 0 12");
		add(txtGeneralAppearanceNormal, "cell 4 12,width 200:200:200");
		add(txtPositiveGeneralAppearance, "cell 5 12,width 200:200:200");
		add(txtRemarksGeneralAppearance, "cell 6 12,width 200:200:200");

		//---- lblEar ----
		add(lblEar, "cell 0 13");
		add(txtEarNormal, "cell 4 13,width 200:200:200");
		add(txtPositiveEar, "cell 5 13,width 200:200:200");
		add(txtRemarksEar, "cell 6 13,width 200:200:200");

		//---- lblHead ----
		add(lblHead, "cell 0 14");
		add(txtHeadNormal, "cell 4 14,width 200:200:200");
		add(txtPositiveHead, "cell 5 14,width 200:200:200");
		add(txtRemarksHead, "cell 6 14,width 200:200:200");

		//---- lblChest ----
		add(lblChest, "cell 0 15");
		add(txtNormalChest, "cell 4 15,width 200:200:200");
		add(txtPositiveChest, "cell 5 15,width 200:200:200");
		add(txtRemarksChest, "cell 6 15,width 200:200:200");

		//---- lblHeart ----
		add(lblHeart, "cell 0 16");
		add(txtNormalHeart, "cell 4 16,width 200:200:200");
		add(txtPositiveHeart, "cell 5 16,width 200:200:200");
		add(txtRemarksHeart, "cell 6 16,width 200:200:200");

		//---- lblAbdomen ----
		add(lblAbdomen, "cell 0 17");
		add(txtNormalAbdomen, "cell 4 17,width 200:200:200");
		add(txtPositiveAbdomen, "cell 5 17,width 200:200:200");
		add(txtRemarksAbdomen, "cell 6 17,width 200:200:200");

		//---- lblGenitals ----
		add(lblGenitals, "cell 0 18");
		add(txtNormalGenitals, "cell 4 18,width 200:200:200");
		add(txtPositiveGenitals, "cell 5 18,width 200:200:200");
		add(txtRemarksGenitals, "cell 6 18,width 200:200:200");

		//---- lblExtremities ----
		add(lblExtremities, "cell 0 19");
		add(txtNormalExtremities, "cell 4 19,width 200:200:200");
		add(txtPositiveExtremities, "cell 5 19,width 200:200:200");
		add(txtRemarksExtremities, "cell 6 19,width 200:200:200");

		//---- lblNeuralgic ----
		add(lblNeuralgic, "cell 0 20");
		add(txtNormalNeuralgic, "cell 4 20,width 200:200:200");
		add(txtPositiveNeuralgic, "cell 5 20,width 200:200:200");
		add(txtRemarksNeuralgic, "cell 6 20,width 200:200:200");

		//---- lblDiagnosis ----
		add(lblDiagnosis, "cell 0 22");

		//---- lblRecommendation ----
		add(lblRecommendation, "cell 5 22");

		//======== scrollPane2 ========
		{
			scrollPane2.setViewportView(taDiagnosis);
		}
		add(scrollPane2, "cell 0 23 5 1,height 100:100:100");

		//======== scrollPane3 ========
		{
			scrollPane3.setViewportView(taRecommendation);
		}
		add(scrollPane3, "cell 5 23 2 1,height 100:100:100");

		//---- lblPhysician ----
		add(lblPhysician, "cell 0 24 5 1,wmax 100");
		add(txtPhysician, "cell 0 24 5 1");

		//---- lblDate ----
		add(lblDate, "cell 5 24 2 1, wmax 100");
		add(dateChooser, "cell 5 24 2 1");

		//---- lblLicenseNo ----
		add(lblLicenseNo, "cell 0 25 5 1,wmax 100");
		add(txtLicense, "cell 0 25 5 1");

		//---- lblTime ----
		add(lblTime, "cell 5 25 2 1, wmax 100");
		add(txtTime, "cell 5 25 2 1");

		//---- lblMedicalExaminer ----
		add(lblMedicalExaminer, "cell 0 26");
		add(txtMedicalExaminer, "cell 1 26 4 1");

	}

	public void clear() {

//		chkYesHypertension.setSelected(false);
//		chkNoHypertension.setSelected(false);
//		chkYesDiabetes.setSelected(false);
//		chkNoDiabetes.setSelected(false);
//		chkYesBronchial.setSelected(false);
//		chkNoBronchial.setSelected(false);
//		chkYesHeartDisease.setSelected(false);
//		chkNoHeartDisease.setSelected(false);
//		chkYesThyroid.setSelected(false);
//		chkNoThyroid.setSelected(false);
//		chkYesCopd.setSelected(false);
//		chkNoCopd.setSelected(false);
//		chkYesPud.setSelected(false);
//		chkNoPud.setSelected(false);

		hypertensionGroup.clearSelection();
		diabetesGroup.clearSelection();
		diabetesGroup.clearSelection();
		bronchialGroup.clearSelection();
		bronchialGroup.clearSelection();
		heartDiseaseGroup.clearSelection();
		heartDiseaseGroup.clearSelection();
		thyroidGroup.clearSelection();
		thyroidGroup.clearSelection();
		copdGroup.clearSelection();
		copdGroup.clearSelection();
		pudGroup.clearSelection();
		pudGroup.clearSelection();

		txtHeight.setText("");
		txtWeight.setText("");
		txtPulseRate.setText("");
		txtBp.setText("");
		txtBloodType.setText("");

		taRemarks.setText("");
		taFamilyHistory.setText("");
		txtDevNormal.setText("");
		txtPositiveDev.setText("");
		txtRemarksDev.setText("");
		txtGeneralAppearanceNormal.setText("");
		txtPositiveGeneralAppearance.setText("");
		txtRemarksGeneralAppearance.setText("");
		txtEarNormal.setText("");
		txtPositiveEar.setText("");
		txtRemarksEar.setText("");
		txtHeadNormal.setText("");
		txtPositiveHead.setText("");
		txtRemarksHead.setText("");
		txtNormalChest.setText("");
		txtPositiveChest.setText("");
		txtRemarksChest.setText("");
		txtNormalHeart.setText("");
		txtPositiveHeart.setText("");
		txtRemarksHeart.setText("");
		txtNormalAbdomen.setText("");
		txtPositiveAbdomen.setText("");
		txtRemarksAbdomen.setText("");
		txtNormalGenitals.setText("");
		txtPositiveGenitals.setText("");
		txtRemarksGenitals.setText("");
		txtNormalExtremities.setText("");
		txtPositiveExtremities.setText("");
		txtRemarksExtremities.setText("");
		txtNormalNeuralgic.setText("");
		txtPositiveNeuralgic.setText("");
		txtRemarksNeuralgic.setText("");
		taDiagnosis.setText("");
		taRecommendation.setText("");
		txtPhysician.setText("");
		dateChooser.setDate(new Date());
		txtLicense.setText("");
		txtTime.setText("");
		txtMedicalExaminer.setText("");
	}

//	public boolean getChkYesHypertension() {
//		return chkYesHypertension.isSelected();
//	}
//
//	public boolean getChkNoHypertension() {
//		return chkNoHypertension.isSelected();
//	}
//
//	public boolean getChkYesDiabetes() {
//		return chkYesDiabetes.isSelected();
//	}
//
//	public boolean getChkNoDiabetes() {
//		return chkNoDiabetes.isSelected();
//	}
//
//	public boolean getChkYesBronchial() {
//		return chkYesBronchial.isSelected();
//	}
//
//	public boolean getChkNoBronchial() {
//		return chkNoBronchial.isSelected();
//	}
//
//	public boolean getChkYesHeartDisease() {
//		return chkYesHeartDisease.isSelected();
//	}
//
//	public boolean getChkNoHeartDisease() {
//		return chkNoHeartDisease.isSelected();
//	}
//
//	public boolean getChkYesThyroid() {
//		return chkYesThyroid.isSelected();
//	}
//
//	public boolean getChkNoThyroid() {
//		return chkNoThyroid.isSelected();
//	}
//
//	public boolean getChkYesCopd() {
//		return chkYesCopd.isSelected();
//	}
//
//	public boolean getChkNoCopd() {
//		return chkNoCopd.isSelected();
//	}
//
//	public boolean getChkYesPud() {
//		return chkYesPud.isSelected();
//	}
//
//	public boolean getChkNoPud() {
//		return chkNoPud.isSelected();
//	}

	public String getTxtHeight() {
		return txtHeight.getText();
	}

	public String getTxtWeight() {
		return txtWeight.getText();
	}

	public String getTxtPulseRate() {
		return txtPulseRate.getText();
	}

	public String getTxtBp() {
		return txtBp.getText();
	}

	public String getTxtBloodType() {
		return txtBloodType.getText();
	}

	public String getTaRemarks() {
		return taRemarks.getText();
	}

	public String getTaFamilyHistory() {
		return taFamilyHistory.getText();
	}

	public String getTxtDevNormal() {
		return txtDevNormal.getText();
	}

	public String getTxtPositiveDev() {
		return txtPositiveDev.getText();
	}

	public String getTxtRemarksDev() {
		return txtRemarksDev.getText();
	}

	public String getTxtGeneralAppearanceNormal() {
		return txtGeneralAppearanceNormal.getText();
	}

	public String getTxtPositiveGeneralAppearance() {
		return txtPositiveGeneralAppearance.getText();
	}

	public String getTxtRemarksGeneralAppearance() {
		return txtRemarksGeneralAppearance.getText();
	}

	public String getTxtEarNormal() {
		return txtEarNormal.getText();
	}

	public String getTxtPositiveEar() {
		return txtPositiveEar.getText();
	}

	public String getTxtRemarksEar() {
		return txtRemarksEar.getText();
	}

	public String getTxtHeadNormal() {
		return txtHeadNormal.getText();
	}

	public String getTxtPositiveHead() {
		return txtPositiveHead.getText();
	}

	public String getTxtRemarksHead() {
		return txtRemarksHead.getText();
	}

	public String getTxtNormalChest() {
		return txtNormalChest.getText();
	}

	public String getTxtPositiveChest() {
		return txtPositiveChest.getText();
	}

	public String getTxtRemarksChest() {
		return txtRemarksChest.getText();
	}

	public String getTxtNormalHeart() {
		return txtNormalHeart.getText();
	}

	public String getTxtPositiveHeart() {
		return txtPositiveHeart.getText();
	}

	public String getTxtRemarksHeart() {
		return txtRemarksHeart.getText();
	}

	public String getTxtNormalAbdomen() {
		return txtNormalAbdomen.getText();
	}

	public String getTxtPositiveAbdomen() {
		return txtPositiveAbdomen.getText();
	}

	public String getTxtRemarksAbdomen() {
		return txtRemarksAbdomen.getText();
	}

	public String getTxtNormalGenitals() {
		return txtNormalGenitals.getText();
	}

	public String getTxtPositiveGenitals() {
		return txtPositiveGenitals.getText();
	}

	public String getTxtRemarksGenitals() {
		return txtRemarksGenitals.getText();
	}

	public String getTxtNormalExtremities() {
		return txtNormalExtremities.getText();
	}

	public String getTxtPositiveExtremities() {
		return txtPositiveExtremities.getText();
	}

	public String getTxtRemarksExtremities() {
		return txtRemarksExtremities.getText();
	}

	public String getTxtNormalNeuralgic() {
		return txtNormalNeuralgic.getText();
	}

	public String getTxtPositiveNeuralgic() {
		return txtPositiveNeuralgic.getText();
	}

	public String getTxtRemarksNeuralgic() {
		return txtRemarksNeuralgic.getText();
	}

	public String getTaDiagnosis() {
		return taDiagnosis.getText();
	}

	public String getTaRecommendation() {
		return taRecommendation.getText();
	}

	public String getTxtPhysician() {
		return txtPhysician.getText();
	}

	public String getTxtLicense() {
		return txtLicense.getText();
	}

	public String getDateByString() {
		return dateChooser.getDate().toString();
	}

	public Date getDate() {
		return dateChooser.getDate();
	}

	public String getTxtTime() {
		return txtTime.getText();
	}

	public String getTxtMedicalExaminer() {
		return txtMedicalExaminer.getText();
	}

	public boolean isHypertension() {
        return chkYesHypertension.isSelected();
    }

	public boolean isDiabetes() {
		return chkYesDiabetes.isSelected();
	}

	public boolean isBronchial() {
		return chkYesBronchial.isSelected();
	}

	public boolean isHeartDisease() {
		return chkYesHeartDisease.isSelected();
	}

	public boolean isThyroid() {
		return chkYesThyroid.isSelected();
	}

	public boolean isCopd() {
		return chkYesCopd.isSelected();
	}

	public boolean isPud() {
		return chkYesPud.isSelected();
	}
}
