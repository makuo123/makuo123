package com.stock.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LeaveCalculator {
    public static void main(String[] args) {
        // 请假开始时间
        LocalDateTime leaveStartTime = LocalDateTime.of(2023, 11, 1, 9, 0);

        // 请假结束时间
        LocalDateTime leaveEndTime = LocalDateTime.of(2023, 11, 10, 18, 0);

        // 多次销假时间范围
        List<Pair<LocalDateTime, LocalDateTime>> cancelLeaveRanges = new ArrayList<>();
        cancelLeaveRanges.add(new Pair<>(LocalDateTime.of(2023, 11, 1, 13, 0), LocalDateTime.of(2023, 11, 1, 18, 0)));
        cancelLeaveRanges.add(new Pair<>(LocalDateTime.of(2023, 11, 6, 12, 0), LocalDateTime.of(2023, 11, 8, 9, 0)));
        cancelLeaveRanges.add(new Pair<>(LocalDateTime.of(2023, 11, 7, 18, 0), LocalDateTime.of(2023, 11, 10, 17, 0)));

        // 计算请假剩余时间
        List<Pair<LocalDateTime, LocalDateTime>> remainingLeaveRanges = calculateRemainingLeaveDuration(leaveStartTime, leaveEndTime, cancelLeaveRanges);

        // 输出请假剩余时间的日期区间范围
        for (Pair<LocalDateTime, LocalDateTime> range : remainingLeaveRanges) {
            System.out.println("请假剩余时间的日期区间范围：从 " + range.getFirst() + " 到 " + range.getSecond());
        }
    }


    private static List<Pair<LocalDateTime, LocalDateTime>> calculateRemainingLeaveDuration(LocalDateTime leaveStartTime, LocalDateTime leaveEndTime, List<Pair<LocalDateTime, LocalDateTime>> cancelLeaveRanges) {
        List<Pair<LocalDateTime, LocalDateTime>> remainingLeaveRanges = new ArrayList<>();
        LocalDateTime currentLeaveTime = leaveStartTime;
        for (Pair<LocalDateTime, LocalDateTime> cancelRange : cancelLeaveRanges) {
            if (currentLeaveTime.isBefore(cancelRange.getFirst())) {
                remainingLeaveRanges.add(new Pair<>(currentLeaveTime, cancelRange.getFirst().minusMinutes(1)));
                currentLeaveTime = cancelRange.getSecond().plusMinutes(1);
            } else {
                currentLeaveTime = cancelRange.getSecond().plusMinutes(1);
            }
        }
        if (currentLeaveTime.isBefore(leaveEndTime) || currentLeaveTime.isEqual(leaveEndTime)) {
            remainingLeaveRanges.add(new Pair<>(currentLeaveTime, leaveEndTime));
        }
        return remainingLeaveRanges;
    }

    private static class Pair<A, B> {
        private final A first;
        private final B second;

        public Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }

        public A getFirst() {
            return first;
        }

        public B getSecond() {
            return second;
        }
    }
}


