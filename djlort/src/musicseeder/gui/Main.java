package musicseeder.gui;

import java.util.List;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.border.*;
import java.awt.*;

import musicseeder.*;
import musicseeder.analysis.*;
import musicseeder.generate.*;
import musicseeder.node.*;
import musicseeder.phases.*;

import javax.sound.midi.*;

import djlort.*;

public class Main{
	public DefaultListModel basis;
	public DefaultListModel filters;
	public DefaultListModel orchester;

	public DefaultListModel layers; // valgte filtre

	public static ASong song;
	public Sequencer sequencer;
	public Synthesizer synth;

	public JTextField randomSeed;
	public JList filterJList;
	public List<Pair> orchesterList;
	public List<Pair> basisList;
	
	public JCheckBox localCheckbox = new JCheckBox("Play locally");
	public JCheckBox remoteCheckbox = new JCheckBox("Play remotely");
	public JTextField hostField = new JTextField("10.11.20.170");
	public JTextField portField = new JTextField("3000");
	public JComboBox comboBox = new JComboBox();
	
	private class Pair{
		public AbstractButton button;
		public GuiPhases phase;
		public Pair(AbstractButton b, GuiPhases p){
			button = b;
			phase = p;
		}
	}

	public void playSeq(Sequence seq) throws Exception{
		Sequencer s = sequencer;
		sequencer.close();
		sequencer.open();
		sequencer.setTempoInBPM(120);
		synth.close();
		//synth.open();

		Receiver silent = new SilentReceiver();
		sequencer.getTransmitters().get(0).setReceiver(silent);
		
		// Add relevant transmitters
		if ( localCheckbox.isSelected()){
			// Hent device fra combobox
			MidiDevice outputPort = MidiSystem.getMidiDevice ((MidiDevice.Info)comboBox.getSelectedItem());
			outputPort.open();
			sequencer.getTransmitters().get(0).setReceiver(outputPort.getReceiver());
		}
		
		if ( remoteCheckbox.isSelected()){
			// TODO: Make this user definable
			String host=hostField.getText();
			int port=Integer.parseInt(portField.getText());
			sequencer.getTransmitter().setReceiver(new Sender(new UdpProtocol(host,port)));
		}
		
		//Transmitter t = sequencer.getTransmitters().get(0);
		//t = null;
		
		sequencer.setSequence(seq);
		sequencer.start();	

		System.out.println(seq.getTickLength());
		//	Thread.sleep(seq.getTickLength()*30);
		//	System.exit(0);
	}

