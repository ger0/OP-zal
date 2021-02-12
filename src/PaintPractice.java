import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PaintPractice extends JPanel implements MouseListener {
    int x;
    int y;
    public PaintPractice(){
        super();
        addMouseListener(this);
    }
    public void update(int[] posXY) {
       x = posXY[0];
       y = posXY[1];
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillOval(x, y, 50, 50);
    }
    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        removeAll();
        repaint();
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
}