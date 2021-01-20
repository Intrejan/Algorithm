import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MyHash {



    public static void main(String[] args){
        LinkedList<String> linkedList = new LinkedList<String>();
        linkedList.add("b");
        linkedList.add(0,"a");
        List<String> List = new ArrayList<String>();
        List = Collections.synchronizedList(List);
        List.clear();
        Vector<String> vector = new Vector<String>();
        vector.add("v");
        HashMap<String,String> hashMap = new HashMap<String,String>();
        hashMap.put("a","b");

        HashSet<String> hashSet = new HashSet<String>();
        hashSet.add("e");

        Hashtable<String,String> hashtable = new Hashtable<String, String>();
        hashtable.put("a","b");

        ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap<String, String>();
        concurrentHashMap.put("a","b");

        StringBuilder stringBuilder = new StringBuilder("a");
        stringBuilder.append("b");
        StringBuffer stringBuffer = new StringBuffer("a");
        stringBuffer.append("b");

    }
}
