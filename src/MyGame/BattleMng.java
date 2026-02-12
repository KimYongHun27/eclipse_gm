package MyGame;

public class BattleMng {

	void battle(Character attacker, Character defender) {
		
		attacker.Attack(defender);
		
		if(!defender.GetIsAlive())
		{
			System.out.println(defender.GetName() + "이(가) 쓰러졌습니다.");
			attacker.Death(defender);
			
			attacker.applyVictoryRewardHp();
			attacker.applyVictoryRewardMp();
			attacker.AddExprience(defender);
			attacker.AddGold((int)(defender.GetGoldP() * 0.9));
		}
	}
	
	
	
	public BattleMng() {}
}
