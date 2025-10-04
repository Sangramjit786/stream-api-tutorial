package net.javaguides.sys.design.typehead;

import java.util.*;
import java.util.stream.Collectors;

public class SimpleAutocomplete {

    // Trie node
    private static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isWord = false;
    }

    // Autocomplete service
    public static class AutocompleteService {
        private final TrieNode root = new TrieNode();
        // store full phrase -> frequency (higher means more popular)
        private final Map<String, Integer> freqMap = new HashMap<>();

        // Insert phrase with initial frequency (if phrase already exists, increment by freq)
        public synchronized void insert(String phrase, int freq) {
            if (phrase == null || phrase.isEmpty()) return;
            // normalize (lowercase) for simplicity; adapt if you need case sensitivity
            String p = phrase.trim().toLowerCase();
            // update frequency map
            freqMap.put(p, freqMap.getOrDefault(p, 0) + freq);

            // insert into trie
            TrieNode node = root;
            for (char c : p.toCharArray()) {
                node = node.children.computeIfAbsent(c, k -> new TrieNode());
            }
            node.isWord = true;
        }

        // Convenience: insert with freq = 1 (e.g., user selected this suggestion once)
        public synchronized void insert(String phrase) {
            insert(phrase, 1);
        }

        // Get top K completions for a prefix, sorted by frequency desc, tie-breaker: lexicographic
        public synchronized List<String> query(String prefix, int k) {
            if (prefix == null) return Collections.emptyList();
            String p = prefix.trim().toLowerCase();
            TrieNode node = root;
            for (char c : p.toCharArray()) {
                node = node.children.get(c);
                if (node == null) return Collections.emptyList();
            }

            // collect all words under node
            List<String> completions = new ArrayList<>();
            collectWords(node, new StringBuilder(p), completions);

            // sort by frequency desc, then lexicographically; use Java 8 streams
            return completions.stream()
                    .sorted(Comparator.comparingInt((String s) -> freqMap.getOrDefault(s, 0)).reversed()
                            .thenComparing(s -> s))
                    .limit(k)
                    .collect(Collectors.toList());
        }

        // DFS to collect words under a trie node
        private void collectWords(TrieNode node, StringBuilder prefix, List<String> out) {
            if (node.isWord) {
                out.add(prefix.toString());
            }
            for (Map.Entry<Character, TrieNode> e : node.children.entrySet()) {
                prefix.append(e.getKey());
                collectWords(e.getValue(), prefix, out);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }

        // For debug / inspection
        public synchronized void printAll() {
            List<String> all = new ArrayList<>();
            collectWords(root, new StringBuilder(), all);
            all.sort(Comparator.comparingInt((String s) -> freqMap.getOrDefault(s, 0)).reversed());
            all.forEach(s -> System.out.println(s + " -> " + freqMap.getOrDefault(s, 0)));
        }
    }

    // Demo
    public static void main(String[] args) {
        AutocompleteService svc = new AutocompleteService();

        // seed with some queries and frequencies
        svc.insert("apple", 50);
        svc.insert("apple pie", 20);
        svc.insert("apple watch", 30);
        svc.insert("app", 10);
        svc.insert("application", 5);
        svc.insert("apply", 8);
        svc.insert("banana", 40);
        svc.insert("band", 25);
        svc.insert("bandana", 3);
        svc.insert("bandwidth", 12);

        // simulate user selecting "application" twice (increment frequency)
        svc.insert("application", 2);

        System.out.println("Top 5 for prefix 'ap': " + svc.query("ap", 5));
        System.out.println("Top 3 for prefix 'app': " + svc.query("app", 3));
        System.out.println("Top 4 for prefix 'ban': " + svc.query("ban", 4));

        System.out.println("\nAll phrases (desc freq):");
        svc.printAll();
    }
}
