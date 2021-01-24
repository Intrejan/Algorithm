import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        sc.close();
        String[] a1 =  s1.split(" ");
        int n = Integer.parseInt(a1[0]);
        int max_count = Integer.parseInt(a1[1]);
        int max_cost = Integer.parseInt(a1[2]);
        String[] a2 = s2.split(" ");
        Card[] cards = new Card[n];
        for(int i=0;i<n;i++){
            Card card = new Card(Integer.parseInt(a2[2*i]),Integer.parseInt(a2[2*i+1]));
            cards[i] = card;
        }
        Result max = new Result();
        max.value = 0;

        boolean[] used = new boolean[n];
        Deque<Card> path = new ArrayDeque<>();
        dfs(cards,path, max,0,max_count,used,max_cost);
        System.out.print(max.value);
    }

    private static void dfs(Card[] cards, Deque<Card> path,Result max, int depth, int max_count, boolean[] used,int max_cost) {
        int cost = 0;
        int power = 0;
        for(Card card:path){
            cost+=card.cost;
            power+=card.power;
        }
        if(cost<=max_cost && power>max.value){
            max.value = power;
        }
        //System.out.println(max);
        if(depth==max_count){
            return;
        }

        for(int i=0;i<cards.length;i++){
            if(used[i]){
                continue;
            }
            path.addLast(cards[i]);
            used[i] = true;
            dfs(cards, path, max,depth+1, max_count, used,max_cost);
            path.removeLast();
            used[i] = false;
        }
    }
}

class Card{
    int cost;
    int power;
    Card(int cost,int power){
        this.cost = cost;
        this.power = power;
    }
}

class Result{
    int value;
}