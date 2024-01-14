package main.java.com.quete_des_3_heros.view.components;

import main.java.com.quete_des_3_heros.element.heros.skills.Skill;

public class SkillButton extends GameButton {
    private Skill skill;
    
    public SkillButton(Skill skill) {
        super("<html><p>" + skill.getName() + "</p><p style=\"font-size: 10px\">" + "att:" + 
        skill.getAttack() + "/rng:" + skill.getRange() + "/mp:" + skill.getMana_consumption() +  "</p></html>");

        this.skill = skill;
    }

    public Skill getSkill() {
        return skill;
    }
}
