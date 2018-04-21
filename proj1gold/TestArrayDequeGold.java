import org.junit.Assert;
import org.junit.Test;

public class TestArrayDequeGold {

    @Test
    public void randomizedTest () {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        for (int i = 0; i < 30; i++) {
            int fun = StdRandom.uniform(3);
            switch (fun) {
                case 0 :
                    int addnum = StdRandom.uniform(10);
                    sad.addFirst(addnum);
                    ads.addFirst(addnum);
                    break;
                case 1 :
                    addnum = StdRandom.uniform(10);
                    sad.addLast(addnum);
                    ads.addLast(addnum);
                    break;
                case 2 :
                    if (!ads.isEmpty()) {
                        int sadRm = sad.removeFirst();
                        int adsRm = ads.removeFirst();
                        Assert.assertEquals(adsRm, sadRm);
                    }
                    break;
                case 3 :
                    if (!ads.isEmpty()) {
                        int sadRm = sad.removeLast();
                        int adsRm = ads.removeLast();
                        Assert.assertEquals(adsRm, sadRm);
                    }
                    break;
            }
        }
        for(int i = 0; i < ads.size(); i++) {
            Assert.assertEquals(ads.get(i), sad.get(i));
        }
    }

}
