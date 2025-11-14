package utils;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Combinators;
import net.jqwik.api.Provide;

import java.util.Arrays;
import java.util.Random;

public abstract class AbstractTestDataGenerator {
    private final Random random = new Random();

    // 定义数组生成器
    @Provide
    public Arbitrary<int[]> intArrayGen() {
        return Arbitraries.integers().array(int[].class);
    }

    // 定义特定大小的数组生成器
    @Provide
    public Arbitrary<int[]> largeArrayGen() {
        return Arbitraries.integers().array(int[].class).ofMinSize(1000).ofMaxSize(1000000);
    }

    // 定义有序数组生成器
    @Provide
    public Arbitrary<int[]> sortedArrayGen() {
        return Combinators.combine(
                Arbitraries.integers(),
                Arbitraries.integers().between(1, 1000000),
                Arbitraries.integers().between(1, 10)
        ).as((start, size, step) -> {
            int[] result = new int[size];
            for (int i = 0; i < size; i++) {
                result[i] = start + i * step;
            }
            return result;
        });
    }

    // 定义重复元素数组生成器
    @Provide
    public Arbitrary<int[]> duplicateArrayGen() {
        return Combinators.combine(
                Arbitraries.integers(),
                Arbitraries.integers().between(1, 1000000)
        ).as((value, size) -> {
            int[] result = new int[size];
            Arrays.fill(result, value);
            return result;
        });
    }

    // 定义部分有序数组生成器
    // 生成一个数组，其中包含多个有序子序列，但整体不是完全有序的
    @Provide
    public Arbitrary<int[]> partiallySortedArrayGen() {
        return Arbitraries.integers().between(10, 1000000)
                .flatMap(size -> {
                    int maxSegmentCount = Math.max(2, size / 5);
                    return Arbitraries.integers().between(2, maxSegmentCount)
                            .map(segmentCount -> {
                                int[] result = new int[size];
                                int baseSegmentSize = size / segmentCount;
                                int remainder = size % segmentCount;

                                int currentIndex = 0;
                                for (int segment = 0; segment < segmentCount; segment++) {
                                    // 计算当前段的实际大小
                                    int segmentSize = baseSegmentSize + (segment < remainder ? 1 : 0);
                                    int segmentEnd = currentIndex + segmentSize;

                                    // 填充当前段
                                    if (segment % 2 == 0) {
                                        // 偶数段：填充有序数据
                                        int startValue = random.nextInt(10000);
                                        for (int i = currentIndex; i < segmentEnd; i++) {
                                            result[i] = startValue + (i - currentIndex);
                                        }
                                    } else {
                                        // 奇数段：填充随机数据
                                        for (int i = currentIndex; i < segmentEnd; i++) {
                                            result[i] = random.nextInt(10000);
                                        }
                                    }

                                    currentIndex = segmentEnd;
                                }

                                return result;
                            });
                });
    }

    // 定义包含极值的数组生成器
    @Provide
    public Arbitrary<int[]> extremeValueArrayGen() {
        return Arbitraries.of(
                Integer.MIN_VALUE,
                Integer.MAX_VALUE,
                0,
                -1,
                1,
                Integer.MIN_VALUE + 1,
                Integer.MAX_VALUE - 1
        ).array(int[].class);
    }
}
