package agh.cs.lab8;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class DNAcreator {

        public static Integer mostCommonDir(List<Integer> list) {

            Collections.sort(list);
            Integer mostCommon = null;
            Integer last = null;
            int mostCount = 0;
            int lastCount = 0;
            for (Integer x : list) {
                if (x.equals(last)) {
                    lastCount++;
                } else if (lastCount > mostCount) {
                    mostCount = lastCount;
                    mostCommon = last;
                }
                last = x;
            }


                return list.indexOf(mostCommon);

        }


        public static List<Integer> exNihiloDNA() {
            List<Integer> DNA = new ArrayList<>();
            for(int i = 0; i<32; i++) {
                DNA.add(ThreadLocalRandom.current().nextInt(0,8));
            }
            lastCheckDNA(DNA);
            return DNA;
        }

        public static List<Integer> lastCheckDNA(List<Integer> DNA) {
            Collections.sort(DNA);
            for(int i = 0; i<8; i++) {
                if(!DNA.contains(i)) {
                    DNA.set(mostCommonDir(DNA), i);
                }
            }
            Collections.sort(DNA);
            return DNA;
        }

        public static List<Integer> fromParentsDNA(List<Integer> DNA1, List<Integer> DNA2) {


            int cut1 = ThreadLocalRandom.current().nextInt(0,32);
            int cut2 = ThreadLocalRandom.current().nextInt(cut1,32);
            List<Integer> childDNA = new ArrayList<>();
            for(int i = 0; i<cut1; i++) {
                childDNA.add(DNA1.get(i));
            }
            for(int i = cut1; i<cut2; i++) {
                childDNA.add(DNA2.get(i));
            }
            for(int i = cut2; i<32; i++) {
                childDNA.add(DNA1.get(i));
            }
            lastCheckDNA(childDNA);
            return childDNA;
        }
    }



