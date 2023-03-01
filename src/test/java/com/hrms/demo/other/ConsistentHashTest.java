package com.hrms.demo.other;

import cn.hutool.core.lang.ConsistentHash;
import cn.hutool.core.lang.UUID;
import com.google.common.hash.Hashing;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ConsistentHashTest {


    @Test
    public void createId() {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            out.append("\"").append(UUID.fastUUID()).append("\"").append(",");
        }
        out.deleteCharAt(out.length() - 1);
        System.out.println(out);
    }


    @Test
    public void testHutoolConsistentHash() {
        // 3 个物理节点 每个物理节点增加三个虚拟节点
        ArrayList<Integer> physicalNodes = Lists.newArrayList(0, 1, 2, 3);
        ConsistentHash<Integer> context = new ConsistentHash<>(3, physicalNodes);
        List<String> idList = Lists.newArrayList("10bd42c6-abcd-4e05-9418-6d1621d38412", "97ee8d76-5c53-4a02-87a4-72d32ad1be40", "2e4d9bf0-5d32-4dbd-ade8-de8d6ba7d443", "9e95a1be-9a31-4295-86fd-beb3fd1169d8", "40a4a364-23a4-48d8-acee-ab0b446e4e64", "87c8e694-7ce2-460a-8ee7-c07929377f76", "930bf083-564e-4f03-ad7a-9a404dff55ff", "3e78b94b-4127-4d63-bf37-d03ef81b0ca1", "f32411d9-0790-4b48-b623-c6b5322813d6", "31184530-d6aa-495a-8e48-e798604c4eb5");

        System.out.println("初始映射关系");
        Function<String, Integer> function = context::get;
        formatPrint(idList, function);
        // 新增一个物理节点
        context.add(4);
        System.out.println("新增一个物理节点【4】后映射关系");
        formatPrint(idList, function);
        //再新增一个物理节点
        context.add(5);
        System.out.println("新增一个物理节点【5】后映射关系");
        formatPrint(idList, function);
    }

    @Test
    public void testGuavaConsistentHash() {
        List<String> idList = Lists.newArrayList("10bd42c6-abcd-4e05-9418-6d1621d38412", "97ee8d76-5c53-4a02-87a4-72d32ad1be40", "2e4d9bf0-5d32-4dbd-ade8-de8d6ba7d443", "9e95a1be-9a31-4295-86fd-beb3fd1169d8", "40a4a364-23a4-48d8-acee-ab0b446e4e64", "87c8e694-7ce2-460a-8ee7-c07929377f76", "930bf083-564e-4f03-ad7a-9a404dff55ff", "3e78b94b-4127-4d63-bf37-d03ef81b0ca1", "f32411d9-0790-4b48-b623-c6b5322813d6", "31184530-d6aa-495a-8e48-e798604c4eb5");
        AtomicInteger buckets = new AtomicInteger(3);
        System.out.println("初始映射关系");
        Function<String, Integer> function = f -> Hashing.consistentHash(f.hashCode(), buckets.incrementAndGet());
        formatPrint(idList, function);
        // 新增一个物理节点
        buckets.incrementAndGet();
        System.out.println("新增一个物理节点【4】后映射关系");
        formatPrint(idList, function);
        //再新增一个物理节点
        buckets.incrementAndGet();
        System.out.println("新增一个物理节点【5】后映射关系");
        formatPrint(idList, function);
    }


    private void formatPrint(List<String> idList, Function<String, Integer> function) {
        Map<String, Integer> map = new HashMap<>(idList.size());
        idList.forEach(t -> map.put(t, function.apply(t)));
        Map<String, Integer> orderedMap = reverseOrderMapByValue(map);
        orderedMap.forEach((k, v) -> {
            System.out.println("[" + k + "=" + v + "]");
        });
    }

    private <K, V extends Comparable<V>> Map<K, V> reverseOrderMapByValue(Map<K, V> originalMap) {
        return originalMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }
}
