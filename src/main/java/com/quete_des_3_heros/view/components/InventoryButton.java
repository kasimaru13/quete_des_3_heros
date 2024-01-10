package main.java.com.quete_des_3_heros.view.components;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * Inventory button (Image, name of item and description of item)
 */
public class InventoryButton extends GameButton {
    String itemName;

    public InventoryButton(String name, String description, Image sprite) {
        super("<html><p>" + name + "</p><p style=\"font-size:14\">" + description + "</p></html>");
        setIcon(new ImageIcon(sprite.getScaledInstance(48, 48, Image.SCALE_SMOOTH)));
        setMaximumSize(new Dimension(250, 80));

        this.itemName = name;
    }

    public String getItemName() {
        return itemName;
    }
}
