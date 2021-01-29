class WordLadder2 {
    HashMap<String, List<String>> childNodes = new HashMap<>();
    List<List<String>> ans = new ArrayList<List<String>>(); 
    int distance;
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord)){
            return ans;
        }
        distance = ladderLength(beginWord, endWord, wordList);
        if(distance == 0){
            return ans;
        }
        // System.out.println(childNodes);
        ArrayList<String> path = new ArrayList<>();
        path.add(beginWord);
        dfs(beginWord, endWord, wordList, path); 
        return ans;
        
    }
    
    private void dfs(String beginWord, String endWord, List<String> wordList, List<String> path){
        if(path.size() == distance){
            return;
        }
        for(String child : childNodes.get(beginWord)){
            List<String> new_path = new ArrayList<>(path);
            new_path.add(child);
            if(child.equals(endWord)){
                ans.add(new_path);
                continue;
            }
            dfs(child, endWord, wordList, new_path);
        }
    }
    
    private int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int transformations = 0;
        if(!wordList.contains(endWord)){
            return transformations;
        }
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        while(queue.size() != 0){
            int level = queue.size();
            transformations++;
            while(level > 0){
                String word = queue.poll();
                ArrayList<String> nodes = new ArrayList<>();
                if(word.equals(endWord)){
                    return transformations;
                }
                for(int i=0; i<word.length(); i++){
                    for(char ch='a'; ch<='z'; ch++){
                        String newWord = word.substring(0,i)+ch+word.substring(i+1);
                        if(wordList.contains(newWord)){
                            queue.add(newWord);
                            nodes.add(newWord);
                            wordList.remove(newWord);
                        }
                    }
                }
                childNodes.put(word, nodes);
                level--;
            }
        }
        return 0;        
    }
}