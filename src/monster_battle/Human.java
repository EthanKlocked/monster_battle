package monster_battle;

import javax.swing.ImageIcon;

import java.util.Timer;
import java.util.TimerTask;

class Human {
	String name;
	int hp = 100;

	public void attack(Monster mon) {		
		mon.hp = mon.hp - 30;
        if (mon.hp < 1) {
    		//사망시 이미지
    		ImageIcon sImg_dead = new ImageIcon(MonsterBattle.class.getResource("/monster_battle/img/slime_dead.png"));
    		ImageIcon mImg_dead = new ImageIcon(MonsterBattle.class.getResource("/monster_battle/img/mushroom_dead.png"));        	

        	if(mon == MonsterBattle.s1) {
        		MonsterBattle.btn1.setEnabled(false);
        		MonsterBattle.imgLbl.setIcon(sImg_dead);
        	} else {
        		MonsterBattle.btn2.setEnabled(false);
        		MonsterBattle.imgLbl2.setIcon(mImg_dead);
        	}

        	MonsterBattle.lbl.setText(mon.name + "은 사망했다\n");
        	MonsterBattle.lbl2.setText("");
        }else {
    		//피격시 이미지
    		ImageIcon sImg_attacked = new ImageIcon(MonsterBattle.class.getResource("/monster_battle/img/slime_attacked.png"));
    		ImageIcon mImg_attacked = new ImageIcon(MonsterBattle.class.getResource("/monster_battle/img/mushroom_attacked.png"));

    		Timer timer1 = new Timer();
    		TimerTask task1;

    		if (mon == MonsterBattle.s1) {
    			MonsterBattle.imgLbl.setIcon(sImg_attacked);
    			task1 = new TimerTask() {
        			@Override
        			public void run() {
        				//일정시간 후에 수행
        				MonsterBattle.imgLbl.setIcon(MonsterBattle.bsImg);
        				timer1.cancel();
        			}
        		};
    		}else{
    			MonsterBattle.imgLbl2.setIcon(mImg_attacked);
    			task1 = new TimerTask() {
        			@Override
        			public void run() {
        				//일정시간 후에 수행
        				MonsterBattle.imgLbl2.setIcon(MonsterBattle.rsImg);
        				timer1.cancel();
        			}
        		};    			
    		}
    		timer1.schedule(task1, 500);        	
        	MonsterBattle.lbl.setText("현재 " + mon.name + "의 체력은 " + mon.hp + "이다\n");        	
        }
	}
}