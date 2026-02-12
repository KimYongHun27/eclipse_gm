package MyGame;

public class Character {
	private String name, Style;
	private int MaxHealthP, HealthP;
	private int  AttP, DefP ,CrtPct, Speed;
	private int MaxGoldP, GoldP;
	private int MaxManaP, ManaP;
	private int MaxExpP, ExpP, LevelP, ExpWeight;
	private int incAttP, incDefP, incHealthP;
	private boolean isAvlive;
	
	public void Attack(Character other/*내가 때린 객체*/)
	{
		other.Hit(this, CalculateDamage(other.GetDefP()));
		System.out.println(this.name + "의 공격! " + other.name + "에게 " + AttP + "의 피해를 입혔습니다.");
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
	}
	public void applyVictoryRewardHp()
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
	
	public void applyVictoryRewardMp()
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
	
	public boolean isFast(Character other)
	{
		return (Speed > other.Speed) ? true : false;
	}
	
	private void refreshExpInfo() {
	    this.ExpP = this.AttP + this.DefP + this.MaxHealthP; 
	    this.ExpWeight = Math.max(1, this.ExpP / 100);
	}
	
	 String GetName()
	{
		return name;
	}
	 void SetName(String name_)
	 {
		 name = name_;
	 }
	 String GetStyle()
	{
		return Style;
	}
	 void SetStyle(String style)
	{
		Style = style;
	}
	 int GetCritical()
	{
		return CrtPct;
	}
	 void SetCritical(int crt)
	{
		CrtPct = crt;
	}
	 int GetGoldP()
	{
		return GoldP;
	}
	 void SetGoldP(int gold)
	{
		GoldP = gold;
	}
	 int GetLevelP()
	{
		return LevelP;
	}
	 void SetLevelP(int level)
	{
		LevelP = level;
	}
	 int GetHP()
	{
		return HealthP;
	}
	 void SetHp(int hp)
	{
		HealthP = hp;
	}
	 int GetDefP()
	{
		return DefP;
	}
	 void SetDefP(int def)
	{
		DefP = def;
	}
	 int GetAttP()
	{
		return AttP;
	}
	 void SetAttP(int att)
	{
		AttP = att;
	}
	 int GetExpP()
	{
		return ExpP;
	}
	 void SetExpP(int exp_) {
		 ExpP = exp_;
	 }
	 int GetExpWeight()
	{
		return ExpWeight;
	}
	 void SetExpWeight(int ew_) {
		 ExpWeight = ew_;
	 }
	 boolean GetIsAlive()
	{
		return isAvlive;
	}
	 void SetIsAlive(boolean alive)
	{
		isAvlive = alive;
	}
	 int GetMaxGoldP() {
		 return MaxGoldP;
	 }
	 void SetMaxGoldP(int gold_) {
		 MaxGoldP = gold_;
	 }
	 int GetSpeed() {
		return Speed;
	}
	 void SetSpeed(int spd_) {
		Speed = spd_;
	}
	 int GetMaxHP() {
		return MaxHealthP;
	}
	 void SetMaxHP(int maxHp_)
	{
		MaxHealthP = maxHp_;
	}
	 int GetMana() {
		return ManaP;
	}
	 void SetMana(int mana_) {
		ManaP = mana_;
	}
	 int GetMaxMana() {
		return MaxManaP;
	}
	 void SetMaxMana(int mana_) {
		MaxManaP = mana_;
	}
	 int GetMaxExp() {
		return MaxExpP;
	}
	 void SetMaxExp(int exp_)
	{
		MaxExpP = exp_;
	}
	 int GetincAtt() {
		 return incAttP;
	 }
	 void SetincAtt(int att_) {
		 incAttP = att_;
	 }
	 int GetincDef() {
		 return incDefP;
	 }
	 void SetincDef(int def_)
	 {
		 incDefP = def_;
	 }
	 int GetincHp() {
		 return incHealthP;
	 }
	 void SetincHp(int hp_) {
		 incHealthP = hp_;
	 }
	
	public Character(String name, int hp, int att, int def, int spd) {
	    this.name = name;
	    this.MaxHealthP = hp;
	    this.HealthP = hp;
	    this.AttP = att;
	    this.DefP = def;
	    this.LevelP = 1;
	    this.Speed = spd;
	    this.MaxExpP = 100; // 초기 목표치
	    this.SetIsAlive(true);
	    this.refreshExpInfo();
	}
}
