package Core;

import javax.swing.*;
import Vehicles.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Options implements Runnable {
    private EntityContainer container;
    private Thread t;
    private int selectedId = 0;

    private JPanel panel;
    private JLabel vehSelect;
    private JLabel vehCoord;
    private JButton newShipButton;
    private JButton vehRemove;
    private JLabel output;

    public Options() {
        vehRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                container.remove(selectedId);
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
    public void select(int id) {
        this.selectedId = id;
        Object select = container.get(id);
        if (select == null) {
            vehSelect.setText("None");
        } else if (select.getClass() == PassengerPlane.class) {
            vehSelect.setText("Passenger Plane");
        } else if(select.getClass() == MilitaryPlane.class) {
             vehSelect.setText("Military Plane");
        } else if(select.getClass() == CarrierShip.class) {
             vehSelect.setText("Carrier Ship");
        } else if(select.getClass() == CruiseShip.class) {
            vehSelect.setText("Cruise Ship");
        }
    }
    public void attach(EntityContainer container) {
        this.container = container;
    }
}
