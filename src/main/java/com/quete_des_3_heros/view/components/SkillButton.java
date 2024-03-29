package main.java.com.quete_des_3_heros.view.components;

import main.java.com.quete_des_3_heros.element.heros.skills.Skill;

/**
 * Button displayed when clicking on skill button, displays informations on skills of current character
 */
public class SkillButton extends GameButton {
    private Skill skill;
    
    public SkillButton(Skill skill) {
        super("<html><p>" + skill.getName() + "</p><p style=\"font-size: 10px\">" + "damage:" + 
        skill.getAttack() + "/range:" + skill.getRange() + "/mp:" + skill.getMana_consumption() +  "</p></html>");

        this.skill = skill;
    }

    public Skill getSkill() {
        return skill;
    }
}
