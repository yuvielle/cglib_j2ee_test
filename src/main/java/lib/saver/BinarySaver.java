package lib.saver;

import lib.CacheKeysComparator;
import java.io.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Created by Yuvielle on 15.02.14.
 */
public class BinarySaver<KeyType extends Serializable, ValueType extends Serializable>  implements BaseSaver<KeyType,ValueType> {

    private ConcurrentHashMap<KeyType,String> hashMap;
    private ConcurrentSkipListMap<KeyType, Integer> mapKeys;
    String  pathToObject;

    public BinarySaver(){
        hashMap = new ConcurrentHashMap<KeyType, String>();
        mapKeys = new ConcurrentSkipListMap<KeyType, Integer>();
        File tempFolder = new File("resources\\data\\binary\\");
        boolean exists = tempFolder.exists();
        if(!exists){
            tempFolder.mkdirs();
        }
    }

    @Override
    public void insertObject(KeyType key, ValueType value) throws IOException, ClassNotFoundException {
        pathToObject = "resources\\data\\binary\\" + value.getClass().getName() + UUID.randomUUID().toString() + ".temp";
        mapKeys.put(key,1);
        hashMap.put(key, pathToObject);
        try{
            FileOutputStream fileStream = new FileOutputStream(pathToObject);
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
            objectStream.writeObject(value);
            objectStream.flush();
            objectStream.close();
            fileStream.flush();
            fileStream.close();
        }
        catch (IOException e){
            System.out.println("catched exeption: " + e.getMessage());
        }
    }

    @Override
    public void updateObject(KeyType key, ValueType value) throws IOException, ClassNotFoundException {

    }

    @Override
    public ValueType getObject(KeyType key, String tablename) throws IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public void deleteObject(KeyType key, String tablename) {
        if(hashMap.containsKey(key))
        {
            File deletingFile = new File(hashMap.remove(key));
            mapKeys.remove(key);
            deletingFile.delete();
        }
    }

    @Override
    public void clearHolder() {
        for(KeyType key : hashMap.keySet()){
            File deletingFile = new File(hashMap.get(key));
            deletingFile.delete();
        }
        hashMap.clear();
        mapKeys.clear();
    }

    @Override
    public ValueType removeObject(KeyType key, String tablename) throws IOException, ClassNotFoundException {
        if(hashMap.containsKey(key))
        {
            ValueType result = this.getObject(key, tablename);
            this.deleteObject(key, tablename);
            return result;
        }
        return null;
    }

    @Override
    public boolean containsKey(KeyType key, String tablename) {
        return hashMap.containsKey(key);
    }

    @Override
    public int size() {
        return hashMap.size();
    }

    @Override
    public void reset() throws IOException, ClassNotFoundException {

    }

    @Override
    public ArrayList getCollection(String modelname) throws IOException {
        return null;
    }

    @Override
    public void addWhere(String expression) throws Exception {

    }

    @Override
    public void addJoin(String tablename) throws Exception {

    }

    @Override
    public ArrayList executeQuery(String query) throws Exception {
        return null;
    }


    public Set<KeyType> getQuantityKeys()
    {
        CacheKeysComparator comparator = new CacheKeysComparator(mapKeys);
        ConcurrentSkipListMap<KeyType,Integer> sorted = new ConcurrentSkipListMap(comparator);
        sorted.putAll(mapKeys);
        return sorted.keySet();
    }
}
