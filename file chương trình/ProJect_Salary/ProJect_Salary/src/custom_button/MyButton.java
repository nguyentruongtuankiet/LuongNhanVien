/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package custom_button;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 *
 * @author ADMIN
 */
public class MyButton extends JButton{
    private boolean hover;
    private Color color;
    private Color colorhover;
    private Color colorclick;
    private int radius = 0;
    private Color borderColor;
    

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    } 
    
    public boolean isHover() {
        return hover;
    }

    public void setHover(boolean hover) {
        this.hover = hover;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        setBackground(color);
    }

    public Color getColorhover() {
        return colorhover;
    }

    public void setColorhover(Color colorhover) {
        this.colorhover = colorhover;
    }

    public Color getColorclick() {
        return colorclick;
    }

    public void setColorclick(Color colorclick) {
        this.colorclick = colorclick;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
    
    
    
    public MyButton() {
        //init Color
        setColor(color.WHITE);
        colorhover = new Color(52, 235, 155);
        colorclick= new Color(245, 93, 66);
        borderColor = new Color(245, 93, 66);
//        colorhover = Color.GRAY;
//        colorclick = Color.red;
//        borderColor = Color.black;
        setContentAreaFilled(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(colorhover);
                hover = true;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(color);
                hover = false;
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(colorclick);
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
               if(hover==true){
                   setBackground(colorhover);
               }else{
                   setBackground(color);
               }
            }
        
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(borderColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        g2.setColor(getBackground());
        g2.fillRoundRect(2, 2, getWidth()-4, getHeight()-4, radius ,radius);
        super.paintComponent(g); 
    // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
    
}
