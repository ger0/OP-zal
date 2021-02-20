package Core;

import Entities.Places.*;
import Entities.Vehicles.*;
import Entities.Viewable;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Vector;

public class Options implements Runnable {
    private EntityContainer container;
    private MapSystem map;
    private Thread t;

    // current selection on the map
    private int selectedId = 0;
    private int[] currentXY = new int[]{0, 0};

    // the most recent entity id
    private int recId;

    JFrame frame;
    private JPanel panel;
    private JLabel selectLabel;

    // info text
    private JTextArea infoFuel, infoWork, infoCompany, infoSpeed,
                        infoCap, infoLoad, infoWeapon;
    private JTextArea infoX, infoY;

    private JButton vehRemove, passengerAdd, cruiseAdd,
                    carrierAdd, militaryAdd, emergencyButton;

    // destination | stored vehicles inside a station
    private JComboBox<Integer> comboBox;
    private JLabel labCombo;
    private JButton selectVehicle, setDest;

    private JPanel infoPanel, comboPanel, addShipPanel,
                   addPlanePanel, emergencyLanding, addVehiclePanel;

    public Options() {
        clearInfo();
        // VEHICLE REMOVAL BUTTON
        vehRemove.addActionListener(actionEvent -> {
            container.remove(selectedId);
            clearInfo();
        });

        // ADDING NEW VEHICLES
        passengerAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                PassengerPlane p = new PassengerPlane(recId, currentXY);
                addVehicleHandler(p);
            }
        });
        militaryAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CarrierShip ref = (CarrierShip) container.get(selectedId);
                MilitaryPlane p = new MilitaryPlane(recId, ref.getPos());

                infoWeapon.setEditable(false);
                addVehicleHandler(p);
                infoWeapon.setEditable(true);
            }
        });
        carrierAdd.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent actionEvent) {
                 CarrierShip p = new CarrierShip(recId, currentXY);
                 addVehicleHandler(p);
             }
        });
        cruiseAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CruiseShip p = new CruiseShip(recId, currentXY);
                addVehicleHandler(p);
            }
        });

        // DESTINATION BUTTON LISTENER
        setDest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (comboBox.getSelectedItem() != null) {
                    Place   out = (Place)container.get((Integer)comboBox.getSelectedItem());
                    Vehicle in = (Vehicle)container.get(selectedId);
                    if (out != null && in != null) {
                        in.setTarget(out.getPos(), out);
                    }
                }
            }
        });

        // SELECTION COMBOBOX BUTTON LISTENER
        selectVehicle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (comboBox.getSelectedItem() != null) {
                    int id = (int)comboBox.getSelectedItem();
                    Viewable ref = (Viewable) container.get(id);
                    if (container.get(id) != null) {
                        setSelection(ref);
                    }
                }
            }
        });
        // emergency landing on a closest available airport
        emergencyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Vehicle obj = (Vehicle)container.get(selectedId);
                Place closest = null;

                if (obj.getClass() == MilitaryPlane.class) {
                    closest = container.findClosestAirport(
                            obj.getPos(), MilitaryAirport.class);
                } else {
                    closest = container.findClosestAirport(
                            obj.getPos(), CivilAirport.class);
                }
                obj.setTarget(closest.getPos(), closest);
            }
        });
    }
    // thread ----------------------------------------------------------------------
    public void start() {
        System.out.println("Starting thread: Options");
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }
    public void run() {
        frame = new JFrame("Options");
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    // ------------------- entity selection ----------------------------------------
    public void select(int[] xy) {
        this.currentXY = xy;
        setInfo(xy[0], infoX);
        setInfo(xy[1], infoY);
        Object select = container.findByPos(xy, map.getGridSize());

        if (select != null) {
            setSelection((Viewable)select);
        } else {
            clearInfo();
            selectLabel.setText("None");
            addPlanePanel.setVisible(true);
            addShipPanel.setVisible(true);
        }
    }
    void setSelection(Viewable select) {
        clearInfo();
        comboPanel.setVisible(true);

        selectedId = select.getId();
        selectLabel.setText(select.getType());

        setInfo(select.getPos()[0],     infoX);
        setInfo(select.getPos()[1],     infoY);

        setInfo(select.getCapacity(),   infoCap);
        setInfo(select.getLoad(),       infoLoad);
        setInfo(select.getMaxSpeed(),   infoSpeed);
        setInfo(select.getFuel(),       infoFuel);
        setInfo(select.getWorkers(),    infoWork);
        setInfo(select.getWeapon(),     infoWeapon);
        setInfo(select.getCompany(),    infoCompany);

        if (select.canSetDestination()) {
            addPlanePanel.setVisible(false);
            addShipPanel.setVisible(false);

            vehRemove.setVisible(true);
            setDest.setVisible(true);

            labCombo.setText("Change destination");

            Map<Integer, Place> ref = container.getPlaces();

            if (select.getType().equals("PassengerPlane")) {
                setComboBox(ref, CivilAirport.class);
                emergencyLanding.setVisible(true);
            }
            else if (select.getType().equals("MilitaryPlane")) {
                setComboBox(ref, MilitaryAirport.class);
                emergencyLanding.setVisible(true);
            }
            else if (select.getType().equals("CarrierShip") ||
                     select.getType().equals("CruiseShip")) {
                setComboBox(ref, Harbour.class);
            }
            comboBox.setSelectedItem(select.getSelected());
        }
        else if (select.canStoreVehicles()) {
            selectVehicle.setVisible(true);
            labCombo.setText("Select vehicle");
            setComboBox(select.getStoredVehicles());
        }
        if (select.spawnsMilitary()) {
            addPlanePanel.setVisible(true);
            militaryAdd.setVisible(true);
        }
        frame.pack();
    }
    // clear all TextAreas on the main panel
    void clearInfo() {
        addVehiclePanel.setVisible(true);
        militaryAdd.setVisible(false);
        vehRemove.setVisible(false);
        emergencyLanding.setVisible(false);

        comboBox.removeAllItems();
        setDest.setVisible(false);
        selectVehicle.setVisible(false);
        comboPanel.setVisible(false);

        for(Component c: infoPanel.getComponents()) {
            if (c instanceof JTextArea) {
                ((JTextArea)c).setText("");
            }
        }
    }
    // display information about currently selected object
    void setInfo(int val, JTextArea handle) {
        if (val > -1) {
            handle.setText(Integer.toString(val));
        } else {
            handle.setText("");
        }
    }
    void setInfo(String val, JTextArea handle) {
        handle.setText(val);
    }
    void setComboBox(Map<Integer, Place> ref, Class request) {
        for (int key: ref.keySet()) {
            if (ref.get(key).getClass() == request) {
                comboBox.addItem(key);
            }
        }
    }
    void setComboBox(Vector<Integer> vec) {
        for (int id: vec) {
            comboBox.addItem(id);
        }
    }

    // adding new vehicles
    int infoAsInt(JTextArea val) throws NumberFormatException {
        if (val.getText().equals("")) {
            return -1;
        } else {
            return Integer.parseUnsignedInt(val.getText());
        }
    }
    void addVehicleHandler(Vehicle obj) {
        try {
            obj.setMap(map);

            obj.setCompany(infoCompany.getText());
            obj.setWeapon(infoWeapon.getText());

            obj.setCapacity(infoAsInt(infoCap));
            obj.setWorkers(infoAsInt(infoWork));
            obj.setLoad(infoAsInt(infoLoad));
            obj.setMaxSpeed(infoAsInt(infoSpeed));

            container.add(obj, recId++);
        } catch (NumberFormatException e) {
            System.out.println("Wrong format:");
            System.out.println(e.getMessage());
        }
    }

    // TBD remove
    public void attach(EntityContainer container, MapSystem map, int id) {
            this.container  = container;
            this.map        = map;
            this.recId   = id;
    }
}