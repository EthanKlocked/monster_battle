package monster_battle;

import javax.swing.JOptionPane;

class Monster {
	String name;
	int hp = 80;

	public Monster(String n) {
		name = n;
	}

	public void attack(Human h) {
		attack(h, 10);
	}

	public void attack(Human h, int damage) {
		if(hp > 0) {
	        h.hp = h.hp - damage;
	        if (h.hp < 1) {
	            JOptionPane.showMessageDialog(null, "Game Over");
	            System.exit(0);
	        }
	        if(damage < 30) {
	        	MonsterBattle.lbl2.setText(name + "의 공격. " + h.name + "의 체력은 " + h.hp + "이다\n");	
	        }else {
	        	MonsterBattle.lbl2.setText(name + "의 강한 공격. " + h.name + "의 체력은 " + h.hp + "이다\n");
	        }
		}
	}
}

 

class Slime extends Monster{
	//생성자
	Slime(String n){
		super(n);
	}

	//약한 공격
	@Override
	public void attack(Human h) {
		attack(h, 8);
	}

	//치료
	void heal(Monster s) {
		if(hp > 0 && s.hp > 0) {
			s.hp += 10;
			if(s.hp > 80) {
				s.hp = 80;
			}
			MonsterBattle.lbl2.setText(name + "은 " + s.name + "을 치료. 남은 체력은" + s.hp + "." );
		}
	}
}

class Mushroom extends Monster{
	Mushroom(String n){
		super(n);
	}
	//강한 공격
	@Override
	public void attack(Human h) {
		attack(h, 15);
	}
	public void power_attack(Human h) {
		attack(h, 30);
	}
}