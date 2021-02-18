package Core;

import Places.*;
import Vehicles.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class Options implements Runnable {
    private EntityContainer container;
    private MapSystem map;
    private Thread t;

    // current selection on the map
    private int selectedId = 0;
    private int[] currentXY = new int[]{0, 0};

    // the most recent entity id
    private int recId;

    private JPanel panel;
    private JLabel selectLabel;

    // info text
    private JTextArea infoFuel, infoWork, infoCompany, infoSpeed,
                        infoCap, infoLoad, infoWeapon;
    private JTextArea infoX, infoY;

    private JButton vehRemove, passengerAdd, cruiseAdd,
                    carrierAdd, militaryAdd, changeDestination;

    // destination
    private JComboBox comboBox;
    private JLabel labDest;

    public Options() {
        clearInfo();
        vehRemove.addActionListener(actionEvent -> {
            container.remove(selectedId);
            clearInfo();
        });
        passengerAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                PassengerPlane p = new PassengerPlane(recId, currentXY);
                p.setMap(map);
                p.setCapacity(Integer.parseUnsignedInt(infoCap.getText()));
                p.setWorkers(Integer.parseUnsignedInt(infoWork.getText()));
                container.add(p, recId);
                recId++;
            }
        });
        carrierAdd.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent actionEvent) {
                 CarrierShip p = new CarrierShip(recId, currentXY);
                 p.setMap(map);
                 p.setWeapon(infoWeapon.getText());
                 p.setMaxSpeed(Integer.parseUnsignedInt(infoSpeed.getText()));
                 container.add(p, recId);
                 recId++;
             }
        });
        cruiseAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CruiseShip p = new CruiseShip(recId, currentXY);
                p.setMap(map);
                p.setCompany(infoCompany.getText());
                p.setCapacity(Integer.parseUnsignedInt(infoCap.getText()));
                p.setMaxSpeed(Integer.parseUnsignedInt(infoSpeed.getText()));
                container.add(p, recId);
            recId++;
            }
        });
        changeDestination.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Place   out = (Place)container.get((Integer)comboBox.getSelectedItem());
                Vehicle in = (Vehicle)container.get(selectedId);
                if (out != null && in != null) {
                    in.setTarget(out.getPos(), out);
                }
            }
        });
    }
    // thread ------------------------------------------------------
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
    // entity selection ---------------------------------------------
    public void select(int[] xy) {
        clearInfo();
        this.currentXY = xy;
        setInfo(xy[0], infoX);
        setInfo(xy[1], infoY);
        Object select = container.findByPos(xy, map.getGridSize());
        setSelection(select);
    }
    void setSelection(Object select) {
        if (select == null) {
            selectLabel.setText("None");
        } else {
// Places - Stations
            if (Place.class.isAssignableFrom(select.getClass())) {
                setInfo(((Place)select).getPos()[0], infoX);
                setInfo(((Place)select).getPos()[1], infoY);

                setInfo(((Place)select).getCapacity(), infoCap);
                setInfo(((Place)select).getLoad(), infoLoad);
                selectedId = ((Place)select).getId();

                if (select.getClass() == Harbour.class) {
                    selectLabel.setText("Harbour");
                } else if (select.getClass() == CivilAirport.class) {
                    selectLabel.setText("Civil Airport");
                } else if (select.getClass() == MilitaryAirport.class) {
                    militaryAdd.setVisible(true);
                    selectLabel.setText("Military Airport");
                }
            }
// Vehicles
            else if (Vehicle.class.isAssignableFrom(select.getClass())) {
                selectedId = ((Vehicle)select).getId();
                setInfo(((Vehicle)select).getPos()[0], infoX);
                setInfo(((Vehicle)select).getPos()[1], infoY);

                comboBox.setVisible(true);
                changeDestination.setVisible(true);
                Map<Integer, Place> ref = container.getPlaces();

    // Airplane
                if (Airplane.class.isAssignableFrom(select.getClass())) {
                    setInfo(((Airplane)select).getFuel(),       infoFuel);
                    setInfo(((Airplane)select).getWorkers(),    infoWork);
        // Passenger Plane
                    if (select.getClass() == PassengerPlane.class) {
                        selectLabel.setText("Passenger Plane");
                        setInfo(((PassengerPlane)select).getCapacity(), infoCap);
                        setInfo(((PassengerPlane)select).getLoad(),     infoLoad);
                        setComboBox(ref, CivilAirport.class);
        // Military Plane
                    } else if (select.getClass() == MilitaryPlane.class) {
                        selectLabel.setText("Military Plane");
                        setInfo(((MilitaryPlane)select).getType(), infoWeapon);
                        setComboBox(ref, MilitaryAirport.class);
                    }
                }
    // Ship
                else if (Ship.class.isAssignableFrom(select.getClass())) {
                    setInfo(((Ship)select).getMaxSpeed(), infoSpeed);
                    setComboBox(ref, Harbour.class);
        // Carrier Ship
                    if (select.getClass() == CarrierShip.class) {
                        selectLabel.setText("Carrier Ship");
                        setInfo(((CarrierShip)select).getWeapon(), infoWeapon);
        // Cruise Ship
                    } else if (select.getClass() == CruiseShip.class) {
                        selectLabel.setText("Cruise Ship");
                        setInfo(((CruiseShip)select).getCapacity(), infoCap);
                        setInfo(((CruiseShip)select).getLoad(),     infoLoad);
                        setInfo(((CruiseShip)select).getCompany(),  infoCompany);
                    }
                }
            }
        }
    }
    // clear all TextAreas on the main panel
    void clearInfo() {
        militaryAdd.setVisible(false);
        comboBox.removeAllItems();
        comboBox.setVisible(false);
        changeDestination.setVisible(false);
        labDest.setVisible(false);

        for(Component c:panel.getComponents()) {
            if (c instanceof JTextArea) {
                ((JTextArea)c).setText("");
            }
        }
    }
    // display information about currently selected object
    void setInfo(int val, JTextArea handle) {
        handle.setText(Integer.toString(val));
    }
    void setInfo(String val, JTextArea handle) {
        handle.setText(val);
    }
    void setComboBox(Map<Integer, Place> ref, Class T) {
        for (Integer key : ref.keySet()) {
            if (ref.get(key).getClass() == T) {
                comboBox.addItem(key);
            }
        }
    }
    // TBD this should be removed -- attaches container and map
    public void attach(EntityContainer container, MapSystem map, int id) {
            this.container  = container;
            this.map        = map;
            this.recId   = id;
    }
}