package set;

import net.jqwik.api.*;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.StringLength;
import org.junit.jupiter.api.Assertions;

import java.util.*;

public class UnionSetMergeUserTest {
    
    @Property
    void mergeUsersReturnsNonNegativeSize(@ForAll("userLists") List<UnionSetMergeUser.User> users) {
        // 测试合并用户后返回的集合数量应该是非负数
        int size = UnionSetMergeUser.mergeUsers(users);
        Assertions.assertTrue(size >= 0);
    }
    
    @Property
    void mergeUsersWithEmptyListReturnsZero() {
        // 测试空列表应该返回0
        int size = UnionSetMergeUser.mergeUsers(Collections.emptyList());
        Assertions.assertEquals(0, size);
    }
    
    @Property
    void mergeUsersWithSingleUserReturnsOne() {
        // 测试单个用户应该返回1
        UnionSetMergeUser.User user = new UnionSetMergeUser.User("1", "github1", "bilibili1");
        List<UnionSetMergeUser.User> users = Collections.singletonList(user);
        int size = UnionSetMergeUser.mergeUsers(users);
        Assertions.assertEquals(1, size);
    }
    
    @Property
    void mergeUsersWithDuplicateIdsMergesCorrectly(@ForAll("userListsWithSameIds") List<UnionSetMergeUser.User> users) {
        // 测试具有重复ID的用户应该被正确合并
        int size = UnionSetMergeUser.mergeUsers(users);
        // 集合数量应该小于等于用户数量
        Assertions.assertTrue(size <= users.size());
    }
    
    @Property
    void usersWithSameIdAreMerged(@ForAll("userPairsWithSameId") List<UnionSetMergeUser.User> userPairs) {
        // 测试具有相同ID的用户应该被合并到同一个集合中
        if (userPairs.size() >= 2) {
            UnionSetMergeUser.User user1 = userPairs.get(0);
            UnionSetMergeUser.User user2 = userPairs.get(1);
            
            List<UnionSetMergeUser.User> users = Arrays.asList(user1, user2);
            int size = UnionSetMergeUser.mergeUsers(users);
            
            // 如果两个用户有相同的ID，它们应该被合并到同一个集合中
            if (user1.id().equals(user2.id())) {
                Assertions.assertEquals(1, size);
            }
        }
    }
    
    @Property
    void usersWithSameGithubIdAreMerged(@ForAll("userPairsWithSameGithubId") List<UnionSetMergeUser.User> userPairs) {
        // 测试具有相同GitHub ID的用户应该被合并到同一个集合中
        if (userPairs.size() >= 2) {
            UnionSetMergeUser.User user1 = userPairs.get(0);
            UnionSetMergeUser.User user2 = userPairs.get(1);
            
            List<UnionSetMergeUser.User> users = Arrays.asList(user1, user2);
            int size = UnionSetMergeUser.mergeUsers(users);
            
            // 如果两个用户有相同的GitHub ID，它们应该被合并到同一个集合中
            if (user1.githubId().equals(user2.githubId())) {
                Assertions.assertEquals(1, size);
            }
        }
    }
    
    @Property
    void usersWithSameBilibiliIdAreMerged(@ForAll("userPairsWithSameBilibiliId") List<UnionSetMergeUser.User> userPairs) {
        // 测试具有相同Bilibili ID的用户应该被合并到同一个集合中
        if (userPairs.size() >= 2) {
            UnionSetMergeUser.User user1 = userPairs.get(0);
            UnionSetMergeUser.User user2 = userPairs.get(1);
            
            List<UnionSetMergeUser.User> users = Arrays.asList(user1, user2);
            int size = UnionSetMergeUser.mergeUsers(users);
            
            // 如果两个用户有相同的Bilibili ID，它们应该被合并到同一个集合中
            if (user1.bilibiliId().equals(user2.bilibiliId())) {
                Assertions.assertEquals(1, size);
            }
        }
    }
    
    @Provide
    Arbitrary<List<UnionSetMergeUser.User>> userLists() {
        return Arbitraries.integers().between(0, 10)
                .flatMap(size -> Arbitraries.strings().withCharRange('a', 'z').ofLength(5)
                        .map(id -> new UnionSetMergeUser.User(id, "github_" + id, "bilibili_" + id))
                        .list().ofSize(size));
    }
    
