package leetcodecn;

// [1000325]实现 Trie (前缀树)
class QC3q1f {


    //IMPORTANT!! Submit Code Region Begin(Do not remove this line)
    class Trie {
        class TrieNode {
            int pass;
            int end;
            TrieNode[] nexts;

            TrieNode() {
                pass = 0;
                end = 0;
                nexts = new TrieNode[26];
            }
        }

        private TrieNode root;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            root = new TrieNode();
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            var cur = root;
            for (var i = 0; i <= word.length(); i++) {
                cur.pass++;
                if (i == word.length()) {
                    cur.end++;
                } else {
                    var next = cur.nexts[word.charAt(i) - 'a'];
                    if (next == null) {
                        next = new TrieNode();
                        cur.nexts[word.charAt(i) - 'a'] = next;
                    }
                    cur = next;
                }
            }

        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            var cur = root;
            for (var i = 0; i < word.length() && cur != null; i++) {
                cur = cur.nexts[word.charAt(i) - 'a'];
            }

            return cur != null && cur.end > 0;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            var cur = root;
            for (var i = 0; i < prefix.length() && cur != null; i++) {
                cur = cur.nexts[prefix.charAt(i) - 'a'];
            }
            return cur != null;
        }
    }

    /**
     * Your Trie object will be instantiated and called as such:
     * Trie obj = new Trie();
     * obj.insert(word);
     * boolean param_2 = obj.search(word);
     * boolean param_3 = obj.startsWith(prefix);
     */
//IMPORTANT!! Submit Code Region End(Do not remove this line)
    public static void main(String[] args) {
        // add your test code
    }
}
