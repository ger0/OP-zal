package Core;

import javax.swing.*;

import Places.*;
import Vehicles.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Options implements Runnable {
    private EntityContainer container;
    private MapSystem map;
    private Thread t;
    private int selectedId = 0;
    private int currentXY[] = new int[]{0, 0};

    private JPanel panel;
    private JLabel selected;
    private JButton vehRemove;
    private JTextArea capacity;
    private JTextArea weaponName;
    private JTextArea load;
    private JTextArea company;
    private JPanel passengerPanel;
    private JPanel militaryPanel;
    private JButton plnAdd;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JTextArea coordinates;
    private JPanel ship;
    private JPanel Plane;
    private JButton shpAdd;
    private JLabel output;

    public Options() {
        vehRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                container.remove(selectedId);
            }
        });
        plnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                container.add(new PassengerPlane(100, 10, 6, currentXY, map), 6);
            }
        });
    }
    public void start() {
        System.out.println("Starting thread: Options");
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }
    public void run() {
        JFrame frame = new JFrame("Options");
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
    public void select(int id, int[] xy) {
        this.selectedId = id;
        this.currentXY = xy;
        Object select = container.get(id);

        if (select == null) {
            selected.setText("None");
        } else {
            if (select.getClass() == PassengerPlane.class) {
                selected.setText("Passenger Plane");
            } else if (select.getClass() == MilitaryPlane.class) {
                selected.setText("Military Plane");
            } else if (select.getClass() == CarrierShip.class) {
                selected.setText("Carrier Ship");
            } else if (select.getClass() == CruiseShip.class) {
                selected.setText("Cruise Ship");
            } else if (select.getClass() == Harbour.class) {
                selected.setText("Harbour");
            } else if (select.getClass() == CivilAirport.class) {
                selected.setText("Civil Airport");
            } else if (select.getClass() == MilitaryAirport.class) {
                selected.setText("Military Airport");
            }
            if (Place.class.isAssignableFrom(select.getClass())) {
                coordinates.setText("x: " + Integer.toString(((Place)select).getPos()[0])
                                 + " y: " + Integer.toString(((Place)select).getPos()[1]));
            } else {
                coordinates.setText("x: " + Integer.toString(((Vehicle)select).getPos()[0])
                        + " y: " + Integer.toString(((Vehicle)select).getPos()[1]));
            }
        }
    }
        public void attach(EntityContainer container, MapSystem map) {
            this.container  = container;
            this.map        = map;
        }
    }