    @Provide
    Arbitrary<List<UnionSetMergeUser.User>> userListsWithSameIds() {
        return Arbitraries.integers().between(2, 5)
                .flatMap(size -> {
                    Arbitrary<String> id = Arbitraries.strings().withCharRange('a', 'z').ofLength(3);
                    Arbitrary<String> githubId = Arbitraries.strings().withCharRange('a', 'z').ofLength(4);
                    Arbitrary<String> bilibiliId = Arbitraries.strings().withCharRange('a', 'z').ofLength(4);
                    
                    return Combinators.combine(id, githubId, bilibiliId)
                            .as((i, g, b) -> new UnionSetMergeUser.User(i, g, b))
                            .list().ofSize(size)
                            .flatMap(users -> {
                                // 添加一些具有重复ID的用户
                                List<UnionSetMergeUser.User> userList = new ArrayList<>(users);
                                if (!users.isEmpty()) {
                                    UnionSetMergeUser.User original = users.get(0);
                                    userList.add(new UnionSetMergeUser.User(original.id(), 
                                            "github_duplicate", "bilibili_duplicate"));
                                }
                                return Arbitraries.just(userList);
                            });
                });
    }
    
    @Provide
    Arbitrary<List<UnionSetMergeUser.User>> userPairsWithSameId() {
        return Arbitraries.strings().withCharRange('a', 'z').ofLength(5)
                .flatMap(id -> {
                    Arbitrary<String> githubId1 = Arbitraries.strings().withCharRange('a', 'z').ofLength(6);
                    Arbitrary<String> bilibiliId1 = Arbitraries.strings().withCharRange('a', 'z').ofLength(6);
                    Arbitrary<String> githubId2 = Arbitraries.strings().withCharRange('a', 'z').ofLength(6);
                    Arbitrary<String> bilibiliId2 = Arbitraries.strings().withCharRange('a', 'z').ofLength(6);
                    
                    return Combinators.combine(githubId1, bilibiliId1, githubId2, bilibiliId2)
                            .as((g1, b1, g2, b2) -> Arrays.asList(
                                    new UnionSetMergeUser.User(id, g1, b1),
                                    new UnionSetMergeUser.User(id, g2, b2)
                            ));
                });
    }
    
    @Provide
    Arbitrary<List<UnionSetMergeUser.User>> userPairsWithSameGithubId() {
        return Arbitraries.strings().withCharRange('a', 'z').ofLength(5)
                .flatMap(githubId -> {
                    Arbitrary<String> id1 = Arbitraries.strings().withCharRange('a', 'z').ofLength(6);
                    Arbitrary<String> bilibiliId1 = Arbitraries.strings().withCharRange('a', 'z').ofLength(6);
                    Arbitrary<String> id2 = Arbitraries.strings().withCharRange('a', 'z').ofLength(6);
                    Arbitrary<String> bilibiliId2 = Arbitraries.strings().withCharRange('a', 'z').ofLength(6);
                    
                    return Combinators.combine(id1, bilibiliId1, id2, bilibiliId2)
                            .as((i1, b1, i2, b2) -> Arrays.asList(
                                    new UnionSetMergeUser.User(i1, githubId, b1),
                                    new UnionSetMergeUser.User(i2, githubId, b2)
                            ));
                });
    }
    
    @Provide
    Arbitrary<List<UnionSetMergeUser.User>> userPairsWithSameBilibiliId() {
        return Arbitraries.strings().withCharRange('a', 'z').ofLength(5)
                .flatMap(bilibiliId -> {
                    Arbitrary<String> id1 = Arbitraries.strings().withCharRange('a', 'z').ofLength(6);
                    Arbitrary<String> githubId1 = Arbitraries.strings().withCharRange('a', 'z').ofLength(6);
                    Arbitrary<String> id2 = Arbitraries.strings().withCharRange('a', 'z').ofLength(6);
                    Arbitrary<String> githubId2 = Arbitraries.strings().withCharRange('a', 'z').ofLength(6);
                    
                    return Combinators.combine(id1, githubId1, id2, githubId2)
                            .as((i1, g1, i2, g2) -> Arrays.asList(
                                    new UnionSetMergeUser.User(i1, g1, bilibiliId),
                                    new UnionSetMergeUser.User(i2, g2, bilibiliId)
                            ));
                });
    }
}