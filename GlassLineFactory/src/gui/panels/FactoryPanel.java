
package gui.panels;

import java.util.TreeMap;

import glassLine.*;
import glassLine.agents.*;
import glassLine.interfaces.*;
import gui.drivers.FactoryFrame;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import transducer.TChannel;
import transducer.Transducer;

/**
 * The FactoryPanel is highest level panel in the actual kitting cell. The
 * FactoryPanel makes all the back end components, connects them to the
 * GuiComponents in the DisplayPanel. It is responsible for handing
 * communication between the back and front end.
 */
@SuppressWarnings("serial")
public class FactoryPanel extends JPanel
{
	/** The frame connected to the FactoryPanel */
	private FactoryFrame parent;

	/** The control system for the factory, displayed on right */
	public ControlPanel cPanel;

	/** The graphical representation for the factory, displayed on left */
	private DisplayPanel dPanel;

	/** Allows the control panel to communicate with the back end and give commands */
	private Transducer transducer;
	
	/** agent counter */
	int agentNumber = 0;
	
	/*
	 * Factory Agents
	 */
	public GlassRobotAgent gRobot;
	public OnlineWorkStationAgent cutter;
	public OnlineWorkStationAgent breakout;
	public OnlineWorkStationAgent mbreakout;
	public PopupAgent drill;
	public PopupAgent crossSeamer;
	public PopupAgent grinder;
	public PopupRobotAgent csRobot1;
	public PopupRobotAgent csRobot2;
	public PopupRobotAgent dRobot1;
	public PopupRobotAgent dRobot2;
	public PopupRobotAgent gRobot1;
	public PopupRobotAgent gRobot2;
	public OnlineWorkStationAgent washer;
	public OnlineWorkStationAgent uv;
	public OnlineWorkStationAgent painter;
	public OnlineWorkStationAgent baker;
	public TruckAgent truck;
	public ConveyorAgent conv0;
	public ConveyorAgent conv1;
	public ConveyorAgent conv2;
	public ConveyorAgent conv3;
	public ConveyorAgent conv4;
	public ConveyorAgent conv5;
	public ConveyorAgent conv6;
	public ConveyorAgent conv7;
	public ConveyorAgent conv8;
	public ConveyorAgent conv9;
	public ConveyorAgent conv10;
	public ConveyorAgent conv11;
	public ConveyorAgent conv12;
	public ConveyorAgent conv13;
	public ConveyorAgent conv14;
	
	public TreeMap<Integer, ConveyorAgent> conveyors;
	public TreeMap<Integer, PopupAgent> popups;
	public TreeMap<Integer, PopupRobotAgent> robots;
	public TreeMap<Integer, OnlineWorkStationAgent> machines;
	

	/**
	 * Constructor links this panel to its frame
	 */
	public FactoryPanel(FactoryFrame fFrame)
	{
		parent = fFrame;

		// initialize transducer
		transducer = new Transducer();
		transducer.startTransducer();
		
		conveyors = new TreeMap<Integer, ConveyorAgent>();
		popups = new TreeMap<Integer, PopupAgent>();
		robots = new TreeMap<Integer, PopupRobotAgent>();
		machines = new TreeMap<Integer, OnlineWorkStationAgent>();

		// use default layout
		// dPanel = new DisplayPanel(this);
		// dPanel.setDefaultLayout();
		// dPanel.setTimerListeners();

		// initialize and run
		this.initialize();
		this.initializeBackEnd();
		
		// initialize the agents and other components
		this.startAgents();
		
		// pass glass robot to selection panel
		cPanel.glassSelectPanel.setGlassRobot(this.gRobot);
	}

	/**
	 * Initializes all elements of the front end, including the panels, and lays
	 * them out
	 */
	private void initialize()
	{
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		// initialize control panel
		cPanel = new ControlPanel(this, transducer);

		// initialize display panel
		dPanel = new DisplayPanel(this, transducer);

		// add panels in
		// JPanel tempPanel = new JPanel();
		// tempPanel.setPreferredSize(new Dimension(830, 880));
		// this.add(tempPanel);

		this.add(dPanel);
		this.add(cPanel);
		
	}
	
