import java.util.Random;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;   
import java.util.ArrayList;

public class RandomSampler {
    public static void main(String[] args) {
        String[] sentences = {"I go to school by bus.",
                              "I go to work by bus.",
                              "I go hiking by bus.",
                              "I go to sea by bus",
                              "I go to home by bus."};

        RandSampler RS = new RandSampler(sentences);
        
        long startTime = System.nanoTime();
        for(int i=0; i<100000; i++) {
            List<String> t = RS.generate(3);
            t.get(0);
        }
        long finishTime = System.nanoTime() - startTime;
        System.out.println("The time span is " + finishTime);
        
        startTime = System.nanoTime();
        for (int i=0; i<100000; i++) {
            List<String> t = RS.generatefast(3);
            t.get(0);
        }
        finishTime = System.nanoTime() - startTime;
        System.out.println("The time span is " + finishTime);

        startTime = System.nanoTime();
        for (int i=0; i<100000; i++) {
            List<String> t = RS.generatetuple(3);
            t.get(0);
        }
        finishTime = System.nanoTime() - startTime;
        System.out.println("The time span is " + finishTime);
    }
}


class RandSampler {
    private Random random = new Random();
    private String[] sentences;

    public RandSampler(String[] sentences) {
        this.sentences = sentences;
    }

    public List<String> generate(int num) {
        List<String> result = new ArrayList<>(num);
        ArrayList<String> sentList = new ArrayList<>(Arrays.asList(this.sentences));
        for (int i = 0; i < num; i++) {
            int randomIndex = this.random.nextInt(sentList.size());
            result.add(sentList.get(randomIndex));
            sentList.remove(randomIndex);
        }
        return result;
    }

    public List<String> generatefast(int num) {
        List<String> result = new ArrayList<String>(num);

        HashMap<String, Boolean> items = new HashMap<String, Boolean>();

        for(String s : this.sentences) {
            items.put(s, false);
        }

        Object[] index = items.entrySet().toArray();

        while (result.size() < num) {
            int i = this.random.nextInt(this.sentences.length);
            HashMap.Entry<String, Boolean> entry = (HashMap.Entry<String, Boolean>) index[i];

            if (!entry.getValue()) {
                entry.setValue(true);
                result.add((String) entry.getKey());
            }
        }

        return result;
    }

    public List<String> generatetuple(int num) {
        List<String> result = new ArrayList<String>(num);
        Pair[] items = new Pair[this.sentences.length];

        for(int i=0; i< this.sentences.length; i++) {
            items[i] = new Pair(this.sentences[i], false);
        }

        while (result.size() < num) {
            int i = this.random.nextInt(this.sentences.length);
            Pair pair = items[i];

            if (!pair.value) {
                pair.value = true;
                result.add(pair.key); 
            }
        }
        return result;
    }
}

class Pair {
    public String key;
    public Boolean value;

    public Pair(String key, Boolean value) {
        this.key = key;
        this.value = value;
    }
}
