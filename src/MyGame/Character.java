package MyGame;

public class Character {
	private String name, Style;
	private int MaxHealthP, HealthP;
	private int  AttP, DefP ,CrtPct;
	private int MaxGoldP, GoldP;
	private int MaxManaP, ManaP;
	private int MaxExpP, ExpP, LevelP, ExpWeight;
	private int incAttP, incDefP, incHealthP;
	private boolean isAvlive;
	
	public void Attack(Character other/*내가 때린 객체*/)
	{
		other.Hit(this, CalculateDamage(other.GetDefP()));
		System.out.println(this.name + "의 공격! " + other.name + "에게 " + AttP + "의 피해를 입혔습니다.");
		if(!other.GetIsAlive())
		{
			Death(other);
		}
	}
	
	public void Hit(Character attacker/*나를 때린 객체*/ , int dmg/*attacker의 데미지*/)
	{
		if(dmg < 0)
		{
			dmg = 1;
		}
		if((HealthP -= dmg) <= 0) {
			HealthP = 0;
			SetIsAlive(false);
		}
	}
	public int CalculateDamage(int def)
	{
		return AttP - def;
	}
	public void Death(Character other/*내가 죽인 객체*/) {
		System.out.println(other.name + "사망");
		//승리, 현재 hp 10%회복, 현재 마나 25% 회복, other가 가진 경험치를 비율에 맞게 획득, other 가 가진 골드의 90% 획득.
		HpHeal();
		ManaHeal();
		AddExprience(other);
		AddGold((int)(other.GetGold() * 0.9));
	}
	public void HpHeal()
	{
		//승리 시에만 호출!
		HealthP += HealthP/10;
		HealthP = clamp(HealthP, MaxHealthP);
		System.out.println(name + "의 hp 회복 (" + HealthP + ")");
	}
	public void HpHeal(int heal)
	{
		HealthP += heal;
		HealthP = clamp(HealthP, MaxHealthP);
		System.out.println(name + "의 hp 회복 (" + HealthP + ")");
	}
	
	public void ManaHeal()
	{
		//승리 시에만 호출!
		ManaP += ManaP/4;
		ManaP = clamp(ManaP, MaxManaP);
		System.out.println(name + "의 mp : " + ManaP);
	}
	public void ManaHeal(int heal)
	{
		ManaP += heal;
		ManaP = clamp(ManaP, MaxManaP);
		System.out.println(name + "의 mp : " + ManaP);
	}
	
	public void AddExprience(Character other)
	{
		int otherExp = other.GetExpP();
		int otherExpW = other.GetExpWeight();
		ExpP += otherExp;
		boolean isLevelJump = false;
		while(ExpP >= MaxExpP){
			isLevelJump = true;
			ExpP -= MaxExpP;
			++LevelP;
			System.out.println(name + "의 lv : " + LevelP);
			//inc 스탯재조정
			applyLevelUpBonus(otherExpW);// inc 스탯 적용
			calculateNextGoal(otherExp, otherExpW);// 최대 경험치량 증가
		}
		if (isLevelJump) {
	        fullRecover();  
	        
	    }
		refreshExpInfo();
	
	}
	
	public void applyLevelUpBonus(int weight)
	{
		//잡았던 적들의 스텟과 기술 등의 수치를 흡수
		this.HealthP +=(this.incHealthP + this.LevelP) * weight;
		this.AttP +=(this.incAttP + this.LevelP) * weight;
		this.DefP +=(this.incDefP + this.LevelP) * weight;
		System.out.println(name + "의 총 hp : " + HealthP);
		System.out.println(name + "의 총 att : " + AttP);
		System.out.println(name + "의 총 def : " + DefP);
	}
	public void calculateNextGoal(int exp, int expWeight)
	{
		//레벨업에 필요한 경험치량 증가
		MaxExpP += (exp * expWeight);
	}
	public void fullRecover()
	{
		//레벨업으로 컨디션 회복
		this.HealthP = this.MaxHealthP;
		this.ManaP = this.MaxManaP;
		System.out.println("레벨 업으로 체력/ 마나 전체 회복");
	}
	
	public void AddGold(int gold)
	{
		this.GoldP = gold;
		clamp(this.GoldP, this.MaxGoldP);
	}
	
	private int clamp(int current, int max) {
	    if (current > max) {
	        return max;
	    }
	    return current;
	}
	
	public String GetName()
	{
		return this.name;
	}
	public String GetStyle()
	{
		return this.Style;
	}
	public void SetStyle(String style)
	{
		this.Style = style;
	}
	public int GetCritical()
	{
		return this.CrtPct;
	}
	public void SetCritical(int crt)
	{
		this.CrtPct = crt;
	}
	public int GetGoldP()
	{
		return this.GoldP;
	}
	public void SetGoldP(int gold)
	{
		this.GoldP = gold;
	}
	public int GetLevelP()
	{
		return this.LevelP;
	}
	public void SetLevelP(int level)
	{
		this.LevelP = level;
	}
	public int GetHP()
	{
		return this.HealthP;
	}
	public void SetHp(int hp)
	{
		this.HealthP = hp;
	}
	public int GetDefP()
	{
		return this.DefP;
	}
	public void SetDefP(int def)
	{
		this.DefP = def;
	}
	public int GetAttP()
	{
		return this.AttP;
	}
	public void SetAttP(int att)
	{
		this.AttP = att;
	}
	public int GetGold()
	{
		return this.GoldP;
	}
	public void SetGold(int gold)
	{
		this.GoldP = gold;
	}
	public int GetExpP()
	{
		return this.ExpP;
	}

	public int GetExpWeight()
	{
		return this.ExpWeight;
	}
	public boolean GetIsAlive()
	{
		return this.isAvlive;
	}
	public void SetIsAlive(boolean alive)
	{
		this.isAvlive = alive;
	}
	
	private void refreshExpInfo() {
	    this.ExpP = this.AttP + this.DefP + this.MaxHealthP; 
	    this.ExpWeight = Math.max(1, this.ExpP / 100);
	}
	
	public Character(String name, int hp, int att, int def) {
	    this.name = name;
	    this.MaxHealthP = hp;
	    this.HealthP = hp;
	    this.AttP = att;
	    this.DefP = def;
	    this.LevelP = 1;
	    this.MaxExpP = 100; // 초기 목표치
	    this.SetIsAlive(true);
	    this.refreshExpInfo();
	}
}
