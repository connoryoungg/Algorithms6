import java.util.LinkedList;
import java.util.Random;
public class ElectionSystem {
    public static void main(String[] args) {
        Random random = new Random();
        //randomize number of voters
        int p = random.nextInt(500,1000);

        Election election = new Election(p);
        //create all candidates
        LinkedList<String> candidates = new LinkedList<>();
        candidates.add("Marcus Fenix");
        candidates.add("Dominic Santiago");
        candidates.add("Damon Baird");
        candidates.add("Stone Coleman");
        candidates.add("Buddy Holly");
        candidates.add("Sabado Domingo");
        candidates.add("Daniel Larson");
        candidates.add("Milly Bobby");
        candidates.add("Dwayne Lillard");
        candidates.add("Peaches MkGee");

        //randomize candidates
        LinkedList<String> actualCandidates = new LinkedList<>();
        int newRandom = 0;
        for(int i = 0; i < candidates.size() - 1; i++) {
            newRandom = random.nextInt(1, 3);
            i = i * newRandom;
            if(i > candidates.size() - 1) {
                i = candidates.size() - 1;
            }
            actualCandidates.add(candidates.get(i));
        }

        //planned election
        System.out.println("Planned Election \n");
        election.initializeCandidates(candidates);

        for(int i = 1; i < p + 1; i++) {
            election.castRandomVote();
        }


        //get results before rig
        election.getTopKCandidates(3);
        election.auditElection();
        //rig election for whoever is at index 1 of heap
        election.rigElection("Stone Coleman");
        System.out.println("Rigged: \n");
        //get results after rig
        election.getTopKCandidates(3);
        election.auditElection();

        //do election for randomized
        System.out.println("Random Election \n");
        election.initializeCandidates(actualCandidates);

        for(int i = 1; i < p + 1; i++) {
            election.castRandomVote();
        }


        //get results before rig
        election.getTopKCandidates(3);
        election.auditElection();
        //rig election for whoever is at index 1 of heap
        election.rigElection(election.c[1].name);
        System.out.println("Rigged: \n");
        //get results after rig
        election.getTopKCandidates(3);
        election.auditElection();


    }
}
