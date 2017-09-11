import java.util.Scanner;

public class Vball {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);

		System.out.print("Enter number of points needed to win: ");
		int maxScore = Integer.parseInt(in.nextLine());
		System.out.print("Enter number of players on team 1: ");
		int teamOnePlayers = Integer.parseInt(in.nextLine());
		System.out.print("Enter number of players on team 2: ");
		int teamTwoPlayers = Integer.parseInt(in.nextLine());

		String[] teamOne = new String[teamOnePlayers];
		String[] teamTwo = new String[teamTwoPlayers];
		System.out.println("Enter team 1 players:");
		for(int i = 0; i < teamOnePlayers; i++){
			teamOne[i] = in.nextLine();
		}
		System.out.println("Enter team 2 players:");
		for(int i = 0; i < teamTwoPlayers; i++){
			teamTwo[i] = in.nextLine();
		}

		int server1 = 0;
		int server2 = 0;
		int points1 = 0;
		int points2 = 0;
		int[] goodServes = new int[teamOnePlayers + teamTwoPlayers];
		int[] badServes = new int[teamOnePlayers + teamTwoPlayers];
		int teamServing = 1;

		System.out.println("Input format:");
		System.out.println("First Character:");
		System.out.println("  O: Successful serve");
		System.out.println("  X: Failed serve");
		System.out.println("Second Character:");
		System.out.println("  1: Team 1 scored");
		System.out.println("  2: Team 2 scored");

		while(points1 < maxScore && points2 < maxScore){
			String server;
			if(teamServing == 1){
				server = teamOne[server1];
			} else {
				server = teamTwo[server2];
			}
			System.out.print(points1 + " - " + points2 + " | " + server + " serving: ");
			int currentServer = (teamServing - 1) * teamOnePlayers + server2;
			String result = in.nextLine();
			if(result.charAt(0) == 'O'){
				goodServes[currentServer]++;
			} else {
				badServes[currentServer]++;
			}
			int winner;
			if(result.charAt(1) == '1'){
				points1++;
				winner = 1;
			} else {
				points2++;
				winner = 2;
			}
			if(teamServing != winner){
				if(winner == 2){
					server1 = (server1 + 1) % teamOnePlayers;
				}
				if(winner == 1){
					server2 = (server2 + 1) % teamTwoPlayers;
				}
			}
			teamServing = winner;
		}

		System.out.println("Final results:");
		System.out.println(points1 + " - " + points2);
		for(int i = 0; i < teamOnePlayers; i++){
			System.out.println(teamOne[i] + ": " + goodServes[i] + " - " + badServes[i] + " (" + (goodServes[i] / 1.0f / (goodServes[i] + badServes[i])) + ")");
		}
		for(int j = 0; j < teamTwoPlayers; j++){
			int i = j + teamOnePlayers;
			System.out.println(teamTwo[j] + ": " + goodServes[i] + " - " + badServes[i] + " (" + (goodServes[i] / 1.0f / (goodServes[i] + badServes[i])) + ")");
		}
	}
}
