package MyGame;
import java.util.Scanner;
public class GameMaster {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Character player = new Character("Hero", 50, 25, 10);
		Character slime = new Character("Slime", 30, 20, 5);
		System.out.println("=== 전투 시작 전 ===");
        System.out.println(player.GetName() + " |" + slime.GetName());
        System.out.println("HP:" + player.GetHP()+ "|" + slime.GetHP());
        
        while(true)
        {
        	System.out.println("enter로 다음 ┛");
        	sc.nextLine();
        	player.Attack(slime);
        	System.out.println(player.GetIsAlive());
        	System.out.println(slime.GetIsAlive());
        	if(!slime.GetIsAlive())
        	{
        		break;
        	}
        	slime.Attack(player);
        	if(!player.GetIsAlive())
            {
        		break;
            }
        	System.out.println("HP:" + player.GetHP()+ "|" + slime.GetHP());
        }
	}
}
