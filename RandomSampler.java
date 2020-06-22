import java.util.Random;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import org.apache.commons.lang.ArrayUtils;

public class RandomSampler {
    public static void main(String[] args) {
        String[] sentences = {"I go to school by bus.",
                              "I go to work by bus.",
                              "I go hiking by bus.",
                              "I go to sea by bus",
                              "I go to home by bus."};
        int rand = 3;

        RandSampler RS = new RandSampler(sentences, rand);
        System.out.println("The random selected sentences are: " + RS.generate());
    }
}


class RandSampler {
    private String[] sentences;
    private int rand;

    public RandSampler(String[] sentences, int rand) {
        this.sentences = sentences;
        this.rand = rand;
    }

    public List<String> generate() {
        Random random = new Random();

        //create a temporary list for storing selected elements
        List<String> newList = new ArrayList();
        List<String> sentList = new ArrayList<String>(Arrays.asList(this.sentences));
        for (int i = 0; i < this.rand; i++) {
            int randomIndex = random.nextInt(this.sentences.length);
            newList.add(sentList.get(randomIndex));
            //this.sentences = ArrayUtils.remove(this.sentences, randomIndex);
            sentList.remove(randomIndex);
        }
        return newList;
    }
}
