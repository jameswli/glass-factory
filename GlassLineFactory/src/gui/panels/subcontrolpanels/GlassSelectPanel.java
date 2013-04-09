
package gui.panels.subcontrolpanels;

import gui.panels.ControlPanel;
import glassLine.*;
import glassLine.agents.GlassRobotAgent;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JCheckBox;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JButton;

/**
 * The GlassSelectPanel class contains buttons allowing the user to select what
 * type of glass to produce.
 */
@SuppressWarnings("serial")
public class GlassSelectPanel extends JPanel implements ActionListener
{
	public GlassRobotAgent gRobot;
	public int glassCount = 0;
	/** The ControlPanel this is linked to */
	private ControlPanel parent;

	/** list of checkboxes for creating glass recipe */
	List<JCheckBox> recipe;
	
	JButton createGlass;
	/**
	 * Creates a new GlassSelect and links it to the control panel
	 * @param cp
	 *        the ControlPanel linked to it
	 */
	public GlassSelectPanel(ControlPanel cp)
	{
		recipe = new ArrayList<JCheckBox>();
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JCheckBox chckbxCutter = new JCheckBox("Cutter");
		recipe.add(chckbxCutter);
		GridBagConstraints gbc_chckbxCutter = new GridBagConstraints();
		gbc_chckbxCutter.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxCutter.gridx = 1;
		gbc_chckbxCutter.gridy = 0;
		add(chckbxCutter, gbc_chckbxCutter);
		
		JCheckBox chckbxGrinder = new JCheckBox("Grinder");
		recipe.add(chckbxGrinder);
		GridBagConstraints gbc_chckbxGrinder = new GridBagConstraints();
		gbc_chckbxGrinder.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxGrinder.gridx = 3;
		gbc_chckbxGrinder.gridy = 0;
		add(chckbxGrinder, gbc_chckbxGrinder);
		
		JCheckBox chckbxBreakout = new JCheckBox("BreakOut");
		recipe.add(chckbxBreakout);
		GridBagConstraints gbc_chckbxBreakout = new GridBagConstraints();
		gbc_chckbxBreakout.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxBreakout.gridx = 1;
		gbc_chckbxBreakout.gridy = 1;
		add(chckbxBreakout, gbc_chckbxBreakout);
		
		JCheckBox chckbxWasher = new JCheckBox("Washer");
		recipe.add(chckbxWasher);
		GridBagConstraints gbc_chckbxWasher = new GridBagConstraints();
		gbc_chckbxWasher.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxWasher.gridx = 3;
		gbc_chckbxWasher.gridy = 1;
		add(chckbxWasher, gbc_chckbxWasher);
		
		JCheckBox chckbxManualBo = new JCheckBox("Manual BO");
		recipe.add(chckbxManualBo);
		GridBagConstraints gbc_chckbxManualBo = new GridBagConstraints();
		gbc_chckbxManualBo.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxManualBo.gridx = 1;
		gbc_chckbxManualBo.gridy = 2;
		add(chckbxManualBo, gbc_chckbxManualBo);
		
		JCheckBox chckbxUv = new JCheckBox("UV");
		recipe.add(chckbxUv);
		GridBagConstraints gbc_chckbxUv = new GridBagConstraints();
		gbc_chckbxUv.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxUv.gridx = 3;
		gbc_chckbxUv.gridy = 2;
		add(chckbxUv, gbc_chckbxUv);
		
		JCheckBox chckbxCrossseamer = new JCheckBox("CrossSeamer");
		recipe.add(chckbxCrossseamer);
		GridBagConstraints gbc_chckbxCrossseamer = new GridBagConstraints();
		gbc_chckbxCrossseamer.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxCrossseamer.gridx = 1;
		gbc_chckbxCrossseamer.gridy = 3;
		add(chckbxCrossseamer, gbc_chckbxCrossseamer);
		
		JCheckBox chckbxPaint = new JCheckBox("Paint");
		recipe.add(chckbxPaint);
		GridBagConstraints gbc_chckbxPaint = new GridBagConstraints();
		gbc_chckbxPaint.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxPaint.gridx = 3;
		gbc_chckbxPaint.gridy = 3;
		add(chckbxPaint, gbc_chckbxPaint);
		
		JCheckBox chckbxDrill = new JCheckBox("Drill");
		recipe.add(chckbxDrill);
		GridBagConstraints gbc_chckbxDrill = new GridBagConstraints();
		gbc_chckbxDrill.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxDrill.gridx = 1;
		gbc_chckbxDrill.gridy = 4;
		add(chckbxDrill, gbc_chckbxDrill);
		
		JCheckBox chckbxBake = new JCheckBox("Bake");
		recipe.add(chckbxBake);
		GridBagConstraints gbc_chckbxBake = new GridBagConstraints();
		gbc_chckbxBake.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxBake.gridx = 3;
		gbc_chckbxBake.gridy = 4;
		add(chckbxBake, gbc_chckbxBake);
		
		createGlass = new JButton("Create Glass");
		GridBagConstraints gbc_createGlass = new GridBagConstraints();
		gbc_createGlass.gridx = 3;
		gbc_createGlass.gridy = 5;
		add(createGlass, gbc_createGlass);
		createGlass.addActionListener(this);
		
		parent = cp;
		
		for(int i = 0; i < recipe.size(); i++){
			recipe.get(i).addActionListener(this);
		}
	}

	/**
	 * Returns the parent panel
	 * @return the parent panel
	 */
	public ControlPanel getGuiParent()
	{
		return parent;
	}
	
	public void setGlassRobot(GlassRobotAgent gr){
		gRobot = gr;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// check which checkboxes are selected when the createGlas button is pressed
		// the selected stations will be added to the glass' recipe 
		if(ae.getSource() == createGlass){
			glassCount++;
			List<String> station = new ArrayList<String>();
			for(JCheckBox cb:recipe){
				if(cb.isSelected()){
					station.add(cb.getText());
				}
			}
			Glass temp = new Glass(glassCount, station);
			parent.makeGlass(temp);
			gRobot.addGlass(temp);
		}
	}
}