package MyGame;
import java.util.Scanner;
public class GameMaster {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Character player = new Character("Hero", 50, 25, 10, 1);
		Character slime = new Character("Slime", 30, 20, 5, 2);
		System.out.println("=== 전투 시작 전 ===");
        System.out.println(player.GetName() + " |" + slime.GetName());
        System.out.println("HP:" + player.GetHP()+ "|" + slime.GetHP());
        
        while(true)
        {
        	System.out.println("enter로 다음 ┛");
        	sc.nextLine();
        	player.Attack(slime);
        	System.out.println("enter로 다음 ┛");
        	sc.nextLine();
        	if(!slime.GetIsAlive())
        	{
        		System.out.println("enter로 다음 ┛");
            	sc.nextLine();
            	break;
        	}
        	slime.Attack(player);
        	System.out.println("enter로 다음 ┛");
        	sc.nextLine();
        	if(!player.GetIsAlive())
            {
        		System.out.println("enter로 다음 ┛");
            	sc.nextLine();
        		break;
            }
        	System.out.println("HP:" + player.GetHP()+ "|" + slime.GetHP());
        }
	}
}