	public void init(){
		orchester=new DefaultListModel();
		basis=new DefaultListModel();
		filters=new DefaultListModel();
		layers=new DefaultListModel();
		try {
			sequencer = MidiSystem.getSequencer();
			synth = MidiSystem.getSynthesizer();
		} catch (Exception e) { e.printStackTrace(); }
	}
	public void initSongSequence(ASong node) {
		try{
			node.sequence=new Sequence(Sequence.PPQ, 80);
		} catch(Exception e){
			e.printStackTrace();
			System.exit(-1);
		}
	}
	private abstract class ListMouseAdapter extends MouseAdapter{
		protected JList l;
		public void setL(JList l){
			this.l=l;
		}
	}
	private class FiltersAdapter extends ListMouseAdapter{
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) { //Dbl-click
				int index = l.locationToIndex(e.getPoint());
				layers.addElement(((DefaultListModel)l.getModel()).elementAt(index));
			}
		}
	}
	private class LayersAdapter extends ListMouseAdapter{
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) { //Dbl-click
				int index = l.locationToIndex(e.getPoint());
				((DefaultListModel)l.getModel()).remove(index);
			}
		}
	}
	private JPanel makeBasisPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(0,1));
		ButtonGroup group = new ButtonGroup();
		basisList = new LinkedList<Pair>();
		Object[] list = basis.toArray();
		for(Object i : list){
			GuiPhases g = (GuiPhases) i;
			JRadioButton r = new JRadioButton(g.title());
			if(g == list[0]){
				r.setSelected(true);
			}
			p.add(r);
			group.add(r);
			basisList.add(new Pair(r,g));
		}
		p.setBorder(new TitledBorder("Basis:"));
		return p;
	}
	private JPanel makeFilterPanel() {
		filterJList = new JList(filters);
		FiltersAdapter filterAd = new FiltersAdapter();
		filterAd.setL(filterJList);
		filterJList.addMouseListener(filterAd);
		JList layerList = new JList(layers);
		LayersAdapter layerAd = new LayersAdapter();
		layerAd.setL(layerList);
		layerList.addMouseListener(layerAd);
		JScrollPane s1 = new JScrollPane(filterJList);
		JScrollPane s2 = new JScrollPane(layerList);

		JButton addButton=new JButton("Add filter >>");
		addButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int index = filterJList.getSelectedIndex();
				if(index!=-1){
					layers.addElement(filters.elementAt(index));
				}
			}
		});
		JButton clearButton=new JButton("Clear");
		clearButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				layers.clear();
			}
		});

		JPanel p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		p1.add(s1, BorderLayout.CENTER);
		p1.add(addButton, BorderLayout.PAGE_END);

		JPanel p2 = new JPanel();
		p2.setLayout(new BorderLayout());
		p2.add(s2, BorderLayout.CENTER);
		p2.add(clearButton, BorderLayout.PAGE_END);
		JPanel p = new JPanel();

		p.setLayout(new GridLayout(0,2));
		p.setBorder(new TitledBorder("Filters:"));
		p.add(p1);
		p.add(p2);
		return p;
	}
	private JPanel makeOrchesterPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(0,1));
		orchesterList = new LinkedList<Pair>();
		Object[] list = orchester.toArray();
		for(Object i : list){
			GuiPhases g = (GuiPhases) i;
			JCheckBox r = new JCheckBox(g.title(), true);
			p.add(r);
			orchesterList.add(new Pair(r,g));
		}
		p.setBorder(new TitledBorder("Orchestration:"));
		return p;
	}
	private JPanel makeWhereToPlayPanel(){
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(0,1));
		p.add(localCheckbox);
		
		Container c1 = new Container();
		c1.setLayout(new BorderLayout());
		c1.add(new JLabel("Device: "), BorderLayout.WEST);

	    MidiDevice.Info[] info = MidiSystem.getMidiDeviceInfo();
	    for (int i = 0 ; i<info.length; i++){
	    	comboBox.addItem(info[i]);
	    }
		
		c1.add(comboBox, BorderLayout.CENTER);
		
		p.add(c1);
		
		p.add(remoteCheckbox);
		
		Container c2 = new Container();
		c2.setLayout(new BorderLayout());
		c2.add(new JLabel("Host: "), BorderLayout.WEST);
		c2.add(hostField, BorderLayout.CENTER);
		
		
		Container c3 = new Container();
		c3.setLayout(new BorderLayout());
		c3.add(new JLabel("Port:"), BorderLayout.WEST);
		c3.add(portField, BorderLayout.CENTER);
		
		Container c = new Container();
		c.setLayout(new GridLayout(0,2));
		c.add(c2);
		c.add(c3);
		p.add(c);
		
		
		p.setBorder(new TitledBorder("Where To Play:"));
		return p;
	}
	
	
	private void runLayers(){
		long seed;
		try{
			seed = Long.parseLong(randomSeed.getText());
		} catch ( NumberFormatException e ) {
			seed = Util.random.nextLong();
			randomSeed.setText(""+seed);
			System.out.println("Using seed : " + seed);
		}
		Util.random = new Random(seed);
		song = new SimpleSong().generate();
		initSongSequence(song);
		for(Pair i : basisList){
			if(i.button.isSelected()){
				song.apply(i.phase);
			}
		}
		Object[] layerArray = layers.toArray();
		for(Object i : layerArray){
			song.apply((DepthFirstAdapter)i);
		}
		for(Pair i : orchesterList){
			if(i.button.isSelected()){
				song.apply(i.phase);
			}
		}
	}
	private JPanel makeSeedPanel() {
		final JTextField t = new JTextField();
		JButton b = new JButton("Randomize");
		b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				t.setText(""+musicseeder.Util.random.nextLong());
			}		
		});
		t.setColumns(20);
		randomSeed = t;
		JPanel p = new JPanel();
		p.setBorder(new TitledBorder("Seed:"));
		p.setLayout(new BorderLayout());
		p.add(b, BorderLayout.WEST);
		p.add(t, BorderLayout.CENTER);
		return p;
	}
	private JPanel makeControlPanel() {
		JButton goButton = new JButton("Start");
		goButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				runLayers();
				try{
					playSeq(song.sequence);
				} catch(Exception e){
					e.printStackTrace();
				}
			}
		});

		JButton stopButton = new JButton("Stop");
		stopButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				try{
					sequencer.stop();
				} catch ( Exception e) {
					e.printStackTrace();
				}
			}		
		});

		JPanel p = new JPanel();
		p.setLayout(new GridLayout(0,2));
		p.add(goButton);
		p.add(stopButton);

		return p;
	}
	public void run(Container frame){
		init();
		try{
			// Set gui look-and-feel to system default
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) { e.printStackTrace();}
		/* window listener so the program will die */

		JPanel basisPanel = makeBasisPanel();

		JPanel filterPanel = makeFilterPanel();
		JPanel orchesterPanel = makeOrchesterPanel();
		JPanel wherePanel = makeWhereToPlayPanel();
		JPanel seedPanel = makeSeedPanel();
		JPanel controlPanel = makeControlPanel();

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(basisPanel);
		mainPanel.add(filterPanel);
		Container c = new Container();
		c.setLayout(new GridLayout(0,2));
		c.add(orchesterPanel);
		c.add(wherePanel);
		
		mainPanel.add(c);
		mainPanel.add(seedPanel);
		mainPanel.add(controlPanel);
		frame.add(mainPanel);
	}
	public static void main(String[] args){
		Main m=new Main();
		JFrame frame = new JFrame("MusicSeeder");

		frame.addWindowListener(new WindowAdapter () {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		m.run(frame.getContentPane());
		frame.setSize(650, 600);
		frame.setVisible(true);
	}
}
