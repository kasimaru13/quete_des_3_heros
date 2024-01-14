package main.java.com.quete_des_3_heros.view.components;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;

import main.java.com.quete_des_3_heros.inventory.Item;

/**
 * Inventory button (Image, name of item and description of item)
 */
public class InventoryButton extends GameButton {
    String itemName;
    Item item;

    public InventoryButton(Item item) {
        super("<html><p>" + item.getName() + "</p><p style=\"font-size:14\">" + item.getDescription() + "</p></html>");
        setIcon(new ImageIcon(item.getSprite().getScaledInstance(48, 48, Image.SCALE_SMOOTH)));
        setMaximumSize(new Dimension(250, 90));
        setPreferredSize(new Dimension(250, 90));

        this.item = item;
    }

    public Item getItem() {
        return item;
    }
}