	private void startAgents(){

	}

	/**
	 * Feel free to use this method to start all the Agent threads at the same time
	 */
	private void initializeBackEnd()
	{
		gRobot = new GlassRobotAgent(transducer, cPanel.getTracePanel(),  "GlassBin");
		cutter = new OnlineWorkStationAgent("CUTTER", 0, 1, transducer, cPanel.getTracePanel());
		breakout = new OnlineWorkStationAgent("BREAKOUT", 111, 1, transducer, cPanel.getTracePanel());
		mbreakout= new OnlineWorkStationAgent("MANUAL_BREAKOUT", 222, 1, transducer, cPanel.getTracePanel());
		drill = new PopupAgent(transducer, 0, 0, 1, cPanel.getTracePanel());
		popups.put(0, drill);
		dRobot1 = new PopupRobotAgent("DRILL",0,true,drill,transducer,cPanel.getTracePanel());
		dRobot2 = new PopupRobotAgent("DRILL",1,false,drill,transducer,cPanel.getTracePanel());
		drill.setRobots(dRobot1, dRobot2);
		drill.setMachineChannel(TChannel.DRILL);
		crossSeamer = new PopupAgent(transducer, 1, 0, 1, cPanel.getTracePanel());
		popups.put(1,  crossSeamer);
		csRobot1 = new PopupRobotAgent("CROSS_SEAMER",0,true,crossSeamer,transducer,cPanel.getTracePanel());
		csRobot2 = new PopupRobotAgent("CROSS_SEAMER",1,false,crossSeamer,transducer,cPanel.getTracePanel());
		crossSeamer.setRobots(csRobot1, csRobot2);
		crossSeamer.setMachineChannel(TChannel.CROSS_SEAMER);
		grinder = new PopupAgent(transducer, 2, 0, 1, cPanel.getTracePanel());
		popups.put(2, grinder);
		gRobot1 = new PopupRobotAgent("GRINDER",0,true,grinder,transducer,cPanel.getTracePanel());
		gRobot2 = new PopupRobotAgent("GRINDER",1,false,grinder,transducer,cPanel.getTracePanel());
		grinder.setRobots(gRobot1, gRobot2);
		grinder.setMachineChannel(TChannel.GRINDER);
		
		washer = new OnlineWorkStationAgent("WASHER", 1, 1, transducer, cPanel.getTracePanel());
		painter = new OnlineWorkStationAgent("PAINTER", 2, 1, transducer, cPanel.getTracePanel());
		uv = new OnlineWorkStationAgent("UV_LAMP", 3, 1, transducer, cPanel.getTracePanel());
		baker = new OnlineWorkStationAgent("OVEN", 4, 1, transducer, cPanel.getTracePanel());
		conv0 = new ConveyorAgent("CUTTER", transducer,0, cPanel.getTracePanel());
		conveyors.put(0, conv0);
		conv1 = new ConveyorAgent("SHUTTLE1", transducer,1,cPanel.getTracePanel());
		conveyors.put(1, conv1);
		conv2 = new ConveyorAgent("BREAKOUT", transducer,2,cPanel.getTracePanel());
		conveyors.put(2, conv2);
		conv3 = new ConveyorAgent("MANUAL_BREAKOUT", transducer,3,cPanel.getTracePanel());
		conveyors.put(3, conv3);
		conv4 = new ConveyorAgent("SHUTTLE2", transducer,4,cPanel.getTracePanel());
		conveyors.put(4, conv4);
		conv5 = new ConveyorAgent("DRILL", transducer,5,cPanel.getTracePanel());
		conveyors.put(5, conv5);
		conv6 = new ConveyorAgent("CROSS_SEAMER", transducer,6,cPanel.getTracePanel());
		conveyors.put(6, conv6);
		conv7 = new ConveyorAgent("GRINDER", transducer,7,cPanel.getTracePanel());
		conveyors.put(7, conv7);
		conv8 = new ConveyorAgent("WASHER", transducer,8,cPanel.getTracePanel());
		conveyors.put(8, conv8);
		conv9 = new ConveyorAgent("SHUTTLE3", transducer,9,cPanel.getTracePanel());
		conveyors.put(9, conv9);
		conv10 = new ConveyorAgent("UV_LAMP", transducer,10,cPanel.getTracePanel());
		conveyors.put(10, conv10);
		conv11 = new ConveyorAgent("PAINTER", transducer,11,cPanel.getTracePanel());
		conveyors.put(11, conv11);
		conv12 = new ConveyorAgent("SHUTLE4", transducer,12,cPanel.getTracePanel());
		conveyors.put(12, conv12);
		conv13 = new ConveyorAgent("OVEN", transducer,13,cPanel.getTracePanel());
		conveyors.put(13, conv13);
		conv14 = new ConveyorAgent("EXIT", transducer,14,cPanel.getTracePanel());
		conveyors.put(14, conv14);
		truck = new TruckAgent("TRUCK", transducer, conv14,cPanel.getTracePanel());
		
		gRobot.setConveyor(conv0);
		conv0.setTwoMachines(gRobot, cutter);
		conv1.setTwoMachines(cutter, conv2);
		conv2.setTwoMachines(conv1, breakout);
		conv3.setTwoMachines(breakout, mbreakout);
		conv4.setTwoMachines(mbreakout, conv5);
		conv5.setTwoMachines(conv4, drill);
		conv6.setTwoMachines(drill, crossSeamer);
		conv7.setTwoMachines(crossSeamer, grinder);
		conv8.setTwoMachines(grinder,washer);
		conv9.setTwoMachines(washer, conv10);
		conv10.setTwoMachines(conv9, painter);
		conv11.setTwoMachines(painter, uv);
		conv12.setTwoMachines(uv, conv13);
		conv13.setTwoMachines(conv12, baker);
		conv14.setTwoMachines(baker,truck);
		
		cutter.setConveyors(conv0, conv1);
		breakout.setConveyors(conv2, conv3);
		mbreakout.setConveyors(conv3, conv4);
		drill.setConveyors(conv5, conv6);
		crossSeamer.setConveyors(conv6, conv7);
		grinder.setConveyors(conv7, conv8);
		washer.setConveyors(conv8, conv9);
		painter.setConveyors(conv10, conv11);
		uv.setConveyors(conv11, conv12);
		baker.setConveyors(conv13, conv14);
		
		gRobot.startThread();
		cutter.startThread();
		machines.put(0, cutter);
		breakout.startThread();
		machines.put(1, breakout);
		mbreakout.startThread();
		machines.put(2, mbreakout);
		drill.startThread();
		dRobot1.startThread();
		robots.put(0, dRobot1);
		dRobot2.startThread();
		robots.put(1, dRobot2);
		crossSeamer.startThread();
		csRobot1.startThread();
		robots.put(2, csRobot1);
		csRobot2.startThread();
		robots.put(3, csRobot2);
		grinder.startThread();
		gRobot1.startThread();
		robots.put(4, gRobot1);
		gRobot2.startThread();
		robots.put(5, gRobot2);
		washer.startThread();
		machines.put(3, washer);
		painter.startThread();
		machines.put(4, painter);
		uv.startThread();
		machines.put(5, uv);
		baker.startThread();
		machines.put(6, baker);
		truck.startThread();
		conv0.startThread();
		conv1.startThread();
		conv2.startThread();
		conv3.startThread();
		conv4.startThread();
		conv5.startThread();
		conv6.startThread();
		conv7.startThread();
		conv8.startThread();
		conv9.startThread();
		conv10.startThread();
		conv11.startThread();
		conv12.startThread();
		conv13.startThread();
		conv14.startThread();

		System.out.println("Back end initialization finished.");
	}

	/**
	 * Returns the parent frame of this panel
	 * 
	 * @return the parent frame
	 */
	public FactoryFrame getGuiParent()
	{
		return parent;
	}

	/**
	 * Returns the control panel
	 * 
	 * @return the control panel
	 */
	public ControlPanel getControlPanel()
	{
		return cPanel;
	}

	/**
	 * Returns the display panel
	 * 
	 * @return the display panel
	 */
	public DisplayPanel getDisplayPanel()
	{
		return dPanel;
	}
	
	public void makeGlass(Glass g){
		parent.glasses.add(g);
	}
}
