package main.java.com.quete_des_3_heros.view.combat_ui;

import main.java.com.quete_des_3_heros.controller.CombatController;
import main.java.com.quete_des_3_heros.element.Entity;

/**
 * Command pattern, move command
 */
public class Move {
    Entity entity;
    int[] start_point;
    int[] end_point;
    CombatController combatController;

    public Move(Entity entity, int x, int y, CombatController combatController){
        this.entity = entity;

        start_point = new int[2];
        start_point[0] = entity.getX();
        start_point[1] = entity.getY();

        end_point = new int[2];
        end_point[0] = x;
        end_point[1] = y;

        this.combatController = combatController;
    }

    public void execute() {
        combatController.moveOnPathEntity(entity, end_point[0], end_point[1]);
    }

    public void undo() {
        combatController.moveOnPathEntity(entity, start_point[0], start_point[1]);
    }
}
