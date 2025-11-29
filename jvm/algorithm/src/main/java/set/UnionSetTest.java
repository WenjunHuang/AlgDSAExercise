package set;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;
import org.junit.jupiter.api.Assertions;

import java.util.*;

public class UnionSetTest {
    
    @Property
    void reflexiveProperty(@ForAll @Size(value = 5) List<@IntRange(min = 1, max = 100) Integer> elements) {
        // 创建一个新的UnionSet实例
        UnionSet<Integer> unionSet = new UnionSet<>(elements);
        
        // 自反性：每个元素应该与自身在同一个集合中
        for (Integer element : elements) {
            Assertions.assertTrue(unionSet.isSameSet(element, element));
        }
    }
    
    @Property
    void symmetricProperty(@ForAll @Size(value = 5) List<@IntRange(min = 1, max = 100) Integer> elements,
                          @ForAll("distinctPair") Tuple.Tuple2<Integer, Integer> pair) {
        // 创建一个新的UnionSet实例
        UnionSet<Integer> unionSet = new UnionSet<>(elements);
        
        int first = pair.get1();
        int second = pair.get2();
        
        // 对称性：如果first和second在同一个集合中，那么second和first也应该在同一个集合中
        boolean firstSecond = unionSet.isSameSet(first, second);
        boolean secondFirst = unionSet.isSameSet(second, first);
        
        Assertions.assertEquals(firstSecond, secondFirst);
    }
    
    @Property
    void transitiveProperty(@ForAll @Size(value = 5) List<@IntRange(min = 1, max = 100) Integer> elements) {
        // 创建一个新的UnionSet实例
        UnionSet<Integer> unionSet = new UnionSet<>(elements);
        
        // 随机选择三个元素进行测试
        if (elements.size() >= 3) {
            Integer first = elements.get(0);
            Integer second = elements.get(1);
            Integer third = elements.get(2);
            
            // 执行联合操作
            unionSet.union(first, second);
            unionSet.union(second, third);
            
            // 传递性：如果first和second在同一个集合中，second和third在同一个集合中，
            // 那么first和third也应该在同一个集合中
            Assertions.assertTrue(unionSet.isSameSet(first, third));
        }
    }
    
    @Property
    void unionOperation(@ForAll @Size(value = 5) List<@IntRange(min = 1, max = 100) Integer> elements,
                       @ForAll("distinctPair") Tuple.Tuple2<Integer, Integer> pair) {
        // 创建一个新的UnionSet实例
        UnionSet<Integer> unionSet = new UnionSet<>(elements);
        
        int first = pair.get1();
        int second = pair.get2();
        
        // 确保元素存在于集合中
        if (!elements.contains(first)) {
            first = elements.get(0);
        }
        if (!elements.contains(second)) {
            second = elements.get(1);
        }
        
        // 在联合之前，两个不同的元素不应该在同一个集合中
        if (first != second) {
            Assertions.assertFalse(unionSet.isSameSet(first, second));
        }
        
        // 执行联合操作
        unionSet.union(first, second);
        
        // 联合之后，两个元素应该在同一个集合中
        Assertions.assertTrue(unionSet.isSameSet(first, second));
    }
    
    @Property
    void unionWithSameElement(@ForAll @Size(value = 5) List<@IntRange(min = 1, max = 100) Integer> elements,
                             @ForAll int element) {
        // 创建一个新的UnionSet实例
        UnionSet<Integer> unionSet = new UnionSet<>(elements);
        
        // 如果元素存在于集合中
        if (elements.contains(element)) {
            // 与自身联合不应该改变任何东西
            unionSet.union(element, element);
            
            // 元素应该仍然与自身在同一个集合中
            Assertions.assertTrue(unionSet.isSameSet(element, element));
        }
    }
    
    @Provide
    Arbitrary<Tuple.Tuple2<Integer, Integer>> distinctPair() {
        return Arbitraries.integers().between(1, 100)
                .flatMap(first -> Arbitraries.integers().between(1, 100)
                        .filter(second -> !second.equals(first))
                        .map(second -> Tuple.of(first, second)));
    }
}