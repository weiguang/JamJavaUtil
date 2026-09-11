package com.okayjam.code.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 *
 * @author JamChen jamchen@tencent.com
 * @date 2026/06/16 15:33
 **/
public class Twitter {

    private final HashMap<Integer, LinkedList<Tweet>> userTweet;
    private final HashMap<Integer, Set<Integer>> userFollow;
    private int counter;

    public Twitter() {
        userTweet = new HashMap<>();
        userFollow = new HashMap<>();
        counter = 0;
    }

    public static class Tweet implements Comparable<Tweet> {
        private final int tweetId;
        private final int order;

        public Tweet(int tweetId, int order) {
            this.tweetId = tweetId;
            this.order = order;
        }

        @Override
        public int compareTo(Twitter.Tweet o) {
            return Integer.compare(this.order, o.order);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            return order == ((Tweet) o).order;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(order);
        }

    }

    public void postTweet(int userId, int tweetId) {
        Tweet tweet = new Tweet(tweetId, ++counter);
        userTweet.computeIfAbsent(userId, k -> new LinkedList<>()).addFirst(tweet);
    }

    private static class HeapEntry {
        Tweet tweet;
        int listIdx;
        int tweetIdx;

        HeapEntry(Tweet tweet, int listIdx, int tweetIdx) {
            this.tweet = tweet;
            this.listIdx = listIdx;
            this.tweetIdx = tweetIdx;
        }
    }

    public List<Integer> getNewsFeed(int userId) {
        List<List<Tweet>> lists = new ArrayList<>();
        List<Tweet> selfTweets = userTweet.get(userId);
        if (selfTweets != null && !selfTweets.isEmpty()) {
            lists.add(selfTweets);
        }
        Set<Integer> follows = userFollow.get(userId);
        if (follows != null) {
            for (int fid : follows) {
                List<Tweet> ft = userTweet.get(fid);
                if (ft != null && !ft.isEmpty()) {
                    lists.add(ft);
                }
            }
        }
        if (lists.isEmpty()) {
            return Collections.emptyList();
        }

        PriorityQueue<HeapEntry> maxHeap = new PriorityQueue<>(
                (a, b) -> Integer.compare(b.tweet.order, a.tweet.order));

        for (int i = 0; i < lists.size(); i++) {
            List<Tweet> list = lists.get(i);
            maxHeap.offer(new HeapEntry(list.get(0), i, 0));
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < 10 && !maxHeap.isEmpty(); i++) {
            HeapEntry entry = maxHeap.poll();
            result.add(entry.tweet.tweetId);
            int nextIdx = entry.tweetIdx + 1;
            List<Tweet> list = lists.get(entry.listIdx);
            if (nextIdx < list.size()) {
                maxHeap.offer(new HeapEntry(list.get(nextIdx), entry.listIdx, nextIdx));
            }
        }
        return result;
    }

    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) {
            return;
        }
        userFollow.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        Set<Integer> follows = userFollow.get(followerId);
        if (follows != null) {
            follows.remove(followeeId);
        }
    }

    public static void main(String[] args) {
        Twitter twitter = new Twitter();
        twitter.postTweet(1, 100);
        twitter.postTweet(2, 200);
        twitter.postTweet(1, 101);
        twitter.postTweet(2, 201);
        twitter.postTweet(1, 102);

        System.out.println("Before follow user1:");
        twitter.getNewsFeed(2).forEach(System.out::println);

        twitter.follow(2, 1);
        System.out.println("After follow user1:");
        twitter.getNewsFeed(2).forEach(System.out::println);

        twitter.unfollow(2, 1);
        System.out.println("After unfollow user1:");
        twitter.getNewsFeed(2).forEach(System.out::println);
    }
}
