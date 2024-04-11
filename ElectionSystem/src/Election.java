import java.util.LinkedList;
import java.util.Random;

//
public class Election {
    Candidate[] c;
    Random random = new Random();
    int p;

    public Election(int numOfVotes) {
        p = numOfVotes;
    }

    //int p;
    public void initializeCandidates(LinkedList<String> candidates) {
        c = new Candidate[candidates.size()];
        for(int i = 0; i < candidates.size(); i++) {
            c[i] = new Candidate(candidates.get(i));
        }
    }

    public void castVote(String candidate) {
        for(int i = 0; i < c.length; i++) {
            if(candidate.equals(c[i].name)) {
                c[i].votes += 1;
                c[i].priority += 1;
            }
        }

    }

    public void castRandomVote() {
        int hat = random.nextInt(c.length);
        castVote(c[hat].name);

    }

    public void rigElection(String candidate) {

        int difference = 0;
        Candidate rigged = null;
        for(int i = 0; i < c.length; i++) {
            if(c[i].name.equals(candidate)) {
                rigged = c[i];
            }
        }
        if(!c[0].name.equals(candidate) && rigged != null) {
            difference = (c[0].votes - rigged.votes) + 1;
            c[0].votes -= difference;
            c[0].priority -= difference ;
            rigged.votes += difference;
            rigged.priority += difference;
        }

    }

    public void getTopKCandidates(int k) {
        //heapify the array
        //c = heapify(c, c.length, 0);
        buildHeap(c, c.length);
        System.out.println("The top " + k + " candidates were: \n");
        printResults(k);
    }


    public void printResults(int length) {
        for(int i = 0; i < length; i++) {
            System.out.println(c[0].name);
            c[0].priority = -1 - i;
            //c = heapify(c, c.length, 0);
            buildHeap(c, c.length);
        }

    }

    public void auditElection() {
        for(int i = 0; i < c.length; i++) {
            c[i].priority = c[i].votes;
            System.out.println(c[i].name + " : " + c[i].votes + " votes \n");
        }
        //c = heapify(c, c.length, 0);
        buildHeap(c, c.length);
    }

    public Candidate[] heapify(Candidate[] can, int n, int first) {
        //start heapify
        int indexLargest = first;
        int indexLeft = 2*first+1;
        int indexRight = 2*first+2;
        if(indexLeft < n && can[indexLeft].priority > can[indexLargest].priority) {
            indexLargest = indexLeft;
        }
        if(indexRight < n && can[indexRight].priority > can[indexLargest].priority) {
            indexLargest = indexRight;
        }
        //swap and recurse
        if(indexLargest != first) {
            Candidate temp = can[first];
            can[first] = can[indexLargest];
            can[indexLargest] = temp;
            heapify(can, n, indexLargest);
        }
        return can;
    }

    public void buildHeap(Candidate[] can, int n)
    {
        // Index of last non-leaf node
        int startIdx = (n / 2) - 1;

        // Perform reverse level order traversal
        // from last non-leaf node and heapify
        // each node
        for (int i = startIdx; i >= 0; i--) {
            heapify(can, n, i);
        }
    }
}
