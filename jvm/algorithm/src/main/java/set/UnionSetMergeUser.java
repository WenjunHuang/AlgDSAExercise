package set;

import java.util.HashMap;

public class UnionSetMergeUser {
    public record User(String id, String githubId, String bilibiliId) {
    }

    public static int mergeUsers(Iterable<User> users){
        var set = new UnionSet<>(users);
        var idMap = new HashMap<String,User>();
        var githubIdMap = new HashMap<String,User>();
        var bilibiliIdMap = new HashMap<String,User>();
        
        for (var user : users){
            if (idMap.containsKey(user.id)){
                set.union(idMap.get(user.id), user);
            } else {
                idMap.put(user.id, user);
            }
            
            if (githubIdMap.containsKey(user.githubId)){
                set.union(githubIdMap.get(user.githubId), user);
            } else {
                githubIdMap.put(user.githubId, user);
            }
            
            if (bilibiliIdMap.containsKey(user.bilibiliId)){
                set.union(bilibiliIdMap.get(user.bilibiliId), user);
            } else{
                bilibiliIdMap.put(user.bilibiliId, user);
            }
        }

        return set.size();
    }
}
