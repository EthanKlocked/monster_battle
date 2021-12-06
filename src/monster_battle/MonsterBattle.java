// monster_battle.java

package monster_battle;

import java.awt.Color;
import java.awt.Font;
import java.util.Enumeration;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class MonsterBattle {
	// 라벨 변수 선언
	static JLabel lbl, lbl2, imgLbl, imgLbl2;
	static ImageIcon bsImg, rsImg;

	// 슬라임과 인간객체 생성
	static Slime s1 = new Slime("슬라임");
	static Mushroom m1 = new Mushroom("주황버섯");
	static Human h = new Human();		

	// 버튼
	static JButton btn1;
	static JButton btn2;

    public static void main(String[] args) {
    	//이름받기
    	String name = JOptionPane.showInputDialog("이름을 입력하세요");
    	h.name = name;
    	if(name == null) {
    		System.exit(0);
    	}
    	
        // 글꼴
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource)
                UIManager.put(key, new FontUIResource("굴림", Font.BOLD, 12));
        }    	
 
        // [start] 프레임 설정
        JFrame frm = new JFrame();
        frm.setTitle("몬스터 퇴치하기");
        frm.setSize(400, 400);
        frm.setLocationRelativeTo(null);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.getContentPane().setLayout(null);
        frm.getContentPane().setBackground(Color.white);

        // 버튼 설정
        btn1 = new JButton(s1.name + " 공격!");
        btn2 = new JButton(m1.name + " 공격!");
        btn1.setBounds(58, 220, 122, 30);
        btn2.setBounds(210, 220, 122, 30);
        frm.getContentPane().add(btn1);
        frm.getContentPane().add(btn2);

        // 라벨 설정
        lbl = new JLabel();
        lbl.setBounds(58, 250, 280, 50);
        lbl.setText("게임을 시작합니다");
        lbl.setHorizontalAlignment(JLabel.CENTER); // 수평 가운데 정렬
        frm.getContentPane().add(lbl);

        // 라벨2 설정
        lbl2 = new JLabel();
        lbl2.setBounds(58, 280, 280, 50);
        lbl2.setText(h.name + "의 체력은 "+h.hp + "입니다.");
        lbl2.setHorizontalAlignment(JLabel.CENTER); // 수평 가운데 정렬
        frm.getContentPane().add(lbl2);        

        // 이미지 라벨1 생성
        imgLbl = new JLabel();
        bsImg = new ImageIcon(MonsterBattle.class.getResource("/monster_battle/img/slime.png"));
        imgLbl.setIcon(bsImg);
        imgLbl.setBounds(58, 50, 140, 150);
        imgLbl.setHorizontalAlignment(JLabel.CENTER);
        frm.getContentPane().add(imgLbl);
        
        // 이미지 라벨2 생성
        imgLbl2 = new JLabel();
        rsImg = new ImageIcon(MonsterBattle.class.getResource("/monster_battle/img/mushroom.png"));        
        imgLbl2.setIcon(rsImg);
        imgLbl2.setBounds(210, 50, 140, 150);
        imgLbl2.setHorizontalAlignment(JLabel.CENTER);
        frm.getContentPane().add(imgLbl2);

        // 프레임이 보이도록 설정
        frm.setVisible(true);

        // 버튼이 눌렸을때
        btn1.addActionListener(event -> {
            battle(s1);
        });
        btn2.addActionListener(event -> {
        	battle(m1);
        });        
    }

    public static void battle(Monster mon) {
    	int randomNum = (int) (Math.random() * 3);
    	if(randomNum == 0) {
    		lbl.setText(h.name + "의 공격은 빗나갔다.");
    	}else {
    		h.attack(mon);	
    	}
		if(mon instanceof Slime) {
	    	randomNum = (int) (Math.random() * 3);
	    	if(randomNum == 0) {
	    		((Slime) mon).heal(mon);
	    	}else{
	    		mon.attack(h);	
	    	}
		}else {
			randomNum = (int) (Math.random() * 3);
			if(randomNum == 0) {
				((Mushroom) mon).power_attack(h);
	    	}else {
	    		mon.attack(h);
	    	}
		}

    	//슬라임이 모두 죽으면 게임 클리어
    	if(s1.hp < 1 && m1.hp < 1) {
    		JOptionPane.showMessageDialog(null, "Game Clear!");
    		System.exit(0);
    	}
    }
}